package com.dealfinder.dealfindercommon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "dealfinder_user")
public class DealFinderUser {

    @Id
    @SequenceGenerator(name = "pk_dealfinder_user", sequenceName = "dealfinder_user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_dealfinder_user")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

    @Column(name = "source")
    @Enumerated(EnumType.STRING)
    private Source source;

    @OneToMany(mappedBy = "user")
    private Set<UserSale> userSales;

    public enum Source {
        TELEGRAM, MOBILE, WEB
    }

}
