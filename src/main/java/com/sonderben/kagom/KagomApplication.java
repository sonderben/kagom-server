package com.sonderben.kagom;

import com.sonderben.kagom.entity.*;
import com.sonderben.kagom.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
                            InvoiceRepository invoiceRepo,
                            PaymentRepository paymentRepo){
        return args -> {

            /*PackageEntity packageEntity = PackageEntity.getExemple();
            packageRepo.save(packageEntity);*/
            List<PackageEntity>packageEntities = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                packageEntities.add(PackageEntity.getExemple());
            }


            DistributionCenterEntity distributionCenter = DistributionCenterEntity
                    .getExemple(AddressEntity.getExemple());




            CustomerEntity customer = CustomerEntity.getExemple(distributionCenter,AddressEntity.getExemple());

            customerRepo.save( customer );

            EmployeeEntity employee = EmployeeEntity.getExemple(distributionCenter,AddressEntity.getExemple());
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

            Invoices invoices = Invoices.getExemple(shipmentEntity,employee);
            invoiceRepo.save(invoices);

            Payments payments = Payments.getExemple(invoices);
            paymentRepo.save(payments);
        };
    }
}
