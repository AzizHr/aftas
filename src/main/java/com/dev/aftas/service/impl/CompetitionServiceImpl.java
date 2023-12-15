package com.dev.aftas.service.impl;

import com.dev.aftas.dto.competition.CompetitionDTO;
import com.dev.aftas.dto.competition.CompetitionResponseDTO;
import com.dev.aftas.model.Competition;
import com.dev.aftas.repository.CompetitionRepository;
import com.dev.aftas.service.CompetitionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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

        if (competitionRepository.existsByLocationAndDate(competitionDTO.getLocation(), competitionDTO.getDate())) {
            throw new RuntimeException("A competition is already exists with the same location and date.");
        }

        Competition competition = modelMapper.map(competitionDTO, Competition.class);
        String generatedCode = generateCode(competitionDTO.getLocation(), competitionDTO.getDate());

        if (isCodeValid(generatedCode)) {
            competition.setCode(generatedCode);
            return modelMapper.map(competitionRepository.save(competition), CompetitionResponseDTO.class);
        }
        return modelMapper.map(competitionRepository.save(competition), CompetitionResponseDTO.class);
    }

    @Override
    public CompetitionResponseDTO update(CompetitionDTO competitionDTO) throws Exception {
        if(findByCode(competitionDTO.getCode()) != null) {
            Competition competition = modelMapper.map(competitionDTO, Competition.class);
            return modelMapper.map(competitionRepository.save(competition), CompetitionResponseDTO.class);
        } else {
            throw new Exception("No competition found");
        }
    }

    @Override
    public Boolean delete(String code) throws Exception {
        if(findByCode(code) != null) {
            competitionRepository.deleteById(code);
            return true;
        }
        return false;
    }

    @Override
    public CompetitionResponseDTO findByCode(String code) throws Exception {
        Competition competition = modelMapper.map(competitionRepository.findByCode(code).orElseThrow(() -> new Exception("No competition found")), Competition.class);
        return modelMapper.map(competition, CompetitionResponseDTO.class);
    }

    @Override
    public String generateCode(String location, LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
        location = location.substring(0, Math.min(location.length(), 3)).toLowerCase();
        return location + "-" + formattedDate;
    }

    @Override
    public Boolean isCodeValid(String code) {
        String pattern = "^[a-z]{3}-\\d{2}-\\d{2}-\\d{2}$";
        return Pattern.matches(pattern, code);
    }


}
