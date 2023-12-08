package com.dev.aftas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

}
