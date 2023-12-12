package com.dev.aftas.service.impl;

import com.dev.aftas.dto.fish.FishDTO;
import com.dev.aftas.dto.fish.FishResponseDTO;
import com.dev.aftas.model.Fish;
import com.dev.aftas.repository.FishRepository;
import com.dev.aftas.repository.LevelRepository;
import com.dev.aftas.service.FishService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class FishServiceImpl implements FishService {

    private final FishRepository fishRepository;
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FishServiceImpl(FishRepository fishRepository, LevelRepository levelRepository, ModelMapper modelMapper) {
        this.fishRepository = fishRepository;
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<FishResponseDTO> findAll() {
        return Arrays.asList(modelMapper.map(fishRepository.findAll(), FishResponseDTO[].class));
    }

    @Override
    public FishResponseDTO save(FishDTO fishDTO) {
        Fish fish = modelMapper.map(fishDTO, Fish.class);
        fish.setLevel(levelRepository.findById(fishDTO.getLevelId()).get());
        return modelMapper.map(fishRepository.save(fish), FishResponseDTO.class);
    }

    @Override
    public FishResponseDTO update(FishDTO fishDTO) {
        Fish fish = modelMapper.map(fishDTO, Fish.class);
        return modelMapper.map(fishRepository.save(fish), FishResponseDTO.class);
    }

    @Override
    public Boolean delete(String code) {
        if(findById(code) != null) {
            fishRepository.deleteById(code);
            return true;
        }
        return false;
    }

    @Override
    public FishResponseDTO findById(String code) {
        Fish fish = modelMapper.map(fishRepository.findById(code).orElseThrow(), Fish.class);
        return modelMapper.map(fish, FishResponseDTO.class);
    }
}
