package com.sonderben.kagom.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(unique = true,nullable = false)
    protected String email;
    protected String telephone;
    protected Date birthday;
    protected String fullName;
    protected String countryIdentity;
    @Column(updatable = false)
    protected Date dateCreated;
    @Column(nullable = false)
    protected String password;
    @Transient
    protected String distributionCenter_;

    @Transient
    protected String InternationalAddresses_;

    @Transient
    protected String address_;

    @Column(updatable = false,nullable = false)
    protected String kmIdentity;


    @ManyToMany(fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Collection<Role> roles= null;


    @ManyToOne
    private DistributionCenterEntity distributionCenter;




    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "id", referencedColumnName = "id")
    private AddressEntity address;

    public KMUser(Long id,String countryIdentity, String fullName, String email, String telephone, Date birthday, String KMIdentity){
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
        this.countryIdentity = countryIdentity;
        this.birthday = birthday;
        this.kmIdentity = KMIdentity;
    }
}
