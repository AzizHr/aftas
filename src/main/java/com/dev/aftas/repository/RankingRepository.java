package com.dev.aftas.repository;

import com.dev.aftas.model.MemberCompetitionKey;
import com.dev.aftas.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, MemberCompetitionKey> {}
