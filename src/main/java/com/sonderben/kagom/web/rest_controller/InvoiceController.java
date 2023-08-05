package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.Invoices;
import com.sonderben.kagom.service.AddressService;
import com.sonderben.kagom.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "invoices")
public class InvoiceController  {


    @Autowired
    InvoiceService service;


    @GetMapping("/")
    public ResponseEntity<List<Invoices>> getAll() {
        List<Invoices> e = service.getAll();
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Invoices>getOneById(@PathVariable Long id){

        Invoices e = service.getOneById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}