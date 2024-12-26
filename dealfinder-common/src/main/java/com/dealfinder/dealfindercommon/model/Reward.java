package com.dealfinder.dealfindercommon.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reward")
public class Reward {

    @Id
    @SequenceGenerator(name = "pk_reward", sequenceName = "reward_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_reward")
    private Long id;

    @Column(name = "reward_text")
    private String rewardText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_id")
    private Sale sale;

}
