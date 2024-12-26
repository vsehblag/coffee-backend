package com.dealfinder.dealfindercommon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "single_day_hours_restriction")
public class SingleDayHoursRestriction {

    @Id
    @SequenceGenerator(name = "pk_single_day_hours_restriction", sequenceName = "single_day_hours_restriction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_single_day_hours_restriction")
    private Long id;

    @Column(name = "start_hour")
    private Integer startHour;

    @Column(name = "end_hour")
    private Integer endHour;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hours_restriction_id")
    private HoursRestriction hoursRestriction;

}
