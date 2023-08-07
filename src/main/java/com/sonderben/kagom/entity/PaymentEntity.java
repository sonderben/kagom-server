package com.sonderben.kagom.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Random;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity extends BaseEntity{


    private Date date;
    private double amount;
    private PaymentMethod paymentMethod ;
    private String idMethodPayment ;
    @OneToOne
    @Nonnull
    private ShipmentEntity shipment;

    public static PaymentEntity getExemple(ShipmentEntity shipment){
        Random random = new Random();

        return PaymentEntity.builder()
                .date(new Date())
                .amount(random.nextDouble(2783628))
                .paymentMethod(PaymentMethod.CARD )
                .shipment(shipment)
                .build();
    }



}
