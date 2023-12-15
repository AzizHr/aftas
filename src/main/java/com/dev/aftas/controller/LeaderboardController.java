package com.dev.aftas.controller;

import com.dev.aftas.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final MemberServiceImpl memberService;

    @Autowired
    public LeaderboardController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/top-three/{competitionCode}")
    public ResponseEntity<Map<String, Object>> topThree(@PathVariable String competitionCode) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(memberService.getTopThree(competitionCode).isEmpty()) {
                message.put("message", "No top 3 found!");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
            message.put("message", "top 3 found");
            message.put("top 3", memberService.getTopThree(competitionCode));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
