package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.entity.DistributionCenterEntity;
import com.sonderben.kagom.service.DistributionCenterService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "distribution")
public class DistributionCenterController  {

    @Autowired
    DistributionCenterService service;


    @GetMapping("")
    public ResponseEntity<List<DistributionCenterEntity>> findAll() {
        List<DistributionCenterEntity> e = service.findAll();
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<DistributionCenterEntity>findById(@PathVariable Long id){

        DistributionCenterEntity e = service.findById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<DistributionCenterEntity>save(@RequestBody DistributionCenterEntity dce){
        DistributionCenterEntity distributionCenter= service.save(dce);
        if (distributionCenter != null)
            return  new ResponseEntity<>(distributionCenter,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}