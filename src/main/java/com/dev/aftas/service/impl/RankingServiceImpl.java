package com.dev.aftas.service.impl;

import com.dev.aftas.dto.ranking.RankingDTO;
import com.dev.aftas.dto.ranking.RankingResponseDTO;
import com.dev.aftas.model.Competition;
import com.dev.aftas.model.Member;
import com.dev.aftas.model.MemberCompetitionKey;
import com.dev.aftas.model.Ranking;
import com.dev.aftas.repository.CompetitionRepository;
import com.dev.aftas.repository.MemberRepository;
import com.dev.aftas.repository.RankingRepository;
import com.dev.aftas.service.RankingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;
    private final CompetitionServiceImpl competitionService;
    private final ModelMapper modelMapper;

    @Autowired
    public RankingServiceImpl(RankingRepository rankingRepository, MemberRepository memberRepository, CompetitionRepository competitionRepository, CompetitionServiceImpl competitionService, ModelMapper modelMapper) {
        this.rankingRepository = rankingRepository;
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
        this.competitionService = competitionService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RankingResponseDTO> findAll() {
        List<Ranking> rankings = rankingRepository.findAll();

        return rankings.stream()
                .map(ranking -> {
                    RankingResponseDTO responseDTO = modelMapper.map(ranking, RankingResponseDTO.class);
                    responseDTO.setMemberNum(ranking.getId().getMemberNum());
                    responseDTO.setCompetitionCode(ranking.getId().getCompetitionCode());
                    return responseDTO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public RankingResponseDTO findById(MemberCompetitionKey id) throws Exception {
        Ranking ranking = modelMapper.map(rankingRepository.findById(id).orElseThrow(() -> new Exception("No ranking found")), Ranking.class);
        return modelMapper.map(ranking, RankingResponseDTO.class);
    }

    @Override
    public RankingResponseDTO save(RankingDTO rankingDTO) throws Exception {
        MemberCompetitionKey key = new MemberCompetitionKey();

        Optional<Member> optionalMember = memberRepository.findByNum(rankingDTO.getMemberNum());
        Optional<Competition> optionalCompetition = competitionRepository.findByCode(rankingDTO.getCompetitionCode());

        if (optionalMember.isPresent()) {
            if (optionalCompetition.isPresent()) {
                key.setMemberNum(rankingDTO.getMemberNum());
                key.setCompetitionCode(rankingDTO.getCompetitionCode());

                Ranking ranking = modelMapper.map(rankingDTO, Ranking.class);
                ranking.setId(key);
                if(competitionService.validateCompetitionDate(rankingDTO.getCompetitionCode())) {
                    ranking = rankingRepository.save(ranking);
                } else {
                    throw new Exception("Sorry, you can't compete in this one");
                }

                RankingResponseDTO responseDTO = modelMapper.map(ranking, RankingResponseDTO.class);
                responseDTO.setMemberNum(ranking.getId().getMemberNum());
                responseDTO.setCompetitionCode(ranking.getId().getCompetitionCode());

                return responseDTO;
            } else {
                throw new Exception("No competition found for code: " + rankingDTO.getCompetitionCode());
            }
        } else {
            throw new Exception("No member found for number: " + rankingDTO.getMemberNum());
        }
    }




    @Override
    public RankingResponseDTO update(RankingDTO rankingDTO) {
        Ranking ranking = modelMapper.map(rankingDTO, Ranking.class);
        return modelMapper.map(rankingRepository.save(ranking), RankingResponseDTO.class);
    }

    @Override
    public void updateRankingScore(MemberCompetitionKey id, Integer score) {
        Ranking ranking = rankingRepository.findById(id).get();
        ranking.setScore(ranking.getScore() + score);
        rankingRepository.save(ranking);
    }

    @Override
    public Boolean delete(MemberCompetitionKey id) throws Exception {
        if(findById(id) != null) {
            rankingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
