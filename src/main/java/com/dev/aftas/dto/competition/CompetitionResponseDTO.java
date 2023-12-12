package com.dev.aftas.dto.competition;

import com.dev.aftas.dto.hunting.HuntingDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CompetitionResponseDTO {

    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    private List<HuntingDTO> huntingList;

}
