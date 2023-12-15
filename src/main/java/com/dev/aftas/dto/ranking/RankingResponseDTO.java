package com.dev.aftas.dto.ranking;

import com.dev.aftas.dto.competition.CompetitionDTO;
import com.dev.aftas.dto.member.MemberDTO;
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
