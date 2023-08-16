package com.sonderben.kagom.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Range;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule extends BaseEntity{
    int dayOfWeek;
    float startHour,endHour;

}
