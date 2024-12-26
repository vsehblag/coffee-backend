package com.dealfinder.dealfindercommon.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "establishment")
public class Establishment {

    @Id
    @SequenceGenerator(name = "pk_establishment", sequenceName = "establishment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_establishment")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "social_network")
    private String socialNetwork;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private double latitude;
    
    @Column(name = "longitude")
    private double longitude;

    @Column(name = "poster")
    private byte[] poster;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "establishment")
    private Set<EstablishmentWorkingDay> workingDays;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "establishment")
    private Set<Sale> sales;

}
