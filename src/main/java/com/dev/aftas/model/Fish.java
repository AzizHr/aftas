package com.dev.aftas.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

}
