package com.dev.aftas.service;

import com.dev.aftas.dto.level.LevelDTO;
import com.dev.aftas.dto.level.LevelResponseDTO;
import com.dev.aftas.model.Level;
import java.util.List;
import java.util.Optional;

public interface LevelService {

    List<LevelResponseDTO> findAll();
    LevelResponseDTO save(LevelDTO levelDTO);
    LevelResponseDTO update(LevelDTO levelDTO);
    Boolean delete(Integer id);
    LevelResponseDTO findById(Integer id);

}
