package com.sonderben.kagom.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShipmentEntity extends BaseEntity{


    private Date shippingDate;
    private Date  receivedDate;


    @OneToOne
    private DistributionCenterEntity distributionOrigin;
    @OneToOne
    @Nonnull
    private DistributionCenterEntity distributionDestination;
    @OneToOne
    @Nonnull
    private CustomerEntity receiver;
    @OneToOne
    @Nonnull
    private CustomerEntity sender;
    @OneToMany(cascade = CascadeType.ALL)
    @Nonnull
    private List<PackageEntity> KMPackage;
    private ShipmentsStatus status;



}
