package com.kensplanet.familytreeapi.controller;

import com.kensplanet.familytreeapi.model.Member;
import com.kensplanet.familytreeapi.service.ParentService;
import com.kensplanet.familytreeapi.service.MemberService;
import com.kensplanet.familytreeapi.transform.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/members")
public class MemberController {

    private final static Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> getMembers() {
        logger.info("Request for getting all members received.");
        return new ResponseEntity<>(Transformer.memberTransformer(memberService.getMembers()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> createOrUpdateMember(@RequestBody Member member) {
        logger.info("Request for saving member {} received.", member);
        return new ResponseEntity<>(memberService.createOrUpdateMember(member), HttpStatus.OK);
    }

    @RequestMapping(value = "/{memberId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> getMember(@PathVariable Integer memberId) {
        logger.info("Request for getting member {} received.", memberId);
        return new ResponseEntity<>(memberService.getMember(memberId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{memberId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> deleteMember(@PathVariable Integer memberId) {
        logger.info("Request for deleting members {} received.", memberId);
        return new ResponseEntity<>(memberService.deleteMember(memberId), HttpStatus.OK);
    }
}



