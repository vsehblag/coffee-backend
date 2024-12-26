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
@Table(name = "sales")
public class Sale {

    @Id
    @SequenceGenerator(name = "pk_sales", sequenceName = "sales_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sales")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "poster")
    private byte[] poster;

    @Column(name = "qr")
    private String qr;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale")
    private Set<Reward> rewards;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale")
    private Set<TimeRestriction> timeRestrictions;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale")
    private Set<HoursRestriction> hoursRestrictions;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale")
    private Set<ManualCondition> manualConditions;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale")
    private Set<ProgressCondition> progressConditions;
}
