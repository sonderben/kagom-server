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
public class DistributionCenter extends BaseEntity{
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private AddressEntity address;
    /*@OneToMany
    private List<EmployeeEntity>employee;*/
    private String email;
    private String tel;
    private String schedule;

    public static DistributionCenter getExemple(AddressEntity address/*,List<EmployeeEntity>employees*/){
        Random random = new Random();
        return DistributionCenter.builder()
                .address(address)
                .name("name: "+random.nextInt(9))
                .email("email@gmail"+random.nextInt(10)+".com")
                .tel("82345676"+random.nextInt(9))
                .schedule("lundi - vendredi 8h-14h\n samdi 9h - 2h")
               // .employee(employees)
                .build();
    }


}
