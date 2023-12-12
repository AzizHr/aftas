package com.dev.aftas.repository;

import com.dev.aftas.model.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, String> {}
