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
public class CustomerEntity extends BaseEntity {
    private String firsName;
    private String lastName;
    private String password;


    @Column(columnDefinition = "varchar(30) default 'KMG'")
    private String KMIdentity="KMG";

    private String email;
    private String telephone;
    private Date birthday;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role>roles= Collections.singletonList(new Role("CUSTOMER"));

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private DistributionCenterEntity distributionCenter;

    @OneToOne
    private DistributionCenterEntity internationalAddresses;

    @Transient
    private String distributionCenter_;

    @Transient
    private String InternationalAddresses_;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "id", referencedColumnName = "id")
    private AddressEntity address;

   @Transient
   private String address_;

   public CustomerEntity(Long id, String firsName, String lastName, String email, String telephone, Date birthday, String KMIdentity){
       this.id = id;
       this.firsName = firsName;
       this.lastName = lastName;
       this.email = email;
       this.telephone = telephone;
       this.birthday = birthday;
       this.KMIdentity = KMIdentity;
   }


   public static CustomerEntity getExemple(DistributionCenterEntity distribution, AddressEntity address){
       Random random = new Random();
       return CustomerEntity.
               builder()
               .address(address)
               .password("1234")
               .firsName("first name: "+random.nextInt(10))
               .lastName("last name: "+random.nextInt(10))
               .email("user"/*+random.nextInt(10)*/)
               .telephone("3245788 "+random.nextInt(9))
               .roles( Collections.singletonList(new Role("CUSTOMER")) )
               .birthday(new Date(  ))
               .KMIdentity("KMI: "+random.nextInt(10))
               .distributionCenter(distribution)
               .internationalAddresses(distribution)
               //.address_(address.toString())
               .build();
   }


}
