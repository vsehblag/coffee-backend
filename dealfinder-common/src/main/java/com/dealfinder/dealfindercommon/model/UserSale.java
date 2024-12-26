package com.dealfinder.dealfindercommon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user_sales")
public class UserSale {

    @Id
    @SequenceGenerator(name = "pk_user_sales", sequenceName = "user_sales_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_user_sales")
    private Long id;

    @Column(name = "completed")
    private Boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_id")
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private DealFinderUser user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userSale")
    Set<UserManualCondition> manualConditions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userSale")
    Set<UserProgressCondition> progressConditions;

}
