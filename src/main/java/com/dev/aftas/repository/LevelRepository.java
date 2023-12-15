package com.dev.aftas.repository;

import com.dev.aftas.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Integer> {

    Optional<Level> findByCode(Integer code);

}