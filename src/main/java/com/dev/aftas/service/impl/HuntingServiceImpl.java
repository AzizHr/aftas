package com.dev.aftas.service.impl;

import com.dev.aftas.dto.hunting.HuntingDTO;
import com.dev.aftas.dto.hunting.HuntingResponseDTO;
import com.dev.aftas.model.Hunting;
import com.dev.aftas.repository.CompetitionRepository;
import com.dev.aftas.repository.FishRepository;
import com.dev.aftas.repository.HuntingRepository;
import com.dev.aftas.repository.MemberRepository;
import com.dev.aftas.service.HuntingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final CompetitionRepository competitionRepository;
    private final FishRepository fishRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HuntingServiceImpl(HuntingRepository huntingRepository, CompetitionRepository competitionRepository, FishRepository fishRepository, MemberRepository memberRepository, ModelMapper modelMapper) {
        this.huntingRepository = huntingRepository;
        this.competitionRepository = competitionRepository;
        this.fishRepository = fishRepository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<HuntingResponseDTO> findAll() {
        return Arrays.asList(modelMapper.map(huntingRepository.findAll(), HuntingResponseDTO[].class));
    }

    @Override
    public HuntingResponseDTO save(HuntingDTO huntingDTO) {
        Hunting hunting = modelMapper.map(huntingDTO, Hunting.class);
        hunting.setCompetition(competitionRepository.findById(huntingDTO.getCompetitionId()).get());
        hunting.setFish(fishRepository.findById(huntingDTO.getFishName()).get());
        hunting.setMember(memberRepository.findById(huntingDTO.getMemberNum()).get());
        return modelMapper.map(huntingRepository.save(hunting), HuntingResponseDTO.class);
    }

    @Override
    public HuntingResponseDTO update(HuntingDTO huntingDTO) {
        Hunting hunting = modelMapper.map(huntingDTO, Hunting.class);
        hunting.setCompetition(competitionRepository.findById(huntingDTO.getCompetitionId()).get());
        hunting.setFish(fishRepository.findById(huntingDTO.getFishName()).get());
        hunting.setMember(memberRepository.findById(huntingDTO.getMemberNum()).get());
        return modelMapper.map(huntingRepository.save(hunting), HuntingResponseDTO.class);
    }

    @Override
    public Boolean delete(Integer id) {
        if(findById(id) != null) {
            huntingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public HuntingResponseDTO findById(Integer id) {
        Hunting hunting = modelMapper.map(huntingRepository.findById(id).orElseThrow(), Hunting.class);
        return modelMapper.map(hunting, HuntingResponseDTO.class);
    }

}
