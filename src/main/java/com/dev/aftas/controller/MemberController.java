package com.dev.aftas.controller;

import com.dev.aftas.dto.member.MemberDTO;
import com.dev.aftas.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberServiceImpl memberService;

    @Autowired
    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody MemberDTO memberDTO) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "member created");
            message.put("member", memberService.save(memberDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e) {
            throw new Exception("cannot create a new member");
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@RequestBody MemberDTO memberDTO) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "member updated");
            message.put("member", memberService.update(memberDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e){
            throw new Exception("cannot update this member");
        }
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer num) {
        Map<String, Object> message = new HashMap<>();

        try {
            if(memberService.delete(num)) {
                message.put("message", "A member has been deleted");
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.put("message" ,"No member found");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            message.put("message" ,"No member found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{num}")
    public ResponseEntity<Map<String, Object>> member(@PathVariable Integer num) throws Exception {

        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "member found");
            message.put("member", memberService.findById(num));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any member");
        }

    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> members() throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(memberService.findAll().isEmpty()) {
                message.put("message", "No members found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "members found");
            message.put("members", memberService.findAll());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any member");
        }
    }

}
