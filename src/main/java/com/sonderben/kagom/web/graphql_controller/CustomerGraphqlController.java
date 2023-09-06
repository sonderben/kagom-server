package com.sonderben.kagom.web.graphql_controller;

import com.sonderben.kagom.dto.CustomerLoginResponse;
import com.sonderben.kagom.dto.Login;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerGraphqlController {
    private CustomerService customerService;
    @QueryMapping(name = "customers" )
    public List<CustomerEntity>allCustomers(){
        return customerService.findAll();
    }

    @QueryMapping(name = "customerById")
    //@PreAuthorize("hasRole('ADMIN')")
    //@Secured("ROLE_USER")
    public CustomerEntity getCustomerById(@Argument Long id){
        return customerService.findFullById(id);
    }

    @MutationMapping(name = "customerLogin")
    public CustomerLoginResponse login(@Argument Login login){
        return customerService.login(login);
    }
}
