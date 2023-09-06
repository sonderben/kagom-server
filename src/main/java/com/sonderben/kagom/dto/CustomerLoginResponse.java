package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerLoginResponse {
    private String jwt;
    private CustomerEntity customer;

    public CustomerLoginResponse(String jwt, CustomerEntity customerEntity) {
        this.jwt = jwt;
        this.customer = customerEntity;
        customer.setDateCreated(null);
        customer.setPassword(null);
    }
}
