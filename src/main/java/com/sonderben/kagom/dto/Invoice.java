package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.PackageEntity;
import com.sonderben.kagom.entity.PaymentEntity;
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
        this.status = shipmentEntity.getShipmentsStatus();
        this.distributionOriginName = shipmentEntity.getDistributionOrigin().getName();
        this.distributionOriginAddress = shipmentEntity.getDistributionOrigin().getAddress().toStringAddress();
        this.fullNameSender = shipmentEntity.getSender().getFullName();
        this.idSender = shipmentEntity.getSender().getId();
        this.id = shipmentEntity.getId();
        this.receiveDate = shipmentEntity.getReceivedDate();
        this.invoiceItems = new ArrayList<>();
        this.totalPrice = 0;
        this.totalItbis = 0;
        for(PackageEntity p : shipmentEntity.getKMPackage()){
            this.invoiceItems.add( new InvoiceItem(p) );
        }
        for (InvoiceItem it : invoiceItems){
            this.totalPrice += it.getTotalPrice();
            this.totalItbis += it.getTotalItbis();
        }
        this.total = totalItbis + totalPrice;

    }

    Long id;
    ShipmentsStatus status;
    String distributionOriginName;
    String distributionOriginAddress;
    Long idSender;
    String fullNameSender;
    List<InvoiceItem> invoiceItems;
    PaymentEntity payment;
    double totalPrice;
    double totalItbis;
    double total;
    Date receiveDate;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvoiceItem{
        public InvoiceItem (PackageEntity packageEntity){
            this.name = packageEntity.getName();
            this.qty = packageEntity.getQty();
            this.unitPrice = packageEntity.getPrice();
            this.itbis = packageEntity.getItbis();
            this.totalPrice = qty * unitPrice;
            this.totalItbis = qty * itbis;
            this.total = totalItbis + totalPrice;
        }
        String name;
        int qty;
        double unitPrice;
        double itbis;
        double totalPrice,totalItbis;
        double total;
    }
}
