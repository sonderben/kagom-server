package com.sonderben.kagom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class CustomerEntity extends KMUser {

    @OneToOne
    private DistributionCenterEntity internationalAddresses;
    private boolean isTemp = false;

   public CustomerEntity(Long id, String fullName, String email, String telephone, Date birthday, String KMIdentity){

       super(id,fullName,email,telephone,birthday,KMIdentity);

   }


   public static CustomerEntity getExemple(
           DistributionCenterEntity distribution
           , AddressEntity address){
       Random random = new Random();

       CustomerEntity ce = new CustomerEntity();
       ce.setPassword("1234");
       ce.setAddress(address);
       ce.setRoles(Collections.singletonList(new Role(1L)));
       ce.setEmail("customer");
       ce.setFullName("Ben Pha");
       ce.setCountryIdentity("pp-324843");
       ce.setInternationalAddresses( distribution );
       ce.setDistributionCenter( distribution );
       ce.setBirthday(new Date());
       ce.setKMIdentity("KMI-0A0-0S1");
       ce.setTelephone("484 940 84 74");

       return  ce;
   }


}
