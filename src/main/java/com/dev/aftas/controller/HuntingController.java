package com.dev.aftas.controller;

import com.dev.aftas.dto.hunting.HuntingDTO;
import com.dev.aftas.service.impl.HuntingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/huntings")
public class HuntingController {

    private final HuntingServiceImpl huntingService;

    @Autowired
    public HuntingController(HuntingServiceImpl huntingService) {
        this.huntingService = huntingService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody HuntingDTO huntingDTO) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "hunting created");
            message.put("hunting", huntingService.save(huntingDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@RequestBody HuntingDTO huntingDTO) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "hunting updated");
            message.put("hunting", huntingService.update(huntingDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e) {
            throw new Exception("cannot update this hunting");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        Map<String, Object> message = new HashMap<>();

        try {
            if(huntingService.delete(id)) {
                message.put("message", "A hunting has been deleted");
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.put("message" ,"No hunting found");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            message.put("message" ,"No hunting found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> hunting(@PathVariable Integer id) {

        Map<String, Object> message = new HashMap<>();

        try{
            message.put("message", "hunting found");
            message.put("hunting", huntingService.findById(id));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            message.put("message", "No hunting found");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> huntings() throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(huntingService.findAll().isEmpty()) {
                message.put("message", "No huntings found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "huntings found");
            message.put("huntings", huntingService.findAll());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any huntings");
        }
    }

}
