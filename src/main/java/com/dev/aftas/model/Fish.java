package com.dev.aftas.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Fish {

    @Id
    private String name;
    private Double averageWeight;
    @OneToMany(mappedBy = "fish", cascade = CascadeType.REMOVE)
    private List<Hunting> huntingList;
    @ManyToOne
    @JoinColumn(name = "level_code")
    private Level level;

}
