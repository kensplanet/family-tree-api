package com.kensplanet.familytreeapi.service;

import com.kensplanet.familytreeapi.exception.MemberEditException;
import com.kensplanet.familytreeapi.exception.MemberNotFoundException;
import com.kensplanet.familytreeapi.model.Member;
import com.kensplanet.familytreeapi.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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
        Member member;
        try {
            member = memberRepository.findById(memberId).get();
        } catch (NoSuchElementException noSuchElementException) {
            logger.error("Member {} not found in DB.", memberId);
            throw new MemberNotFoundException("Member not found.");
        }
        return member;
    }

    public Integer deleteMember(Integer memberId) {
        logger.info("Deleting member {} from DB.", memberId);
        try {
            memberRepository.deleteById(memberId);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            logger.error("Member {} not found in DB.", memberId);
            throw new MemberNotFoundException("Member not found.");
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            logger.error("Member {} cannot be deleted.", memberId);
            throw new MemberEditException("Member cannot be deleted.");
        }
        return memberId;
    }

    public Member createOrUpdateMember(Member member) {
        logger.info("Saving member {} in DB.", member);
        return memberRepository.save(member);
    }
}