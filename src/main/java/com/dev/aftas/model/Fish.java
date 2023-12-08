package com.dev.aftas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Fish {

    @Id
    private String name;
    private Double averageWeight;

}
