package com.dealfinder.dealfindercommon.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "progress_condition")
public class ProgressCondition {

    @Id
    @SequenceGenerator(name = "pk_progress_condition", sequenceName = "progress_condition_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_progress_condition")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "needed_amount")
    private Integer neededAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_id")
    private Sale sale;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "progressCondition")
    private Set<UserProgressCondition> userProgressConditions;

}
