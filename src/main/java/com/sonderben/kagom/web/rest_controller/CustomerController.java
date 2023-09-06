package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.dto.CustomerLoginResponse;
import com.sonderben.kagom.dto.Login;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "customers")
public class CustomerController  {

    //@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader

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
    //@PostAuthorize("hasAnyAuthority('CUSTOMER','EMPLOYEE')")
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

    @PostMapping("/signup")
    public ResponseEntity<CustomerEntity> save(@RequestBody CustomerEntity customer){
       CustomerEntity ce= service.save(customer);
       if (ce != null)
           return new ResponseEntity<>(ce,HttpStatus.OK);
       else
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CustomerEntity cus, @PathVariable Long id){
        CustomerEntity customer = service.update(cus,id);
        if (customer != null)
            return new ResponseEntity<>(customer,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerEntity>delete(@PathVariable Long id){
       CustomerEntity ce = service.delete(id);
       if (ce != null){
           return new ResponseEntity<>(ce,HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerLoginResponse> login(@RequestBody Login login){
        System.out.println("jwt: "+login);
       CustomerLoginResponse clr = service.login(login);

       if (clr != null)
           return new ResponseEntity<>(clr,HttpStatus.OK);
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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