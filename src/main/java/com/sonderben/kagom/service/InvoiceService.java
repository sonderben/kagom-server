package com.sonderben.kagom.service;

import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.Invoices;
import com.sonderben.kagom.repository.AddressRepository;
import com.sonderben.kagom.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService  {
    @Autowired
    InvoiceRepository repository;


    public List<Invoices> getAll() {
        return repository.findAll();
    }


    public Invoices getOneById( Long id) {
        return repository.findById(id).orElse(null);
    }

}