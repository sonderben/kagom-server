package com.sonderben.kagom.service;

import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AddressService  {
    @Autowired
    AddressRepository repository;


    public  List<AddressEntity> getAll() {
        return repository.findAll();
    }


    public AddressEntity getOneById( Long id) {
        return repository.findById(id).orElse(null);
    }




}