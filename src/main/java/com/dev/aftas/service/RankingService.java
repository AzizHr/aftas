package com.dev.aftas.service;


import com.dev.aftas.dto.ranking.RankingDTO;
import com.dev.aftas.dto.ranking.RankingResponseDTO;
import com.dev.aftas.model.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankingService {

    List<RankingResponseDTO> findAll();
    RankingResponseDTO findByMemberNumAndCompetitionCode(Integer memberNum, String competitionCode) throws Exception;
    RankingResponseDTO save(RankingDTO rankingDTO);
    RankingResponseDTO update(RankingDTO rankingDTO);
    Boolean delete(Integer memberNum, String competitionCode) throws Exception;

}
