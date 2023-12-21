package com.dev.aftas.service.impl;

import com.dev.aftas.dto.competition.CompetitionDTO;
import com.dev.aftas.dto.competition.CompetitionResponseDTO;
import com.dev.aftas.model.Competition;
import com.dev.aftas.repository.CompetitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;


import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CompetitionServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTest() {

        Competition competition = new Competition();
        when(competitionRepository.findAll()).thenReturn(Collections.singletonList(competition));

        CompetitionResponseDTO responseDTO = new CompetitionResponseDTO();
        when(modelMapper.map(competition, CompetitionResponseDTO.class)).thenReturn(responseDTO);

        assertEquals(Collections.singletonList(responseDTO), competitionService.findAll());
    }


    @Test
    void saveTest() {

        CompetitionDTO competitionDTO = new CompetitionDTO();
        Competition competition = new Competition();
        when(modelMapper.map(competitionDTO, Competition.class)).thenReturn(competition);
        when(competitionRepository.existsByLocationAndDate(Mockito.anyString(), Mockito.any(LocalDate.class)))
                .thenReturn(false);

        when(competitionService.generateCode(Mockito.anyString(), Mockito.any(LocalDate.class)))
                .thenReturn("abc-21-12-23");

        CompetitionResponseDTO responseDTO = new CompetitionResponseDTO();
        when(modelMapper.map(competitionRepository.save(competition), CompetitionResponseDTO.class))
                .thenReturn(responseDTO);

        assertEquals(responseDTO, competitionService.save(competitionDTO));
    }

    @Test
    void updateTest() throws Exception {

        CompetitionDTO competitionDTO = new CompetitionDTO();
        Competition competition = new Competition();
        when(modelMapper.map(competitionDTO, Competition.class)).thenReturn(competition);
        when(competitionRepository.findByCode(Mockito.anyString())).thenReturn(Optional.of(new Competition()));

        CompetitionResponseDTO responseDTO = new CompetitionResponseDTO();
        when(modelMapper.map(competitionRepository.save(competition), CompetitionResponseDTO.class))
                .thenReturn(responseDTO);
        assertEquals(responseDTO, competitionService.update(competitionDTO));
    }


    @Test
    void deleteTest() throws Exception {

        String code = "abc-21-12-23";
        when(competitionRepository.findByCode(code)).thenReturn(Optional.of(new Competition()));

        assertTrue(competitionService.delete(code));
    }


    @Test
    void findByCodeTest() throws Exception {

        String code = "abc-21-12-23";
        Competition competition = new Competition();
        when(competitionRepository.findByCode(code)).thenReturn(Optional.of(competition));

        CompetitionResponseDTO responseDTO = new CompetitionResponseDTO();
        when(modelMapper.map(competition, CompetitionResponseDTO.class)).thenReturn(responseDTO);

        assertEquals(responseDTO, competitionService.findByCode(code));
    }


    @Test
    void generateCodeTest() {

        String location = "Location";
        LocalDate date = LocalDate.of(2023, 12, 21);

        assertEquals("loc-21-12-23", competitionService.generateCode(location, date));
    }

    @Test
    void isCodeValidTest() {

        String validCode = "abc-21-12-23";
        String invalidCode = "invalid-code";

        assertTrue(competitionService.isCodeValid(validCode));
        assertFalse(competitionService.isCodeValid(invalidCode));
    }

    @Test
    void validateCompetitionDateTest() {

        String competitionCode = "abc-21-12-23";
        Competition competition = new Competition();
        competition.setDate(LocalDate.of(2023, 12, 22));
        when(competitionRepository.findById(competitionCode)).thenReturn(Optional.of(competition));

        assertTrue(competitionService.validateCompetitionDate(competitionCode));
    }

}
