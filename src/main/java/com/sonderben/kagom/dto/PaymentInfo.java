package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PaymentInfo {
    private Invoice invoice;
    private Date date;
    private PaymentMethod paymentMethod ;
    public PaymentInfo(Invoice invoice){
        this.invoice = invoice;
    }
}
