package com.sonderben.kagom.service;

import com.sonderben.kagom.dto.Login;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.EmployeeEntity;
import com.sonderben.kagom.entity.Role;
import com.sonderben.kagom.repository.CustomerRepository;
import com.sonderben.kagom.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public List<CustomerEntity> findAll() {
        return repository.findAll();
    }

    public CustomerEntity findFullById(Long id) {
        return  repository.findById(id).orElse(null);
    }
    public CustomerEntity findLightById(Long id) {
        CustomerEntity e = repository.findById(id).orElse(null);
        if (e!=null){
            e.setAddress_(e.getAddress().toStringAddress());
            e.setDistributionCenter_(e.getDistributionCenter().toStringDistribution());
            e.setInternationalAddresses_( e.getInternationalAddresses().toStringDistribution() );
            e.setAddress(null);
            e.setRoles(null);
            e.setInternationalAddresses(null);
            e.setDistributionCenter(null);
        }
        return e;
    }

    public CustomerEntity findById(Long id){
        return repository.findCustomer(id);
    }

    public CustomerEntity save(CustomerEntity entity){
        //entity.setRoles(Collections.singletonList(new Role(1L)));
        entity.setPassword( passwordEncoder.encode(entity.getPassword()) );
        return repository.save(entity);
    }

    public CustomerEntity delete(Long id){
        CustomerEntity ce = repository.findById(id).orElse(null);
            if (ce==null)return null;
         repository.delete(ce);
         return ce;
    }

    public CustomerEntity findByEmail(String email){
        return repository.findByEmail(email).orElse(null);
    }

    public String login(Login login){
        CustomerEntity ce = repository.findByEmail(login.getEmail()).orElse(null);
        System.out.println("cool: "+ce);
        if (ce==null) return null;
        //assert ce != null;
        //if (login.getPassword().equals(ce.getPassword()))
        if (passwordEncoder.matches(login.getPassword(), ce.getPassword()))
            return Util.createToken(login.getEmail(),ce.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return null;
    }

}