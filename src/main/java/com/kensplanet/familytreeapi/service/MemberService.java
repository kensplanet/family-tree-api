package com.kensplanet.familytreeapi.service;

import com.kensplanet.familytreeapi.model.Member;
import com.kensplanet.familytreeapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public Member getMember(Integer memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member.get();
    }

    public Integer deleteMember(Integer memberId) {
        memberRepository.deleteById(memberId);
        return memberId;
    }

    public Member createOrUpdateMember(Member member) {
        return memberRepository.save(member);
    }
}