package com.dev.aftas.service;

import com.dev.aftas.dto.hunting.HuntingDTO;
import com.dev.aftas.dto.hunting.HuntingResponseDTO;
import com.dev.aftas.model.Hunting;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface HuntingService {

    List<HuntingResponseDTO> findAll();
    HuntingResponseDTO save(HuntingDTO huntingDTO);
    HuntingResponseDTO update(HuntingDTO huntingDTO);
    Boolean delete(Integer id);
    HuntingResponseDTO findById(Integer id);

}
