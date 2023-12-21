package com.dev.aftas.service.impl;


import com.dev.aftas.model.Level;
import com.dev.aftas.repository.LevelRepository;
import com.dev.aftas.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }


    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }

    @Override
    public Level save(Level level) throws Exception {
        List<Level> levels = findAll();

        for (int i = 0; i < levels.size(); i++) {
            if (levels.get(i).getCode() + 1 == level.getCode()) {
                if (level.getPoints() <= levels.get(i).getPoints()) {
                    throw new IllegalArgumentException("Points should be greater than " + levels.get(i).getPoints());
                } else {
                    return levelRepository.save(level);
                }
            } else if (i == levels.size() - 1) {
                throw new IllegalArgumentException("ID should be greater than " + levels.get(i).getCode() + " and less than " + (levels.get(i).getCode() + 2));
            }
        }

        return levelRepository.save(level);
    }


    @Override
    public Level update(Level level) throws Exception {

        List<Level> levels = findAll();

        if(findByCode(level.getCode()) != null) {

            for (int i = 0; i < levels.size(); i++) {
                if (levels.get(i).getCode() + 1 == level.getCode()) {
                    if (level.getPoints() <= levels.get(i).getPoints()) {
                        throw new IllegalArgumentException("Points should be greater than " + levels.get(i).getPoints());
                    } else {
                        return levelRepository.save(level);
                    }
                } else if (i == levels.size() - 1) {
                    throw new IllegalArgumentException("ID should be greater than " + levels.get(i).getCode() + " and less than " + (levels.get(i).getCode() + 2));
                }
            }
        }
        return levelRepository.save(level);
    }

    @Override
    public Boolean delete(Integer code) throws Exception {
        if(findByCode(code) != null) {
            levelRepository.deleteById(code);
            return true;
        }
        return false;
    }

    @Override
    public Level findByCode(Integer code) throws Exception {
        return levelRepository.findById(code).orElseThrow(() -> new Exception("No level found"));
    }

}
