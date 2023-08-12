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
@Builder
public class EmployeeEntity extends KMUser{


    private String jobTitle;


    public EmployeeEntity(Long id, String fullName,  String email, String telephone, Date birthday,String jobTitle, String KMIdentity){
        super(id,fullName,email,telephone,birthday,KMIdentity);
        this.jobTitle =  jobTitle;
    }

    public static EmployeeEntity getExemple(String email,DistributionCenterEntity distribution){

        EmployeeEntity ee = new EmployeeEntity();
        Random random = new Random();
        ee.setPassword("1234");
        ee.setAddress(AddressEntity.getExemple());
        //ee.setRoles(Collections.singletonList(new Role(1L)));
        ee.setEmail(email);
        ee.setFullName("Ben Pha");
        ee.setCountryIdentity("Gv-324843");
        ee.setJobTitle(email.equalsIgnoreCase("Admin")?"admin":"cashier");
        //ee.setDistributionCenter(distribution);
        ee.setBirthday(new Date());
        ee.setKMIdentity("KMI-0A0-"+random.nextInt(99));
        ee.setTelephone("484 940 84 74");

        return ee;
    }
}
