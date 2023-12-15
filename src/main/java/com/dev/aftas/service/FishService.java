package com.dev.aftas.service;

import com.dev.aftas.dto.fish.FishDTO;
import com.dev.aftas.dto.fish.FishResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface FishService {

    List<FishResponseDTO> findAll();
    FishResponseDTO save(FishDTO fishDTO) throws Exception;
    FishResponseDTO update(FishDTO fishDTO) throws Exception;
    Boolean delete(String name) throws Exception;
    FishResponseDTO findByName(String name) throws Exception;

}
