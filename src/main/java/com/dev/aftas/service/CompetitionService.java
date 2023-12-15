package com.dev.aftas.service;

import com.dev.aftas.dto.competition.CompetitionDTO;
import com.dev.aftas.dto.competition.CompetitionResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CompetitionService {

    List<CompetitionResponseDTO> findAll();
    CompetitionResponseDTO save(CompetitionDTO competitionDTO);
    CompetitionResponseDTO update(CompetitionDTO competitionDTO) throws Exception;
    Boolean delete(String code) throws Exception;
    CompetitionResponseDTO findByCode(String code) throws Exception;
    String generateCode(String location, LocalDate date);
    Boolean isCodeValid(String code);
    Boolean validateCompetitionDate(String competitionCode);

}
