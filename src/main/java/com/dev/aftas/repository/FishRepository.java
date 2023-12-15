package com.dev.aftas.repository;

import com.dev.aftas.model.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FishRepository extends JpaRepository<Fish, String> {

    Optional<Fish> findByName(String name);

}
