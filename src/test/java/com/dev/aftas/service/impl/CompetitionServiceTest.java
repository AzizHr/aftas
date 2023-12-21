package com.dev.aftas.service.impl;

import com.dev.aftas.dto.competition.CompetitionDTO;
import com.dev.aftas.dto.competition.CompetitionResponseDTO;
import com.dev.aftas.dto.member.TopMemberDTO;
import com.dev.aftas.model.Competition;
import com.dev.aftas.repository.CompetitionRepository;
import com.dev.aftas.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CompetitionServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;
    @Mock
    private MemberService memberService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveTest() {

        CompetitionDTO competitionDTO = new CompetitionDTO();
        competitionDTO.setDate(LocalDate.now());
        competitionDTO.setLocation("Location");
        competitionDTO.setNumberOfParticipants(3);
        competitionDTO.setStartTime(LocalTime.of(9,0,0));

        Competition competition = new Competition();
        when(modelMapper.map(competitionDTO, Competition.class)).thenReturn(competition);
        when(competitionRepository.existsByLocationAndDate(Mockito.anyString(), Mockito.any(LocalDate.class)))
                .thenReturn(false);

        CompetitionResponseDTO responseDTO = new CompetitionResponseDTO();
        when(modelMapper.map(competitionRepository.save(competition), CompetitionResponseDTO.class))
                .thenReturn(responseDTO);

        assertEquals(responseDTO, competitionService.save(competitionDTO));
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

    @Test
    void getTop3Test() throws Exception {
        List<TopMemberDTO> topThree = new ArrayList<>();
        String code = "loc-21-12-23";

        when(memberService.getTopThree(code)).thenReturn(topThree);
        assertEquals(topThree, memberService.getTopThree(code));
    }

}
