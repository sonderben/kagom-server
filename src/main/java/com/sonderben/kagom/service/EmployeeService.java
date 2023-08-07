package com.sonderben.kagom.service;

import com.sonderben.kagom.dto.Login;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.EmployeeEntity;
import com.sonderben.kagom.entity.Role;
import com.sonderben.kagom.repository.EmployeeRepository;
import com.sonderben.kagom.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService  {
    @Autowired
    EmployeeRepository repository;



    public List<EmployeeEntity> findAll() {
        return repository.findAll();
    }

    public EmployeeEntity findFullById(Long id) {
        return  repository.findById(id).orElse(null);
    }


    public EmployeeEntity findLightById(Long id) {
        EmployeeEntity e = repository.findById(id).orElse(null);
        if (e!=null){
            e.setAddress_(e.getAddress().toStringAddress());
            e.setDistributionCenter_(e.getDistributionCenter().toStringDistribution());
            e.setAddress(null);
            e.setDistributionCenter(null);
        }
        return e;
    }

    public EmployeeEntity findById(Long id){
        return repository.findEmployee(id);
    }

    public EmployeeEntity save(EmployeeEntity em){
        return repository.save(em);
    }

    public EmployeeEntity findByEmail(String email){
        return repository.findByEmail(email).orElse(null);
    }

    public String login(Login login){
        EmployeeEntity ee = repository.findByEmail(login.getEmail()).orElse(null);
        if (ee==null) return null;
        //assert ce != null;
        if (login.getPassword().equals(ee.getPassword()))
            return Util.createToken(login.getEmail(),ee.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return null;
    }

}