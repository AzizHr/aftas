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
    HuntingResponseDTO save(HuntingDTO huntingDTO) throws Exception;
    HuntingResponseDTO update(HuntingDTO huntingDTO) throws Exception;
    Boolean delete(Integer id) throws Exception;
    HuntingResponseDTO findById(Integer id) throws Exception;

}
