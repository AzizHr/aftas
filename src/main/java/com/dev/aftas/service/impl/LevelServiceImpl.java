package com.dev.aftas.service.impl;


import com.dev.aftas.dto.level.LevelDTO;
import com.dev.aftas.dto.level.LevelResponseDTO;
import com.dev.aftas.model.Level;
import com.dev.aftas.repository.LevelRepository;
import com.dev.aftas.service.LevelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository, ModelMapper modelMapper) {
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<LevelResponseDTO> findAll() {
        return Arrays.asList(modelMapper.map(levelRepository.findAll(), LevelResponseDTO[].class));
    }

    @Override
    public LevelResponseDTO save(LevelDTO levelDTO) {
        Level level = modelMapper.map(levelDTO, Level.class);
        return modelMapper.map(levelRepository.save(level), LevelResponseDTO.class);
    }

    @Override
    public LevelResponseDTO update(LevelDTO levelDTO) {
        Level level = modelMapper.map(levelDTO, Level.class);
        return modelMapper.map(levelRepository.save(level), LevelResponseDTO.class);
    }

    @Override
    public Boolean delete(Integer code) {
        if(findById(code) != null) {
            levelRepository.deleteById(code);
            return true;
        }
        return false;
    }

    @Override
    public LevelResponseDTO findById(Integer code) {
        Level level = modelMapper.map(levelRepository.findById(code).orElseThrow(), Level.class);
        return modelMapper.map(level, LevelResponseDTO.class);
    }

}
