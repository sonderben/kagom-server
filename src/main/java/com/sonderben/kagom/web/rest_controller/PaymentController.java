package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.dto.PaymentInfo;
import com.sonderben.kagom.entity.PaymentEntity;
import com.sonderben.kagom.entity.PaymentMethod;
import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "payments")
public class PaymentController  {

    @Autowired
    PaymentService service;


    @GetMapping("")
    public ResponseEntity<List<PaymentEntity>> getAll() {
        List<PaymentEntity> e = service.getAll();
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<PaymentEntity>getOneById(@PathVariable Long id){

        PaymentEntity e = service.getOneById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}")
    public ResponseEntity<PaymentInfo>pay(@PathVariable Long id, @RequestParam PaymentMethod pm,@RequestParam(required = false) String paymentId){
        PaymentInfo paymentInfo = service.pay(id,pm,paymentId);

        if (paymentInfo != null)
            return  new ResponseEntity<>(paymentInfo,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}