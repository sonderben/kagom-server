package com.sonderben.kagom.service;

import com.sonderben.kagom.dto.Invoice;
import com.sonderben.kagom.dto.Shipment;
import com.sonderben.kagom.dto.ShipmentTracking;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.PackageEntity;
import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.entity.ShipmentsStatus;
import com.sonderben.kagom.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ShipmentService  {
    @Autowired
    ShipmentRepository repository;


    public List<ShipmentEntity> getAll() {
        return repository.findAll();
    }


    public ShipmentEntity getOneById( Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Shipment> findShipmentsBYReceiverOrSender(Long idReceiver, Long idSender){


        CustomerEntity receiver = new CustomerEntity();
        receiver.setId( idReceiver != null?idReceiver:idSender );

        List<ShipmentEntity>shipmentEntities;
        if (idReceiver!=null) {
            shipmentEntities = repository.findByReceiver(receiver);

        }
        else {
            shipmentEntities = repository.findBySender(receiver);

        }

        List<Shipment> shipments = null;
        if (shipmentEntities != null){
            shipments = new ArrayList<>(5);
            for (ShipmentEntity es : shipmentEntities){
                shipments.add( new Shipment(es) );
            }
        }
        return  shipments;
    }

    public List<Shipment> searchShipments(Boolean isPaid,Long idReceiver, Long idSender){
        if ( Objects.equals(idReceiver, idSender) ) return null;

        if (isPaid == null) {
            System.out.println("isPAid == null");
            return findShipmentsBYReceiverOrSender(idReceiver,idSender);
        }

        List<ShipmentEntity> shipmentEntities;
        if (isPaid) {
            if (idSender !=null) {
                shipmentEntities = repository.findShipmentPaidBySender(idSender);
            }else {
                shipmentEntities = repository.findShipmentPaidByReceiver(idReceiver);
            }
        }else {
            if (idSender !=null) {
                shipmentEntities = repository.findShipmentDueBySender(idSender);
            }else {
                shipmentEntities = repository.findShipmentDueByReceiver(idReceiver);
            }
        }
        if (shipmentEntities != null){
            List<Shipment> shipments;
            shipments = new ArrayList<>(5);
            for (ShipmentEntity es : shipmentEntities){
                shipments.add( new Shipment(es) );
            }
            return shipments;
        }
        return null;
    }

    public List<Shipment> findShipmentDueBySenderOrReceiver(Long idReceiver, Long idSender){
        if ( Objects.equals(idReceiver, idSender) ) return null;
        List<ShipmentEntity> shipmentEntities;
        if (idSender !=null) {
            shipmentEntities = repository.findShipmentDueBySender(idSender);
        }else {
            shipmentEntities = repository.findShipmentDueByReceiver(idReceiver);
        }
        if (shipmentEntities != null){
            List<Shipment> shipments;
            shipments = new ArrayList<>(5);
            for (ShipmentEntity es : shipmentEntities){
                shipments.add( new Shipment(es) );
            }
            return shipments;
        }
        return null;
    }



    public Invoice calculInvoice(Long idShipment){
        ShipmentEntity shipmentEntity = repository.findById(idShipment).orElse(null);
        if (shipmentEntity == null) return  null;
        return new Invoice(shipmentEntity);
    }

    public ShipmentTracking findByTrackingId(String trackingId){
        ShipmentEntity shipmentEntity = repository.findByTrackingId(trackingId);
        ShipmentTracking shipmentTracking = null;
        if (shipmentEntity != null /*&& retirado hace 2 dias*/ ){
            shipmentTracking = new ShipmentTracking(shipmentEntity);
        }
        return shipmentTracking;
    }





}