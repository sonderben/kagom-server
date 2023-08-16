package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.entity.ShipmentsStatus;
import lombok.Data;

import java.util.List;

@Data
public class ShipmentTracking {


    //private Date shippingDate;
    private ShipmentsStatus shipmentsStatus;
    private float shipmentsStatusPercent;
    private List<ShipmentsStatus>possibleStatus;
    public ShipmentTracking(ShipmentEntity se){
        this.shipmentsStatus = se.getShipmentsStatus();


        if (se.isLocal()) {
            this.possibleStatus = List.of(
                    ShipmentsStatus.PROCESSED,
                    ShipmentsStatus.SENT,
                    ShipmentsStatus.CENTER_DISTRIBUTION,
                    ShipmentsStatus.RETIRED
            );

        }else {
            this.possibleStatus = List.of(
                    ShipmentsStatus.PROCESSED,
                    ShipmentsStatus.SENT,
                    ShipmentsStatus.CUSTOMS,
                    ShipmentsStatus.CENTER_DISTRIBUTION,
                    ShipmentsStatus.RETIRED
            );
        }
        this.shipmentsStatusPercent = getPercent(this.shipmentsStatus, possibleStatus.size(), se.getShipmentsStatusPercent());

    }

    private float getPercent(ShipmentsStatus ss,int size,float stepPercent){
        float percent;
        final int SHIPMENT_LOCAL = 4;
        if (size == SHIPMENT_LOCAL){
             switch (ss) {
                 case SENT -> percent = 33.33f;
                case CENTER_DISTRIBUTION ->percent = 66.66f;
                case RETIRED ->percent = 100;
                default ->percent = 0;
            };
        }else {
             switch (ss) {
                 case SENT -> percent = 25f;
                case CENTER_DISTRIBUTION ->percent = 50f;
                case CUSTOMS -> percent =75f;
                case RETIRED -> percent =100;
                default -> percent =0;
            };
        }
        final float f = percent + stepPercent;
        return f >100? percent:f;

    }



}
