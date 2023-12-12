package com.dev.aftas.dto.fish;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FishDTO {

    private String name;
    private Double averageWeight;
    private Integer levelId;

}
