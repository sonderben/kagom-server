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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
    public WebMvcConfigurer Web(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000");
            }
        };
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
            roleRepository.save( new Role("DIRECTOR") );
            roleRepository.save( new Role("ADMIN") );




            List<PackageEntity>packageEntities = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                packageEntities.add(PackageEntity.getExemple());
            }
            List<PackageEntity>packageEntities2 = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                packageEntities.add(PackageEntity.getExemple());
            }


            DistributionCenterEntity dc = DistributionCenterEntity
                    .getExemple("Abc",false,AddressEntity.getExemple("Haïti","Lascirie # 4"));

            DistributionCenterEntity dc2 = DistributionCenterEntity
                    .getExemple("Mene",false,AddressEntity.getExemple("Haïti","Rue Magloire # 14"));

            DistributionCenterEntity dc3 = DistributionCenterEntity
                    .getExemple("Cool",false,AddressEntity.getExemple( "Haïti","Rue 23  # 24" ));

            AddressEntity address = AddressEntity
                    .builder()
                    .country("Usa")
                    .state("Miami")
                    .codePostal("25378363")
                    .direction("123 Main Street, Miami, Florida")
                    .build();
            DistributionCenterEntity international = DistributionCenterEntity
                    .getExemple("Great",true,address);



            distributionCenterService.save(international);
            distributionCenterService.save(dc);
            distributionCenterService.save(dc2);
            distributionCenterService.save(dc3);




            CustomerEntity customer = CustomerEntity.getExemple(dc,AddressEntity.getExemple( "Haïti","Block-hausse # 4" ));
            customer.setRoles(Collections.singletonList(new Role(1L)));
            customerRepo.save( customer );

            EmployeeEntity employee = EmployeeEntity.getExemple("user@gmail.com",JobTitle.CASHIER);
            employee.setRoles(Collections.singletonList(new Role(2L)));
            employeeRepo.save(employee);

            EmployeeEntity director = EmployeeEntity.getExemple("director@gmail.com",JobTitle.DIRECTOR);
            director.setRoles(Collections.singletonList(new Role(2L)));
            employeeRepo.save(director);

            EmployeeEntity admin = EmployeeEntity.getExemple("admin@gmail.com",JobTitle.ADMIN);
            admin.setRoles(Collections.singletonList(new Role(4L)));
            employeeRepo.save(admin);

            ShipmentEntity shipmentEntity = ShipmentEntity.builder()
                    .distributionDestination(dc)
                    .receiver(customer)
                    .sender(customer)
                    .shipmentsStatus(ShipmentsStatus.RETIRED)
                    .isLocal(false)
                    .info("Un ordinateur portable macOS doté d'un écran de 13,30 pouces avec une résolution de 2560 x 1600 pixels. Il est alimenté par un processeur Core i5 et est livré avec 12 Go de RAM. L'Apple MacBook Pro contient 512 Go de stockage SSD.")
                    .shipmentsStatusPercent(0f)
                    .trackingId("KMTS-1234-A")//("KMTS-"+ (new Date().getTime()-1692118753009L)+"-A" )//c
                    .deliveryEmployee(employee)
                    .receiverEmployee(employee)
                    .receivedDate(new Date())
                    .distributionOrigin(dc)
                    .shippingDate(new Date())
                    .distributionDestination(dc)
                    .KMPackage( packageEntities )
                    .build();
//
            ShipmentEntity shipmentEntity2 = ShipmentEntity.builder()
                    .distributionDestination(dc)
                    .receiver(customer)
                    .sender(customer)
                    .shipmentsStatus(ShipmentsStatus.SENT)
                    .isLocal(false)
                    .info("Un ordinateur portable macOS doté d'un écran de 13,30 pouces avec une résolution de 2560 x 1600 pixels. Il est alimenté par un processeur Core i5 et est livré avec 12 Go de RAM. L'Apple MacBook Pro contient 512 Go de stockage SSD.")
                    .shipmentsStatusPercent(0f)
                    .trackingId("KMTS-1234-B")//("KMTS-"+ (new Date().getTime()-1692118753009L)+"-A" )//c
                    .deliveryEmployee(employee)
                    .receiverEmployee(employee)
                    .receivedDate(new Date())
                    .distributionOrigin(dc)
                    .shippingDate(new Date())
                    .distributionDestination(dc)
                    .KMPackage( packageEntities2 )
                    .build();

            shipmentRepo.save(shipmentEntity);
            shipmentRepo.save(shipmentEntity2);



            PaymentEntity payments = PaymentEntity.getExemple(shipmentEntity);
            paymentRepo.save(payments);

            PaymentEntity payments2 = PaymentEntity.getExemple(shipmentEntity2);
            paymentRepo.save(payments2);
        };
    }
}
