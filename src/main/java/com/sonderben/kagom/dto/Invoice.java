package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.PackageEntity;
import com.sonderben.kagom.entity.Payments;
import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.entity.ShipmentsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {


    public Invoice(ShipmentEntity shipmentEntity){
        this.status = shipmentEntity.getStatus();
        this.distributionOriginName = shipmentEntity.getDistributionOrigin().getName();
        this.distributionOriginAddress = shipmentEntity.getDistributionOrigin().getAddress().toStringAddress();
        this.fullNameSender = shipmentEntity.getSender().getFirsName() + " " +shipmentEntity.getSender().getLastName();
        this.idSender = shipmentEntity.getSender().getId();
        this.id = shipmentEntity.getId();
        this.payment = shipmentEntity.getPayments();
        this.receiveDate = shipmentEntity.getReceivedDate();
        this.invoiceItems = new ArrayList<>();
        this.totalPrice = 0;
        this.totalItbis = 0;
        for(PackageEntity p : shipmentEntity.getKMPackage()){
            this.invoiceItems.add( new InvoiceItem(p) );
            this.totalPrice +=p.getPrice();
            this.totalItbis += p.getItbis();
        }

    }

    Long id;
    ShipmentsStatus status;
    String distributionOriginName;
    String distributionOriginAddress;
    Long idSender;
    String fullNameSender;
    List<InvoiceItem> invoiceItems;
    Payments payment;
    double totalPrice;
    double totalItbis;
    Date receiveDate;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvoiceItem{
        public InvoiceItem (PackageEntity packageEntity){
            this.name = packageEntity.getName();
            this.price = packageEntity.getPrice();
            this.itbis = packageEntity.getItbis();
        }
        String name;
        double price;
        double itbis;
    }
}
