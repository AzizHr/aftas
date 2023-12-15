package com.dev.aftas.dto.ranking;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RankingDTO {

    @NotNull(message = "Member num is required")
    private Integer memberNum;
    @NotNull(message = "Competition code is required")
    private String competitionCode;
    @NotNull(message = "Rank is required")
    private Integer rank;
    @NotNull(message = "Score is required")
    private Integer score;

}
