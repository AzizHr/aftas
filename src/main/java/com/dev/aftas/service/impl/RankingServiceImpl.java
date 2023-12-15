package com.dev.aftas.service.impl;

import com.dev.aftas.dto.hunting.HuntingResponseDTO;
import com.dev.aftas.dto.member.MemberResponseDTO;
import com.dev.aftas.dto.ranking.RankingDTO;
import com.dev.aftas.dto.ranking.RankingResponseDTO;
import com.dev.aftas.model.Hunting;
import com.dev.aftas.model.Member;
import com.dev.aftas.model.MemberCompetitionKey;
import com.dev.aftas.model.Ranking;
import com.dev.aftas.repository.MemberRepository;
import com.dev.aftas.repository.RankingRepository;
import com.dev.aftas.service.RankingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RankingServiceImpl(RankingRepository rankingRepository, ModelMapper modelMapper) {
        this.rankingRepository = rankingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RankingResponseDTO> findAll() {
        return Arrays.asList(modelMapper.map(rankingRepository.findAll(), RankingResponseDTO[].class));
    }

    @Override
    public RankingResponseDTO findByMemberNumAndCompetitionCode(Integer memberNum, String competitionCode) throws Exception {
        Ranking ranking = modelMapper.map(rankingRepository.findByMemberNumAndCompetitionCode(memberNum, competitionCode).orElseThrow(() -> new Exception("No member found")), Ranking.class);
        return modelMapper.map(ranking, RankingResponseDTO.class);
    }

    @Override
    public RankingResponseDTO save(RankingDTO rankingDTO) {
        Ranking ranking = modelMapper.map(rankingDTO, Ranking.class);
        return modelMapper.map(rankingRepository.save(ranking), RankingResponseDTO.class);
    }

    @Override
    public RankingResponseDTO update(RankingDTO rankingDTO) {
        Ranking ranking = modelMapper.map(rankingDTO, Ranking.class);
        return modelMapper.map(rankingRepository.save(ranking), RankingResponseDTO.class);
    }

    @Override
    public Boolean delete(Integer memberNum, String competitionCode) throws Exception {
        if(findByMemberNumAndCompetitionCode(memberNum, competitionCode) != null) {
            rankingRepository.deleteByMemberNumAndCompetitionCode(memberNum, competitionCode);
            return true;
        }
        return false;
    }
}
