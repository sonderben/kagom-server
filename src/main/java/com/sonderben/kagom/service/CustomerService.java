package com.sonderben.kagom.service;

import com.sonderben.kagom.dto.CustomerLoginResponse;
import com.sonderben.kagom.dto.Login;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.DistributionCenterEntity;
import com.sonderben.kagom.entity.Role;
import com.sonderben.kagom.repository.CustomerRepository;
import com.sonderben.kagom.util.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
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
        Random random = new Random();

        DistributionCenterEntity ds = DistributionCenterEntity.builder().build();
        ds.setInternational(true);
        ds.setId(1L);
        entity.setInternationalAddresses(ds);
        entity.setPoints(0L);
        entity.setKmIdentity("KMI-"+random.nextInt(9)+"A"+random.nextInt(9)+"0S"+random.nextInt(9));


        entity.setPassword( passwordEncoder.encode(entity.getPassword()) );
        return repository.save(entity);
    }

    public CustomerEntity update(CustomerEntity entity,Long id){
        entity.setRoles(null);



        CustomerEntity temp = repository.findById(id).orElse(null);
        if ( temp ==null ) return null;



        entity.setId(id);
        BeanUtils.copyProperties(entity, temp, getNullPropertyNames(entity));
        if (entity.getPassword()!=null)
            temp.setPassword( passwordEncoder.encode(entity.getPassword()) );

        repository.save(temp);
       // entity.setRoles(null);
        //entity.setIsTemp(null);
        //if (entity.getPassword()!=null)
          //  entity.setPassword("************");

        return entity;
    }



    public CustomerEntity delete(Long id){
        CustomerEntity ce = repository.findById(id).orElse(null);
            if (ce==null)return null;
         repository.delete(ce);
         return ce;
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public CustomerEntity findByEmail(String email){
        return repository.findByEmail(email).orElse(null);
    }

    public CustomerLoginResponse login(Login login){
        CustomerEntity ce = repository.findByEmail(login.getEmail()).orElse(null);
        System.out.println("cool: "+ce);
        if (ce==null) return null;

        if (passwordEncoder.matches(login.getPassword(), ce.getPassword())) {
            String jwt = Util.createToken(login.getEmail(),
                    ce.getRoles().stream().map(Role::getName).collect(Collectors.toList()), true);
            if (jwt != null) {

                ce.getDistributionCenter().getScheduleString();
                ce.getDistributionCenter().setSchedules(null);



                return new CustomerLoginResponse(jwt, ce);

            }
        }

        return null;
    }

}