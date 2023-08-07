package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.BaseEntity;
import com.sonderben.kagom.entity.CustomerEntity;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public class Customer extends BaseEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private Date birthday;
    private String KMIdentity;
    private String address;
    private String password;
    private DistributionCenter distributionCenter;


    public Customer from(CustomerEntity customerEntity) {
        return Customer.builder()
                .id(customerEntity.getId())
                .firstName(customerEntity.getFirsName())
                .lastName(customerEntity.getLastName())
                .email(customerEntity.getEmail())
                .password(customerEntity.getPassword())
                .birthday(customerEntity.getBirthday())
                .KMIdentity(customerEntity.getKMIdentity())
                .address(customerEntity.getAddress().toString())
                .distributionCenter(DistributionCenter.from(customerEntity.getDistributionCenter()))
                .build();
    }



}
