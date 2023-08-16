package com.sonderben.kagom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributionCenterEntity extends BaseEntity{
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private AddressEntity address;
    private String email;
    private String tel;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    public String toStringDistribution(){
        return String.format("%s %s %s %s,%s",name,email,tel,schedules.toString(),address.toStringAddress());
    }

    public static DistributionCenterEntity getExemple(AddressEntity address){
        Random random = new Random();

        return DistributionCenterEntity.builder()
                .address(address)
                .name("name: "+random.nextInt(9))
                .email("email@gmail"+random.nextInt(10)+".com")
                .tel("82345676"+random.nextInt(9))
                .schedules(List.of(
                        new Schedule(1,8,16),
                        new Schedule(2,8,16),
                        new Schedule(3,8,16),
                        new Schedule(4,8,16),
                        new Schedule(5,8,16),
                        new Schedule(1,9,14.30f)
                ))
               // .employee(employees)
                .build();
    }


}
