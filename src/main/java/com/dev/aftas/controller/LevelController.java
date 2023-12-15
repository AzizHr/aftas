package com.dev.aftas.controller;

import com.dev.aftas.model.Level;
import com.dev.aftas.service.impl.LevelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/levels")
public class LevelController {

    private final LevelServiceImpl levelService;

    @Autowired
    public LevelController(LevelServiceImpl levelService) {
        this.levelService = levelService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody Level level) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "level created");
            message.put("level", levelService.save(level));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e) {
            throw new Exception("cannot create a new level");
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@RequestBody Level level) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "level updated");
            message.put("level", levelService.update(level));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e){
            throw new Exception("cannot update this level");
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer code) {
        Map<String, Object> message = new HashMap<>();

        try {
            if(levelService.delete(code)) {
                message.put("message", "A level has been deleted");
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.put("message" ,"No level found");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            message.put("message" ,"No level found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<Map<String, Object>> level(@PathVariable Integer code) throws Exception {

        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "level found");
            message.put("level", levelService.findById(code));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any level");
        }

    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> levels() throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(levelService.findAll().isEmpty()) {
                message.put("message", "No levels found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "levels found");
            message.put("levels", levelService.findAll());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any level");
        }
    }

}
