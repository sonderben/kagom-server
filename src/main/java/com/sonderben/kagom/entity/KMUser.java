package com.sonderben.kagom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@MappedSuperclass
public class KMUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    protected Long id;
    protected String email;
    protected String telephone;
    protected Date birthday;
    protected String fullName;
    protected String countryIdentity;
    //protected String lastName;
    protected Date dateCreated = new Date();
    protected String password;
    @Transient
    protected String distributionCenter_;

    @Transient
    protected String InternationalAddresses_;

    @Transient
    protected String address_;

    @Column(columnDefinition = "varchar(30) default 'KMG'")
    protected String KMIdentity="KMG";


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles= new ArrayList<>();


    @OneToOne
    //@JoinColumn(name = "id", referencedColumnName = "id")
    private DistributionCenterEntity distributionCenter;




    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "id", referencedColumnName = "id")
    private AddressEntity address;

    public KMUser(Long id, String fullName, String email, String telephone, Date birthday, String KMIdentity){
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
        this.birthday = birthday;
        this.KMIdentity = KMIdentity;
    }
}
