package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.Payments;
import com.sonderben.kagom.service.AddressService;
import com.sonderben.kagom.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "payments")
public class PaymentController  {

    @Autowired
    PaymentService service;


    @GetMapping("")
    public ResponseEntity<List<Payments>> getAll() {
        List<Payments> e = service.getAll();
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Payments>getOneById(@PathVariable Long id){

        Payments e = service.getOneById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}