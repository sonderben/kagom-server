package com.sonderben.kagom.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class ShipmentEntity extends BaseEntity{

    private Date shippingDate;
    private Date  receivedDate;
    private String info;

    @Enumerated(value = EnumType.ORDINAL)
    private ShipmentsStatus shipmentsStatus;
    private float shipmentsStatusPercent;
    private boolean isLocal = true;
    private String trackingId;

    @ManyToOne private DistributionCenterEntity distributionOrigin;
    @ManyToOne @Nonnull private DistributionCenterEntity distributionDestination;

    @ManyToOne @Nonnull private CustomerEntity receiver;
    @ManyToOne @Nonnull private CustomerEntity sender;



    @OneToMany(cascade = CascadeType.ALL) @Nonnull private List<PackageEntity> KMPackage;







    @ManyToOne private EmployeeEntity receiverEmployee,deliveryEmployee;

    @OneToOne(mappedBy = "shipment") PaymentEntity payment;



}
