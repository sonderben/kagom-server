package com.sonderben.kagom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity extends BaseEntity{
    private String country;
    private String state;
    private String city;
    private String codePostal;
    private String direction;

    private String note;


    /*@OneToOne(mappedBy = "address")
    @JsonIgnore
    private CustomerEntity customer;*/



    public String toStringAddress() {
        return country+" "+state+" "+city+" "+direction;
    }

    public static AddressEntity getExemple() {

        Random random = new Random();

        String state[]= {"Artibonite","Est","Nord","Sud"};

        return AddressEntity
                .builder()
                .city("Saint-marc: "+random.nextInt(100) )
                .country("Haiti: "+random.nextInt(100))
                .codePostal("ht-"+random.nextInt(400))
                .direction("Lascirie # "+random.nextInt(40))
                .state(state[random.nextInt(state.length-1)])
                .note("some notes: "+random.nextInt())
                .build();
    }
}
