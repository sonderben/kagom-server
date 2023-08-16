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
        this.packages = shipmentEntity.getKMPackage();
        this.status = shipmentEntity.getShipmentsStatus();
        this.distributionOriginName = shipmentEntity.getDistributionOrigin().getName();
        this.distributionOriginAddress = shipmentEntity.getDistributionOrigin().getAddress().toStringAddress();
        this.fullNameSender = shipmentEntity.getSender().getFullName();
        this.idSender = shipmentEntity.getSender().getId();
        this.idReceiver = shipmentEntity.getReceiver().getId();
        this.id = shipmentEntity.getId();
        this.receiveDate = shipmentEntity.getReceivedDate();
    }

    Long id;
    ShipmentsStatus status;
    String distributionOriginName;
    String distributionOriginAddress;
    Long idSender,idReceiver;
    String fullNameSender;
    List<PackageEntity> packages;
    PaymentEntity payment;
    Date receiveDate;
}
