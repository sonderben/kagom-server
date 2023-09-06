package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.dto.Invoice;
import com.sonderben.kagom.dto.Shipment;
import com.sonderben.kagom.dto.ShipmentTracking;
import com.sonderben.kagom.entity.PackageEntity;
import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.service.SendEmailService;
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

    @Autowired
    SendEmailService sendEmailService;


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


    @GetMapping("/search")
    public ResponseEntity< List<Shipment> > findBYReceiverOrSender(
            @RequestParam(name = "paid",required = false) boolean isPaid,
            @RequestParam(name = "receiver",required = false) Long receiver,
            @RequestParam(name = "sender",required = false) Long sender
    ){

            List<Shipment> shipments = service.searchShipments(isPaid, receiver ,sender );
            if (shipments != null){
                //sendEmailService.sendEmail();

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


    @GetMapping("/tracking/{id}")
    public ResponseEntity<ShipmentTracking> findByTrackingId(@PathVariable(name = "id") String trackingId){
        ShipmentTracking st = service.findByTrackingId(trackingId);

        if (st != null){
            return new ResponseEntity<>(st,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}