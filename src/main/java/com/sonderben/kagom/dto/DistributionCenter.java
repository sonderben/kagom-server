package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.DistributionCenterEntity;
import lombok.Builder;

import java.util.UUID;

@Builder
public class DistributionCenter {
    Long id;
    String name;
    String schedule;
    String direction;

    public static DistributionCenter  from(DistributionCenterEntity dc){
        return DistributionCenter.builder()
                .id(dc.getId())
                .name(dc.getName())
                .schedule(dc.getSchedule())
                .direction(dc.getAddress().toString())
                .build();
    }
}
