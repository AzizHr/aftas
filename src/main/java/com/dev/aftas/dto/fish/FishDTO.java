package com.dev.aftas.dto.fish;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FishDTO {

    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "average weight is required")
    @Min(500)
    private Double averageWeight;
    @NotNull(message = "Level ID is required")
    private Integer levelId;

}
