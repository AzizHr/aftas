package com.dev.aftas.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Hunting {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer numberOfFish;
    @ManyToOne
    @JoinColumn(name = "competition_code")
    private Competition competition;
    @ManyToOne
    @JoinColumn(name = "fish_name")
    private Fish fish;
    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member member;

}
