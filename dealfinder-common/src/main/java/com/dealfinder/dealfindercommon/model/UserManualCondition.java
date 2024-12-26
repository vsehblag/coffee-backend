package com.dealfinder.dealfindercommon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_manual_condition")
public class UserManualCondition {

    @Id
    @SequenceGenerator(name = "pk_user_manual_condition", sequenceName = "user_manual_condition_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_user_manual_condition")
    private Long id;

    @Column(name = "completed")
    private Boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sales_id")
    private UserSale userSale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manual_condition_id")
    private ManualCondition manualCondition;

}
