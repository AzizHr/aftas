package com.dev.aftas.service;

import com.dev.aftas.model.Level;
import java.util.List;

public interface LevelService {

    List<Level> findAll();
    Level save(Level level) throws Exception;
    Level update(Level level) throws Exception;
    Boolean delete(Integer code) throws Exception;
    Level findByCode(Integer code) throws Exception;

}
