package com.kensplanet.familytreeapi.controller;

import com.kensplanet.familytreeapi.model.Member;
import com.kensplanet.familytreeapi.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/familyTree")
public class FamilyTreeController {

    private final static Logger logger = LoggerFactory.getLogger(FamilyTreeController.class);

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Member getMember(@PathVariable Integer personId) {
        logger.info("Request for getting family tree {} received.", personId);
        return memberService.getMember(personId);
    }
}
