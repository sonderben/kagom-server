package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.PackageEntity;
import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.service.AddressService;
import com.sonderben.kagom.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "packages")
public class PackageController  {


    @Autowired
    PackageService service;


    @GetMapping("")
    public ResponseEntity<List<PackageEntity>> getAll() {
        List<PackageEntity> e = service.getAll();
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<PackageEntity>getOneById(@PathVariable Long id){

        PackageEntity e = service.getOneById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<PackageEntity>> findPackagesByShipment(@RequestParam(name = "shipment_id") Long shipmentId){
        List<PackageEntity> packageEntity = service.findPackagesByShipment(shipmentId);
        return new ResponseEntity<>(packageEntity,HttpStatus.OK);
    }


}