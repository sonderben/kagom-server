package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.dto.Login;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.Role;
import com.sonderben.kagom.service.CustomerService;
import com.sonderben.kagom.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "customers")
public class CustomerController  {


    @Autowired
    CustomerService service;


    @GetMapping("")
    public ResponseEntity< List<CustomerEntity> > getAll() {
        List<CustomerEntity> e = service.findAll();
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity>findById(@PathVariable Long id){

        CustomerEntity e = service.findById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/light/{id}")
    public ResponseEntity<CustomerEntity>findLightById(@PathVariable Long id){

        CustomerEntity e = service.findLightById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/full/{id}")
    public ResponseEntity<CustomerEntity>findFullById(@PathVariable Long id){

        CustomerEntity e = service.findFullById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<CustomerEntity> save(@RequestBody CustomerEntity customer){
       CustomerEntity ce= service.save(customer);
       if (ce != null)
           return new ResponseEntity<>(ce,HttpStatus.OK);
       else
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerEntity>delete(@PathVariable Long id){
       CustomerEntity ce = service.delete(id);
       if (ce != null){
           return new ResponseEntity<>(ce,HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){
       String jwt = service.login(login);
       if (jwt != null)
           return new ResponseEntity<>(jwt+" m mal jere jwt a",HttpStatus.OK);
       return new ResponseEntity<>("m pa jween ni",HttpStatus.NOT_FOUND);

    }




   /* @PutMapping("")
    public ResponseEntity<CustomerEntity>save(@RequestBody  CustomerEntity entity){
        return null;
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerEntity>update(@PathVariable Long id){
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerEntity>delete(@PathVariable Long id){
        return null;
    }*/



}