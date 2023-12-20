package com.dev.aftas.controller;

import com.dev.aftas.dto.ranking.RankingDTO;
import com.dev.aftas.model.MemberCompetitionKey;
import com.dev.aftas.service.impl.RankingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/rankings")
public class RankingController {

    private final RankingServiceImpl rankingService;

    @Autowired
    public RankingController(RankingServiceImpl rankingService) {
        this.rankingService = rankingService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody RankingDTO rankingDTO) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "ranking created");
            message.put("ranking", rankingService.save(rankingDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@RequestBody RankingDTO rankingDTO) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "ranking updated");
            message.put("ranking", rankingService.update(rankingDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e){
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable MemberCompetitionKey id) {
        Map<String, Object> message = new HashMap<>();

        try {
            if(rankingService.delete(id)) {
                message.put("message", "A ranking has been deleted");
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.put("message" ,"No ranking found");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            message.put("message" ,"No ranking found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> ranking(@PathVariable MemberCompetitionKey id) {

        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "ranking found");
            message.put("ranking", rankingService.findById(id));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> rankings() {
        Map<String, Object> message = new HashMap<>();
        try{
            if(rankingService.findAll().isEmpty()) {
                message.put("message", "No rankings found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "rankings found");
            message.put("rankings", rankingService.findAll());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

}
