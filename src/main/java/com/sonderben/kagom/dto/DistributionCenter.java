package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.DistributionCenterEntity;
import com.sonderben.kagom.entity.Schedule;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public class DistributionCenter {
    Long id;
    String name;
    List <Schedule> schedules;
    String direction;

    public static DistributionCenter  from(DistributionCenterEntity dc){
        return DistributionCenter.builder()
                .id(dc.getId())
                .name(dc.getName())
                .schedules(dc.getSchedules())
                .direction(dc.getAddress().toString())
                .build();
    }
}
