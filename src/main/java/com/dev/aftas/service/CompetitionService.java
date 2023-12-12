package com.dev.aftas.service;

import com.dev.aftas.dto.competition.CompetitionDTO;
import com.dev.aftas.dto.competition.CompetitionResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CompetitionService {

    List<CompetitionResponseDTO> findAll();
    CompetitionResponseDTO save(CompetitionDTO competitionDTO);
    CompetitionResponseDTO update(CompetitionDTO competitionDTO);
    Boolean delete(String code);
    CompetitionResponseDTO findById(String code);

}
