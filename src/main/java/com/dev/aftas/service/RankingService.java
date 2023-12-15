package com.dev.aftas.service;


import com.dev.aftas.dto.ranking.RankingDTO;
import com.dev.aftas.dto.ranking.RankingResponseDTO;
import com.dev.aftas.model.MemberCompetitionKey;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RankingService {

    List<RankingResponseDTO> findAll();
    RankingResponseDTO findById(MemberCompetitionKey id) throws Exception;
    RankingResponseDTO save(RankingDTO rankingDTO) throws Exception;
    RankingResponseDTO update(RankingDTO rankingDTO);
    void updateRankingScore(MemberCompetitionKey id, Integer score);
    Boolean delete(MemberCompetitionKey id) throws Exception;

}
