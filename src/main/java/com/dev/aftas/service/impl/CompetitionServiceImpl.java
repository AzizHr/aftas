package com.dev.aftas.service.impl;

import com.dev.aftas.dto.competition.CompetitionDTO;
import com.dev.aftas.dto.competition.CompetitionResponseDTO;
import com.dev.aftas.model.Competition;
import com.dev.aftas.repository.CompetitionRepository;
import com.dev.aftas.service.CompetitionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CompetitionResponseDTO> findAll() {
        return Arrays.asList(modelMapper.map(competitionRepository.findAll(), CompetitionResponseDTO[].class));
    }

    @Override
    public CompetitionResponseDTO save(CompetitionDTO competitionDTO) {
        Competition competition = modelMapper.map(competitionDTO, Competition.class);
        return modelMapper.map(competitionRepository.save(competition), CompetitionResponseDTO.class);
    }

    @Override
    public CompetitionResponseDTO update(CompetitionDTO competitionDTO) {
        Competition competition = modelMapper.map(competitionDTO, Competition.class);
        return modelMapper.map(competitionRepository.save(competition), CompetitionResponseDTO.class);
    }

    @Override
    public Boolean delete(String code) {
        if(findById(code) != null) {
            competitionRepository.deleteById(code);
            return true;
        }
        return false;
    }

    @Override
    public CompetitionResponseDTO findById(String code) {
        Competition competition = modelMapper.map(competitionRepository.findById(code).orElseThrow(), Competition.class);
        return modelMapper.map(competition, CompetitionResponseDTO.class);
    }
}
