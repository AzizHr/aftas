package com.dev.aftas.dto.hunting;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HuntingDTO {

    private Integer id;
    @NotNull(message = "Number of fish is required")
    @Min(1)
    private Integer numberOfFish;
    @NotNull(message = "Competition code is required")
    private String competitionCode;
    @NotNull(message = "Fish name is required")
    private String fishName;
    @NotNull(message = "Member num is required")
    private Integer memberNum;

}
