package com.dev.aftas.dto.ranking;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RankingResponseDTO {

    private Integer memberNum;
    private String competitionCode;
    private Integer rank;
    private Integer score;

}
