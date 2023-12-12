package com.dev.aftas.service;

import com.dev.aftas.dto.fish.FishDTO;
import com.dev.aftas.dto.fish.FishResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface FishService {

    List<FishResponseDTO> findAll();
    FishResponseDTO save(FishDTO fishDTO);
    FishResponseDTO update(FishDTO fishDTO);
    Boolean delete(String name);
    FishResponseDTO findById(String name);

}
