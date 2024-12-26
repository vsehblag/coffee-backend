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
@Table(name = "manual_condition")
public class ManualCondition {

    @Id
    @SequenceGenerator(name = "pk_manual_condition", sequenceName = "manual_condition_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_manual_condition")
    private Long id;

    @Column(name = "condition_text")
    private String conditionText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_id")
    private Sale sale;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manualCondition")
    private Set<UserManualCondition> userManualConditions;

}
