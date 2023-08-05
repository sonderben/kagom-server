package com.sonderben.kagom.service;

import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;


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
            e.setInternationalAddresses(null);
            e.setDistributionCenter(null);
        }
        return e;
    }

    public CustomerEntity findById(Long id){
        return repository.findCustomer(id);
    }

    public CustomerEntity save(CustomerEntity entity){
        return repository.save(entity);
    }

    public CustomerEntity delete(Long id){
        CustomerEntity ce = repository.findById(id).orElse(null);
            if (ce==null)return null;
         repository.delete(ce);
         return ce;
    }

}