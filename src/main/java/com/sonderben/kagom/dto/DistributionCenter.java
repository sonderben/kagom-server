package com.sonderben.kagom.dto;

import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.DistributionCenterEntity;
import com.sonderben.kagom.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributionCenter {
    Long id;
    String name;
    List <Schedule> schedules;
    String direction;
    AddressEntity address;

    public static DistributionCenter  from(DistributionCenterEntity dc){
        return DistributionCenter.builder()
                .id(dc.getId())
                .name(dc.getName())
                .schedules(dc.getSchedules())
                .direction(dc.getAddress().toStringAddress())
                .address(dc.getAddress())
                .build();
    }
}
