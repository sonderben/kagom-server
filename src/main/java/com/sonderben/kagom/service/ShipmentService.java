package com.sonderben.kagom.service;

import com.sonderben.kagom.dto.Invoice;
import com.sonderben.kagom.dto.Shipment;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Shipment> findBYReceiverOrSender(Long idReceiver, Long idSender){
        if (idReceiver==null && idSender==null) return null;

        CustomerEntity receiver = CustomerEntity.builder().build();
        receiver.setId( idReceiver != null?idReceiver:idSender );

        List<ShipmentEntity>shipmentEntities;
        if (idReceiver!=null) {
            shipmentEntities = repository.findByReceiver(receiver);
            System.out.println("findByReceiver");
        }
        else {
            shipmentEntities = repository.findBySender(receiver);
            System.out.println("findBySender");
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

    public Invoice calculInvoice(Long idShipment){
        ShipmentEntity shipmentEntity = repository.findById(idShipment).orElse(null);
        if (shipmentEntity == null) return  null;
        return new Invoice(shipmentEntity);
    }



}