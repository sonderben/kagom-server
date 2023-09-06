package com.sonderben.kagom.web.graphql_controller;

import com.sonderben.kagom.entity.DistributionCenterEntity;
import com.sonderben.kagom.service.DistributionCenterService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class DistributionGraphqlController {
    private DistributionCenterService service;
    @QueryMapping(name = "distributions" )
    public List<DistributionCenterEntity>allCustomers(){
        return service.findAll();
    }

    @QueryMapping(name = "distributionById")
    //@PreAuthorize("hasRole('ADMIN')")
    //@Secured("ROLE_USER")
    public DistributionCenterEntity getCustomerById(@Argument Long id){
        return service.findById(id);
    }
}
