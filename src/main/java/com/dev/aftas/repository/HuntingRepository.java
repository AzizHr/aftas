package com.dev.aftas.repository;

import com.dev.aftas.model.Competition;
import com.dev.aftas.model.Fish;
import com.dev.aftas.model.Hunting;
import com.dev.aftas.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    Hunting findByFishAndMemberAndCompetition(Fish fish, Member member, Competition competition);
}
