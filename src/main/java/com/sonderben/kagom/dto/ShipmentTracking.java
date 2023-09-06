package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.entity.ShipmentsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
        stepPercent = stepPercent>=1? ( (float) stepPercent/100 ) : stepPercent;
        final int SHIPMENT_LOCAL = 4;
        if (size == SHIPMENT_LOCAL){
             switch (ss) {
                 case SENT -> percent = (float) 2 /4;
                case CENTER_DISTRIBUTION ->percent = (float) 3 /4;
                case RETIRED ->percent = 1;
                default ->percent = (float) 1 /4;
            };
        }else {
             switch (ss) {
                 case SENT -> percent = (float) 2 /5;
                case CENTER_DISTRIBUTION ->percent = (float) 4 /5;
                case CUSTOMS -> percent = (float) 3 /5;
                case RETIRED -> percent =1;
                default -> percent = (float) 1 /5;
            };
        }
        final float f = percent + stepPercent;
        return f >100? percent:f;

    }



}
