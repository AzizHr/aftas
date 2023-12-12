package com.dev.aftas.controller;

import com.dev.aftas.dto.fish.FishDTO;
import com.dev.aftas.service.impl.FishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fish")
public class FishController {

    private final FishServiceImpl fishService;

    @Autowired
    public FishController(FishServiceImpl fishService) {
        this.fishService = fishService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody FishDTO fishDTO) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "fish created");
            message.put("fish", fishService.save(fishDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@RequestBody FishDTO fishDTO) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "fish updated");
            message.put("fish", fishService.save(fishDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e) {
            throw new Exception("cannot update this fish");
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String name) {
        Map<String, Object> message = new HashMap<>();

        try {
            if(fishService.delete(name)) {
                message.put("message", "A fish has been deleted");
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.put("message" ,"No fish found");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            message.put("message" ,"No fish found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Map<String, Object>> fish(@PathVariable String name) {

        Map<String, Object> message = new HashMap<>();

        try{
            message.put("message", "fish found");
            message.put("fish", fishService.findById(name));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            message.put("message", "No fish found");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> fishList() throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(fishService.findAll().isEmpty()) {
                message.put("message", "No fish found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "fish found");
            message.put("fish", fishService.findAll());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any fish");
        }
    }

}
