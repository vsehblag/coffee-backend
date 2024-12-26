package com.dealfinder.dealfindercommon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_progress_condition")
public class UserProgressCondition {

    @Id
    @SequenceGenerator(name = "pk_user_progress_condition", sequenceName = "user_progress_condition_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_user_progress_condition")
    private Long id;

    @Column(name = "current_amount")
    private Integer currentAmount;

    @Column(name = "completed")
    private Boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sales_id")
    private UserSale userSale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "progress_condition_id")
    private ProgressCondition progressCondition;

}
