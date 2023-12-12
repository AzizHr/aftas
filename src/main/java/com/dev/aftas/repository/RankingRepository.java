package com.dev.aftas.repository;

import com.dev.aftas.model.MemberCompetitionKey;
import com.dev.aftas.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, MemberCompetitionKey> {}
