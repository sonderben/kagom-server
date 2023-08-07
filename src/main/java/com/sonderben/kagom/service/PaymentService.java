package com.sonderben.kagom.service;

import com.sonderben.kagom.dto.Invoice;
import com.sonderben.kagom.dto.PaymentInfo;
import com.sonderben.kagom.entity.PaymentEntity;
import com.sonderben.kagom.entity.PaymentMethod;
import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.repository.PaymentRepository;
import com.sonderben.kagom.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService  {
    @Autowired
    PaymentRepository repository;

    @Autowired
    ShipmentRepository shipmentRepository;


    public List<PaymentEntity> getAll() {
        return repository.findAll();
    }


    public PaymentEntity getOneById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public PaymentInfo pay(Long id,PaymentMethod paymentMethod,String idMethodPayment){
        ShipmentEntity shipmentEntity = shipmentRepository.findById(id).orElse(null);
        if (shipmentEntity==null)return null;

        PaymentEntity paymentEntity=null;
        Date date =null;
        if (paymentMethod==PaymentMethod.CASH){
            date= new Date();
              paymentEntity = PaymentEntity.builder()
                     .date(date)
                     .paymentMethod(PaymentMethod.CASH)
                     .shipment(shipmentEntity)
                     .idMethodPayment(null)
                     .build();
         }
        /*
        other method
         */
        if (paymentEntity == null)
            return null;

        PaymentInfo paymentInfo = new PaymentInfo(new Invoice(shipmentEntity));
        paymentInfo.setDate(date);
        paymentInfo.setPaymentMethod(paymentMethod);

        paymentEntity.setAmount(paymentInfo.getInvoice().getTotal());





        return paymentInfo;
    }

}