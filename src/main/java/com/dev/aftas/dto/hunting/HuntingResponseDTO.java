package com.dev.aftas.dto.hunting;

import com.dev.aftas.dto.competition.CompetitionDTO;
import com.dev.aftas.dto.fish.FishDTO;
import com.dev.aftas.dto.member.MemberDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HuntingResponseDTO {

    private Integer id;
    private Integer numberOfFish;
    private CompetitionDTO competition;
    private FishDTO fish;
    private MemberDTO member;

}
