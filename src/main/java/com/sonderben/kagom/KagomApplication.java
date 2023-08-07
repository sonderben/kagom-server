package com.sonderben.kagom;

import com.sonderben.kagom.entity.*;
import com.sonderben.kagom.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication

//@ComponentScan("com.sonderben.kagom")
public class KagomApplication  {

    public static void main(String[] args) {
        SpringApplication.run(KagomApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepo,
                            EmployeeRepository employeeRepo,
                            PackageRepository packageRepo,
                            ShipmentRepository shipmentRepo,
                            PaymentRepository paymentRepo,
                            RoleRepository roleRepository){
        return args -> {



            Role[] roles = {new Role("CUSTOMER"),new Role("EMPLOYEE")};

            roleRepository.saveAll( Arrays.asList(roles) );




            List<PackageEntity>packageEntities = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                packageEntities.add(PackageEntity.getExemple());
            }


            DistributionCenterEntity distributionCenter = DistributionCenterEntity
                    .getExemple(AddressEntity.getExemple());




            CustomerEntity customer = CustomerEntity.getExemple(distributionCenter,AddressEntity.getExemple());
            customer.setRoles(Collections.singletonList(new Role(2L)));
            customerRepo.save( customer );

            EmployeeEntity employee = EmployeeEntity.getExemple(distributionCenter,AddressEntity.getExemple());
            employee.setRoles(Collections.singletonList(new Role(2L)));
            employeeRepo.save(employee);

            ShipmentEntity shipmentEntity = ShipmentEntity.builder()
                    .distributionDestination(distributionCenter)
                    .receiver(customer)
                    .sender(customer)
                    .status(ShipmentsStatus.INIT)
                    .distributionOrigin(distributionCenter)
                    .shippingDate(new Date())
                    .distributionDestination(distributionCenter)
                    .KMPackage( packageEntities )
                    .build();

            shipmentRepo.save(shipmentEntity);



            PaymentEntity payments = PaymentEntity.getExemple(shipmentEntity);
            paymentRepo.save(payments);
        };
    }
}
