package com.dev.aftas.repository;

import com.dev.aftas.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, String> {

    Boolean existByLocationAndDate(String location, LocalDate date);
    Optional<Competition> findByCode(String code);

}
