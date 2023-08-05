package com.sonderben.kagom.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invoices extends BaseEntity{
    @OneToOne
    @Nonnull
    private ShipmentEntity shipping;
    @OneToOne
    private EmployeeEntity senderEmployee;

    @OneToOne
    private EmployeeEntity receiptEmployee;


    public static Invoices getExemple (ShipmentEntity shipping,EmployeeEntity employee) {
        return Invoices.builder()
                .senderEmployee(employee)
                .receiptEmployee(employee)
                .shipping(shipping)
                .build();
    }
}
