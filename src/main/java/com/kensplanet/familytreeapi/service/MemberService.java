package com.kensplanet.familytreeapi.service;

import com.kensplanet.familytreeapi.exception.MemberException;
import com.kensplanet.familytreeapi.model.Member;
import com.kensplanet.familytreeapi.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class MemberService {

    private final static Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getMembers() {
        logger.info("Getting all members from DB.");
        return memberRepository.findAll();
    }

    public Member getMember(Integer memberId) {
        logger.info("Getting member {} from DB.", memberId);
        Member member = null;
        try {
            member = memberRepository.findById(memberId).get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new MemberException("Member not found.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return member;
    }

    public Integer deleteMember(Integer memberId) {
        logger.info("Deleting member {} from DB.", memberId);
        memberRepository.deleteById(memberId);
        return memberId;
    }

    public Member createOrUpdateMember(Member member) {
        logger.info("Saving member {} in DB.", member);
        return memberRepository.save(member);
    }
}