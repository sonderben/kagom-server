package com.sonderben.kagom.entity.kmpackage;

import com.sonderben.kagom.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageEntity extends BaseEntity {

    private String description;
    private Characteristic characteristic;
    private String content;

}
