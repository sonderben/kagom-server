package com.sonderben.kagom.service;

import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.Payments;
import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.repository.AddressRepository;
import com.sonderben.kagom.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService  {
    @Autowired
    PaymentRepository repository;


    public List<Payments> getAll() {
        return repository.findAll();
    }


    public Payments getOneById( Long id) {
        return repository.findById(id).orElse(null);
    }

    public Payments pay(ShipmentEntity shipmentEntity){
        return null;
    }

}