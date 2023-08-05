package com.sonderben.kagom.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Payments extends BaseEntity{


    private Date date;
    private double amount;
    private String method ;
    private String status ;
    @OneToOne
    @Nonnull
    private Invoices invoices;

    public static Payments getExemple(Invoices invoices){
        Random random = new Random();
        String[] method = {"PAYPALL,APPLEPAY","CASH"};
        String[] status = {"PAY,NO PAY"};
        return Payments.builder()
                .date(new Date())
                .amount(random.nextDouble(2783628))
                .method(method[method.length-1])
                .status(status[status.length-1])
                .invoices(invoices)
                .build();
    }



}
