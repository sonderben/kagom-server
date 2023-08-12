package com.sonderben.kagom;

import com.sonderben.kagom.entity.*;
import com.sonderben.kagom.repository.PaymentRepository;
import com.sonderben.kagom.repository.RoleRepository;
import com.sonderben.kagom.repository.ShipmentRepository;
import com.sonderben.kagom.service.CustomerService;
import com.sonderben.kagom.service.DistributionCenterService;
import com.sonderben.kagom.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootApplication
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
//@ComponentScan("com.sonderben.kagom")
public class KagomApplication  {

    public static void main(String[] args) {
        SpringApplication.run(KagomApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner start(CustomerService customerRepo,
                            EmployeeService employeeRepo,
                            DistributionCenterService distributionCenterService,
                            ShipmentRepository shipmentRepo,
                            PaymentRepository paymentRepo,
                            RoleRepository roleRepository){
        return args -> {





            roleRepository.save( new Role("CUSTOMER") );
            roleRepository.save( new Role("EMPLOYEE") );
            roleRepository.save( new Role("ADMIN") );




            List<PackageEntity>packageEntities = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                packageEntities.add(PackageEntity.getExemple());
            }


            DistributionCenterEntity dc = DistributionCenterEntity
                    .getExemple(AddressEntity.getExemple());

            distributionCenterService.save(dc);



            CustomerEntity customer = CustomerEntity.getExemple(dc,AddressEntity.getExemple());
            customer.setRoles(Collections.singletonList(new Role(1L)));
            customerRepo.save( customer );

            EmployeeEntity employee = EmployeeEntity.getExemple("user",  dc);
            employee.setRoles(Collections.singletonList(new Role(2L)));
            employeeRepo.save(employee);

            EmployeeEntity admin = EmployeeEntity.getExemple("admin",dc);
            admin.setRoles(Collections.singletonList(new Role(3L)));
            employeeRepo.save(admin);

            ShipmentEntity shipmentEntity = ShipmentEntity.builder()
                    .distributionDestination(dc)
                    .receiver(customer)
                    .sender(customer)
                    .status(ShipmentsStatus.INIT)
                    .deliveryEmployee(employee)
                    .receiverEmployee(employee)
                    .distributionOrigin(dc)
                    .shippingDate(new Date())
                    .distributionDestination(dc)
                    .KMPackage( packageEntities )
                    .build();

            shipmentRepo.save(shipmentEntity);



            PaymentEntity payments = PaymentEntity.getExemple(shipmentEntity);
            paymentRepo.save(payments);
        };
    }
}
