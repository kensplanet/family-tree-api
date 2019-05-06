package com.kensplanet.familytreeapi.service;

import com.kensplanet.familytreeapi.model.Parent;
import com.kensplanet.familytreeapi.model.Member;
import com.kensplanet.familytreeapi.repository.ParentRepository;
import com.kensplanet.familytreeapi.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ParentService {

    private final static Logger logger = LoggerFactory.getLogger(ParentService.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ParentRepository parentRepository;

    public Parent addCouple(Parent parentObject) {
        logger.info("Saving couple {} in DB.", parentObject);
        Parent parent = new Parent();
        for (Member memberObject : parentObject.getMembers()) {
            Member member = memberRepository.findById(memberObject.getMemberId()).get();
            member.getSpouses().add(parent);
            parent.getMembers().add(member);
        }
        return parentRepository.save(parent);
    }

    public Member addParent(Member memberObject) {
        logger.info("Saving parent {} in DB.", memberObject);
        Parent parent = parentRepository.findById(memberObject.getParent().getParentId()).get();
        Member member = memberRepository.findById(memberObject.getMemberId()).get();
        member.setParent(parent);
        parent.getChildren().add(member);
        return memberRepository.save(member);
    }

    public List<Parent> getCouples() {
        logger.info("Getting all couples from DB.");
        return parentRepository.findAll();
    }
}
