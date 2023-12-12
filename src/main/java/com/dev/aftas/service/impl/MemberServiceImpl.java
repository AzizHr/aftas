package com.dev.aftas.service.impl;

import com.dev.aftas.dto.member.MemberDTO;
import com.dev.aftas.dto.member.MemberResponseDTO;
import com.dev.aftas.model.Member;
import com.dev.aftas.repository.MemberRepository;
import com.dev.aftas.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<MemberResponseDTO> findAll() {
        return Arrays.asList(modelMapper.map(memberRepository.findAll(), MemberResponseDTO[].class));
    }

    @Override
    public MemberResponseDTO save(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        return modelMapper.map(memberRepository.save(member), MemberResponseDTO.class);
    }

    @Override
    public MemberResponseDTO update(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        return modelMapper.map(memberRepository.save(member), MemberResponseDTO.class);
    }

    @Override
    public Boolean delete(Integer code) {
        if(findById(code) != null) {
            memberRepository.deleteById(code);
            return true;
        }
        return false;
    }

    @Override
    public MemberResponseDTO findById(Integer code) {
        Member member = modelMapper.map(memberRepository.findById(code).orElseThrow(), Member.class);
        return modelMapper.map(member, MemberResponseDTO.class);
    }
}
