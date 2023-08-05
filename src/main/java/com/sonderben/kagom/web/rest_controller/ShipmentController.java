package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.dto.Invoice;
import com.sonderben.kagom.dto.Shipment;
import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "shipments")
public class ShipmentController  {

    @Autowired
    ShipmentService service;


    @GetMapping("/")
    public ResponseEntity<List<ShipmentEntity>> getAll() {
        List<ShipmentEntity> e = service.getAll();
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ShipmentEntity>getOneById(@PathVariable Long id){

        ShipmentEntity e = service.getOneById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("")
    public ResponseEntity< List<Shipment> > findBYReceiverOrSender(
            @RequestParam(name = "receiver",required = false) Long receiver,
            @RequestParam(name = "sender",required = false) Long sender
    ){

            List<Shipment> shipments = service.findBYReceiverOrSender( receiver ,sender );
            if (shipments != null){
                return new ResponseEntity<>( shipments,HttpStatus.OK);
            }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);



    }

    @GetMapping("/{id}/invoice")
    public ResponseEntity<Invoice> calculateInvoice(@PathVariable Long id){

        Invoice e = service.calculInvoice(id);

        if (e != null){
            return new ResponseEntity<>(e,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}