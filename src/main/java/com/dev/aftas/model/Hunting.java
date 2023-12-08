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
    @Column(name = "competition_id")
    private Competition competition;
    @ManyToOne
    @Column(name = "fish_id")
    private Fish fish;

}
