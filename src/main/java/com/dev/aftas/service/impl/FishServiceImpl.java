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
    public FishResponseDTO save(FishDTO fishDTO) throws Exception {
        Fish fish = modelMapper.map(fishDTO, Fish.class);
        fish.setLevel(levelRepository.findById(fishDTO.getLevelId()).orElseThrow(() -> new Exception("No fish found")));
        return modelMapper.map(fishRepository.save(fish), FishResponseDTO.class);
    }

    @Override
    public FishResponseDTO update(FishDTO fishDTO) throws Exception {
        if(findByName(fishDTO.getName()) != null) {
            Fish fish = modelMapper.map(fishDTO, Fish.class);
            return modelMapper.map(fishRepository.save(fish), FishResponseDTO.class);
        }
        return null;
    }

    @Override
    public Boolean delete(String name) throws Exception {
        if(findByName(name) != null) {
            fishRepository.deleteById(name);
            return true;
        }
        return false;
    }

    @Override
    public FishResponseDTO findByName(String name) throws Exception {
        Fish fish = modelMapper.map(fishRepository.findById(name).orElseThrow(() -> new Exception("No fish found with this name")), Fish.class);
        return modelMapper.map(fish, FishResponseDTO.class);
    }
}
