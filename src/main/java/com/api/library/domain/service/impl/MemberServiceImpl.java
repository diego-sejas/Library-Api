package com.api.library.domain.service.impl;

import com.api.library.dto.MemberDTO;
import com.api.library.exception.NotFoundException;
import com.api.library.mapper.MemberMapper;
import com.api.library.domain.model.Member;
import com.api.library.domain.repository.MemberRepository;
import com.api.library.domain.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberDTO addMember(MemberDTO memberDto) {

            Member MemberEntity = memberMapper.memberDtoToMemberEntity(memberDto);
            Member savedEntity = memberRepository.save(MemberEntity);
            return memberMapper.memberEntityToMemberDto(savedEntity);

    }


    @Override
    @Transactional
    public MemberDTO updateById(MemberDTO dto, Long id) {
        Member memberEntity = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID: " + id));
        memberMapper.memberDto2EntityWithId(memberEntity, dto);
        Member entitySaved = memberRepository.save(memberEntity);
        return memberMapper.memberEntityToMemberDto(entitySaved);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundException("Member not found with id: " + id));
        member.setDeleted(true);
        memberRepository.save(member);

    }

    @Override
    public List<MemberDTO> getAll() {
        List<Member> memberList = memberRepository.findAll();
        if (memberList.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        return memberMapper.memberEntityListToMemberDtoList(memberList);
    }
}