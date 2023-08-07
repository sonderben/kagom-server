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
public class EmployeeEntity extends BaseEntity{
    private String firsName;
    private String lastName;
    private String KMIdentity;

    private String password;

    private String jobTitle;

    private String email;
    private String telephone;
    private Date birthday;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private DistributionCenterEntity distributionCenter;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private AddressEntity address;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = Collections.singletonList(new Role("EMPLOYEE"));

    @Transient
    private String address_;
    @Transient
    private String distributionCenter_;
    public EmployeeEntity(Long id, String firsName, String lastName, String email, String telephone, Date birthday,String jobTitle, String KMIdentity){
        this.id = id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle =  jobTitle;
        this.telephone = telephone;
        this.birthday = birthday;
        this.KMIdentity = KMIdentity;
    }

    public static EmployeeEntity getExemple(DistributionCenterEntity distribution, AddressEntity address){
        Random random = new Random();
        return EmployeeEntity.
                builder()
                .address(address)
                .password("1234")
                .firsName("first name: "+random.nextInt(10))
                .lastName("last name: "+random.nextInt(10))
                .email("user"/*+random.nextInt(10)*/)
                .telephone("3245788 "+random.nextInt(9))
                .roles( Collections.singletonList(new Role("EMPLOYEE")) )
                .birthday(new Date(random.nextLong(1204482478)))
                .jobTitle("cashier")
                .KMIdentity("KMI: "+random.nextInt(10))
                .distributionCenter(distribution)
                .build();
    }
}
