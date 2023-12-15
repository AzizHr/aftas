package com.dev.aftas.service;

import com.dev.aftas.dto.member.MemberDTO;
import com.dev.aftas.dto.member.MemberResponseDTO;
import com.dev.aftas.dto.member.TopMemberDTO;

import java.util.List;

public interface MemberService {

    List<MemberResponseDTO> findAll();
    MemberResponseDTO save(MemberDTO memberDTO);
    MemberResponseDTO update(MemberDTO memberDTO) throws Exception;
    Boolean delete(Integer num) throws Exception;
    MemberResponseDTO findByNum(Integer num) throws Exception;
    List<TopMemberDTO> getTopThree(String competitionCode) throws Exception;

}
