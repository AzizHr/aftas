package com.dev.aftas.controller;

import com.dev.aftas.dto.competition.CompetitionDTO;
import com.dev.aftas.dto.competition.CompetitionResponseDTO;
import com.dev.aftas.service.impl.CompetitionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionServiceImpl competitionService;

    @Autowired
    public CompetitionController(CompetitionServiceImpl competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody CompetitionDTO competitionDTO) {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "competition created");
            message.put("competition", competitionService.save(competitionDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@RequestBody CompetitionDTO competitionDTO) {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "competition updated");
            message.put("competition", competitionService.update(competitionDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String code) {
        Map<String, Object> message = new HashMap<>();

        try {
            if(competitionService.delete(code)) {
                message.put("message", "A competition has been deleted");
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.put("message" ,"No competition found");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            message.put("message" ,"No competition found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<Map<String, Object>> competition(@PathVariable String code) {

        Map<String, Object> message = new HashMap<>();

        try{
            message.put("message", "competition found");
            message.put("competition", competitionService.findByCode(code));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            message.put("message", "No competition found");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

    }

//    @GetMapping()
//    public ResponseEntity<Map<String, Object>> competitions() throws Exception {
//        Map<String, Object> message = new HashMap<>();
//        try{
//            if(competitionService.findAll().isEmpty()) {
//                message.put("message", "No competitions found!");
//                return new ResponseEntity<>(message, HttpStatus.OK);
//            }
//            message.put("message", "competitions found");
//            message.put("competitions", competitionService.findAll());
//            return new ResponseEntity<>(message, HttpStatus.OK);
//        }catch(Exception e){
//            throw new Exception("cannot find any competition");
//        }
//    }

//    @GetMapping()
//    public ResponseEntity<Pageable> competitions(Pageable pageable) throws Exception {
//
//        return new ResponseEntity<Pageable>.ok(competitionService.findAll());
//         /*
//        Map<String, Object> message = new HashMap<>();
//        message.put("message", "competitions found");
//        message.put("competitions", competitionService.findAll(pageable));
//        return new ResponseEntity<>(message, HttpStatus.OK);
//          */
//    }

    @GetMapping
    public ResponseEntity<Page<CompetitionResponseDTO>> getCompetitions(
            Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(competitionService.findAll(pageable));
    }

}
