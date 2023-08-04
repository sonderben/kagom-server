package com.sonderben.kagom.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cashier extends BaseEntity{//Employees
    private String firsName;
    private String lastName;
    private String KMIdentity;
    private String identity;

    private String jobTitle;

    private String email;
    private String telephone;
    private Date birthday;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private DistributionCenter distributionCenter;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private AddressEntity address;
}
