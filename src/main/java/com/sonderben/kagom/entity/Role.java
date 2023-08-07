package com.sonderben.kagom.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role extends BaseEntity{
    String name;

    public Role(Long id){
        super(id);
    }


}
