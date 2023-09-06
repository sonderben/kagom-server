package com.sonderben.kagom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class CustomerEntity extends KMUser {

    @ManyToOne
    private DistributionCenterEntity internationalAddresses;
    private Boolean isTemp = false;
    private Long points;

   public CustomerEntity(Long id, String fullName, String email, String telephone, Date birthday, String KMIdentity){

       super(id,fullName,email,telephone,birthday,KMIdentity);

   }


   public static CustomerEntity getExemple(
           DistributionCenterEntity distribution
           , AddressEntity address){
       Random random = new Random();

       CustomerEntity ce = new CustomerEntity();
       ce.setPassword("!Kagom1234");
       ce.setAddress(address);
       ce.setRoles(Collections.singletonList(new Role(1L)));
       ce.setEmail("customer@gmail.com");
       ce.setFullName("Ben Pha");
       ce.setPoints(145_643L);
       ce.setCountryIdentity("pp-324843");
       ce.setInternationalAddresses( distribution );
       ce.setDistributionCenter( distribution );
       ce.setBirthday(new Date());
       ce.setDateCreated(new Date());
       ce.setKmIdentity("KMI-0A0-0S1");
       ce.setTelephone("484 940 84 74");

       return  ce;
   }


}
