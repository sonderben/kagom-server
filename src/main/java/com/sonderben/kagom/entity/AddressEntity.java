package com.sonderben.kagom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address extends BaseEntity{
    private String country;
    private String state;
    private String City;
    private String CodePostal;
    private String direction;

    private String note;


    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private CustomerEntity customer;
}
