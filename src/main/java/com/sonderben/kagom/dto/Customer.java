package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.BaseEntity;
import com.sonderben.kagom.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer /*extends BaseEntity*/ {

    private Long id;
    private String fullName;
    private String email;
    private String telephone;
    private Date birthday;
    private String kmIdentity;
    private String address;
    private DistributionCenter distributionCenter;
    private Long points;



    public static   Customer from(CustomerEntity customerEntity) {
        return Customer.builder()
                .id(customerEntity.getId())
                .fullName(customerEntity.getFullName())
                .email(customerEntity.getEmail())
                .points(customerEntity.getPoints())
                .birthday(customerEntity.getBirthday())
                .kmIdentity(customerEntity.getKmIdentity())
                .address( customerEntity.getAddress().toStringAddress() )
                .distributionCenter(DistributionCenter.from(customerEntity.getDistributionCenter()))
                .build();
    }



}
