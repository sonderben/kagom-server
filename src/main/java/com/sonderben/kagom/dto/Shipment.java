package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.PackageEntity;
import com.sonderben.kagom.entity.PaymentEntity;
import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.entity.ShipmentsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {


    public Shipment(ShipmentEntity shipmentEntity){
        this.status = shipmentEntity.getShipmentsStatus();
        this.id = shipmentEntity.getId();
        this.info = shipmentEntity.getInfo();
        this.trackingId = shipmentEntity.getTrackingId();
        this.receiveDate = shipmentEntity.getReceivedDate();
        this.sendDate = shipmentEntity.getShippingDate();
        this.sender = Customer.from(shipmentEntity.getSender());
        this.receiver = Customer.from(shipmentEntity.getReceiver());
        setCustomer(this.receiver);setCustomer(this.sender);
        this.isPaid = shipmentEntity.getPayment().getBalance() <= 0;
        this.distributionOrigin = DistributionCenter.from(shipmentEntity.getDistributionOrigin());
        this.distributionDestination = DistributionCenter.from(shipmentEntity.getDistributionDestination());
        setScheduleDistribution(this.distributionDestination);
        setScheduleDistribution(this.distributionOrigin);
    }

    Long id;
    ShipmentsStatus status;
    DistributionCenter distributionOrigin,distributionDestination;
    Customer sender,receiver;
    boolean isPaid = false;
    Date receiveDate,sendDate;
    String info, trackingId;;

    private void setCustomer(Customer customer){
        customer.setAddress(null);
        customer.setBirthday(null);
        customer.setPoints(null);
        customer.setKmIdentity(null);
        customer.setDistributionCenter(null);
    }

    private void setScheduleDistribution(DistributionCenter distributionCenter){
        distributionCenter.setSchedules(null);

    }

}
