package com.dev.aftas.service.impl;

import com.dev.aftas.dto.hunting.HuntingDTO;
import com.dev.aftas.dto.hunting.HuntingResponseDTO;
import com.dev.aftas.model.Fish;
import com.dev.aftas.model.Hunting;
import com.dev.aftas.model.Level;
import com.dev.aftas.model.MemberCompetitionKey;
import com.dev.aftas.repository.CompetitionRepository;
import com.dev.aftas.repository.FishRepository;
import com.dev.aftas.repository.HuntingRepository;
import com.dev.aftas.repository.MemberRepository;
import com.dev.aftas.service.HuntingService;
import com.dev.aftas.service.RankingService;
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
    private final RankingServiceImpl rankingService;
    private final ModelMapper modelMapper;

    @Autowired
    public HuntingServiceImpl(HuntingRepository huntingRepository, CompetitionRepository competitionRepository, FishRepository fishRepository, MemberRepository memberRepository, RankingServiceImpl rankingService, ModelMapper modelMapper) {
        this.huntingRepository = huntingRepository;
        this.competitionRepository = competitionRepository;
        this.fishRepository = fishRepository;
        this.memberRepository = memberRepository;
        this.rankingService = rankingService;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<HuntingResponseDTO> findAll() {
        return Arrays.asList(modelMapper.map(huntingRepository.findAll(), HuntingResponseDTO[].class));
    }

    @Override
    public Integer save(HuntingDTO huntingDTO) throws Exception {

        Fish fish = fishRepository.findByName(huntingDTO.getFishName())
                .orElseThrow(() -> new RuntimeException("Fish not found with name: " + huntingDTO.getFishName()));

        Hunting existingHunting = huntingRepository.findByFishAndMemberAndCompetition(
                fish,
                memberRepository.findByNum(huntingDTO.getMemberNum())
                        .orElseThrow(() -> new RuntimeException("Member not found with number: " + huntingDTO.getMemberNum())),
                competitionRepository.findByCode(huntingDTO.getCompetitionCode())
                        .orElseThrow(() -> new RuntimeException("Competition not found with code: " + huntingDTO.getCompetitionCode()))
        );

        int points = fish.getLevel().getPoints();
        int score = huntingDTO.getNumberOfFish() * points;

        if (existingHunting != null) {

            existingHunting.setNumberOfFish(existingHunting.getNumberOfFish() + huntingDTO.getNumberOfFish());
            huntingRepository.save(existingHunting);
        } else {

            Hunting newHunting = modelMapper.map(huntingDTO, Hunting.class);
            newHunting.setNumberOfFish(huntingDTO.getNumberOfFish());
            newHunting.setFish(fish);
            newHunting.setMember(memberRepository.findByNum(huntingDTO.getMemberNum())
                    .orElseThrow(() -> new RuntimeException("Member not found with number: " + huntingDTO.getMemberNum())));
            newHunting.setCompetition(competitionRepository.findByCode(huntingDTO.getCompetitionCode())
                    .orElseThrow(() -> new RuntimeException("Competition not found with code: " + huntingDTO.getCompetitionCode())));
            huntingRepository.save(newHunting);
        }

        MemberCompetitionKey id = new MemberCompetitionKey();
        id.setMemberNum(huntingDTO.getMemberNum());
        id.setCompetitionCode(huntingDTO.getCompetitionCode());
        rankingService.updateRankingScore(id, score);

        return score;
    }


    @Override
    public HuntingResponseDTO update(HuntingDTO huntingDTO) throws Exception {
        Hunting hunting = modelMapper.map(huntingDTO, Hunting.class);
        hunting.setCompetition(competitionRepository.findById(huntingDTO.getCompetitionCode()).orElseThrow(() -> new Exception("No competition found")));
        hunting.setFish(fishRepository.findById(huntingDTO.getFishName()).orElseThrow(() -> new Exception("No fish found")));
        hunting.setMember(memberRepository.findById(huntingDTO.getMemberNum()).orElseThrow(() -> new Exception("No member found")));
        return modelMapper.map(huntingRepository.save(hunting), HuntingResponseDTO.class);
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        if(findById(id) != null) {
            huntingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public HuntingResponseDTO findById(Integer id) throws Exception {
        Hunting hunting = modelMapper.map(huntingRepository.findById(id).orElseThrow(() -> new Exception("No hunting found")), Hunting.class);
        return modelMapper.map(hunting, HuntingResponseDTO.class);
    }

}
