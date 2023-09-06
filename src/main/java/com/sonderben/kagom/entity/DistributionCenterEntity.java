package com.sonderben.kagom.entity;

import com.sonderben.kagom.dto.DistributionCenter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributionCenterEntity extends BaseEntity{
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private AddressEntity address;
    private String email;
    private String tel;
    private boolean isInternational;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Schedule> schedules;

    @Transient
    private String schedule;


    public String toStringDistribution(){
        return String.format("%s %s %s %s,%s",name,email,tel,schedules.toString(),address.toStringAddress());
    }

    public DistributionCenterEntity(Long id, String name, AddressEntity address, String email, String tel, boolean isInternational, List<Schedule> schedules, String schedule) {
        super(id);
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.isInternational = isInternational;
        this.schedules = schedules;
        this.schedule = schedule;
        getScheduleString();
    }



    public static DistributionCenterEntity getExemple(String name, Boolean isInternational, AddressEntity address){
        Random random = new Random();

        return DistributionCenterEntity.builder()
                .address(address)
                .name(name)
                .isInternational(isInternational == null?false:isInternational)
                .email("email@gmail"+random.nextInt(10)+".com")
                .tel("82345676"+random.nextInt(9))
                .schedules(List.of(
                        new Schedule(1,8,16),
                        new Schedule(2,8,16),
                        new Schedule(3,8,16),
                        new Schedule(4,8,16),
                        new Schedule(5,8,16),
                        new Schedule(6,9,14.30f)
                ))
               // .employee(employees)
                .build();
    }

    public void getScheduleString() {

           Map<ImmutablePair<Float,Float>, List<Schedule>> a =  schedules.stream().collect(Collectors.groupingBy(s ->
                           new ImmutablePair<>(s.getStartHour(), s.endHour)
                     ));
           StringBuilder scheduleString = new StringBuilder();
        for (Map.Entry<ImmutablePair<Float, Float>, List<Schedule>> entry : a.entrySet()) {
            ImmutablePair<Float, Float> hoursPair = entry.getKey();
            List<Schedule> schedulesForHours = entry.getValue();



            for (Schedule schedule : schedulesForHours) {
                scheduleString.append(numberToDay(schedule.getDayOfWeek()));
                scheduleString.append(", ");
            }
            final int t = scheduleString.length();
            scheduleString.delete(t-2,t);
            scheduleString.append(""+hoursPair.getLeft()+": "+hoursPair.getRight());
            scheduleString.append("\n");
        }










        this.schedule =scheduleString.toString();
    }
    private String numberToDay(int day){
        return switch (day) {
            case 1 -> "Lun";
            case 2 -> "Mar";
            case 3 -> "Mer";
            case 4 -> "Jeu";
            case 5 -> "Ven";
            case 6 -> "Sam";
            default -> "Dim";
        };
    }
}
