package com.dev.aftas.service.impl;

import com.dev.aftas.dto.member.MemberDTO;
import com.dev.aftas.dto.member.MemberResponseDTO;
import com.dev.aftas.dto.member.TopMemberDTO;
import com.dev.aftas.model.Competition;
import com.dev.aftas.model.Member;
import com.dev.aftas.repository.CompetitionRepository;
import com.dev.aftas.repository.MemberRepository;
import com.dev.aftas.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
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
    public MemberResponseDTO update(MemberDTO memberDTO) throws Exception {
        if(findByNum(memberDTO.getNum()) != null) {
            Member member = modelMapper.map(memberDTO, Member.class);
            return modelMapper.map(memberRepository.save(member), MemberResponseDTO.class);
        }
        return null;
    }

    @Override
    public Boolean delete(Integer code) throws Exception {
        if(findByNum(code) != null) {
            memberRepository.deleteById(code);
            return true;
        }
        return false;
    }

    @Override
    public MemberResponseDTO findByNum(Integer num) throws Exception {
        Member member = modelMapper.map(memberRepository.findByNum(num).orElseThrow(() -> new Exception("No member found")), Member.class);
        return modelMapper.map(member, MemberResponseDTO.class);
    }

    @Override
    public List<TopMemberDTO> getTopThree(String competitionCode) throws Exception {
        Competition competition = competitionRepository.findByCode(competitionCode)
                .orElseThrow(() -> new Exception("No competition found"));

        List<TopMemberDTO> topMembers = memberRepository.getTop3MembersInCompetition(competition.getCode());

        return topMembers.stream().limit(3).collect(Collectors.toList());
    }

}
