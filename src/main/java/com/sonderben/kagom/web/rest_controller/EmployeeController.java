package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.entity.EmployeeEntity;
import com.sonderben.kagom.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "employees")
public class EmployeeController  {


    @Autowired
    EmployeeService service;


    @GetMapping("")
    public ResponseEntity<List<EmployeeEntity>> getAll() {
        List<EmployeeEntity> e = service.findAll();
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity>findById(@PathVariable Long id){

        EmployeeEntity e = service.findById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("light/{id}")
    public ResponseEntity<EmployeeEntity>findLightById(@PathVariable Long id){

        EmployeeEntity e = service.findLightById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("full/{id}")
    public ResponseEntity<EmployeeEntity>findFullById(@PathVariable Long id){

        EmployeeEntity e = service.findFullById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<EmployeeEntity> save(@RequestBody EmployeeEntity em){
        EmployeeEntity employee = service.save(em);
        if (employee != null)
            return new ResponseEntity<>(em,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}