package com.dev.aftas.dto.hunting;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HuntingDTO {

    private Integer id;
    private Integer numberOfFish;
    private String competitionId;
    private String fishName;
    private Integer memberNum;

}
