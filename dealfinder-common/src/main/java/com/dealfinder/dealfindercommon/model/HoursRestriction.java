package com.dealfinder.dealfindercommon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "hours_restriction")
public class HoursRestriction {

    @Id
    @SequenceGenerator(name = "pk_hours_restriction", sequenceName = "hours_restriction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_hours_restriction")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_id")
    private Sale sale;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hoursRestriction")
    private Set<SingleDayHoursRestriction> singleDayHoursRestriction;

}
