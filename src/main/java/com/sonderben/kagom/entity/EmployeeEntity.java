package com.sonderben.kagom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEntity extends KMUser{


    private JobTitle jobTitle;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Schedule> schedules;


    public EmployeeEntity(Long id,String countryIdentity, String fullName,  String email, String telephone, Date birthday,JobTitle jobTitle, String KMIdentity){
        super(id,countryIdentity,fullName,email,telephone,birthday,KMIdentity);
        this.jobTitle =  jobTitle;
    }

    public static EmployeeEntity getExemple(String email,JobTitle jobTitle){

        EmployeeEntity ee = new EmployeeEntity();
        Random random = new Random();
        ee.setPassword("!Kagom1234");
        ee.setAddress(AddressEntity.getExemple("Haïti","Lascirie # 4"));
        List<Schedule> schedule = Collections.singletonList(Schedule.builder()
                .dayOfWeek(1)
                .startHour(8.30f)
                .endHour(12.30f)
                .build());
        ee.setSchedules(schedule);
        ee.setEmail(email);
        ee.setFullName("Ben Pha");
        ee.setCountryIdentity("Gv-324843");
        ee.setJobTitle(jobTitle);
        //ee.setDistributionCenter(distribution);
        ee.setBirthday(new Date());
        ee.setDateCreated(new Date());
        ee.setKmIdentity("KMI-0A0-"+random.nextInt(99));
        ee.setTelephone("484 940 84 74");

        return ee;
    }
}
