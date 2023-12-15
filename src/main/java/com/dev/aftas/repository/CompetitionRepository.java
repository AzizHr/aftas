package com.dev.aftas.repository;

import com.dev.aftas.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {

    Boolean existsByLocationAndDate(String location, LocalDate date);
    Optional<Competition> findByCode(String code);

}
