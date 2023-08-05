package com.sonderben.kagom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String schedule;

    public String toStringDistribution(){
        return String.format("%s %s %s %s,%s",name,email,tel,schedule,address.toStringAddress());
    }

    public static DistributionCenterEntity getExemple(AddressEntity address){
        Random random = new Random();
        return DistributionCenterEntity.builder()
                .address(address)
                .name("name: "+random.nextInt(9))
                .email("email@gmail"+random.nextInt(10)+".com")
                .tel("82345676"+random.nextInt(9))
                .schedule("lundi - vendredi 8h-14h\n samdi 9h - 2h")
               // .employee(employees)
                .build();
    }


}
