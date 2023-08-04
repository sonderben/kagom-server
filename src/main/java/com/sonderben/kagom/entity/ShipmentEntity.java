package com.sonderben.kagom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShippingEntity extends BaseEntity{
    @OneToOne
    private EmployeeEntity cashier;
    @OneToOne
    private DistributionCenter distributionOrigin;
    @OneToOne
    private DistributionCenter distributionDestination;
    @OneToOne
    private CustomerEntity receiver;
    @OneToOne
    private CustomerEntity senders;
    @OneToOne
    private PackageEntity KMPackage;
    private ShipmentsStatus status;
    private float price;
}
