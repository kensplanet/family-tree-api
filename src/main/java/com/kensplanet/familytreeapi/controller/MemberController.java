package com.kensplanet.familytreeapi.controller;

import com.kensplanet.familytreeapi.model.Member;
import com.kensplanet.familytreeapi.service.ParentService;
import com.kensplanet.familytreeapi.service.MemberService;
import com.kensplanet.familytreeapi.transform.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ParentService parentService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Member> getMembers() {
        return Transformer.memberTransformer(memberService.getMembers());
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Member createOrUpdateMember(@RequestBody Member member) {
        return memberService.createOrUpdateMember(member);
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Member getMember(@PathVariable Integer personId) {
        return memberService.getMember(personId);
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer deleteMember(@PathVariable Integer personId) {
        return memberService.deleteMember(personId);
    }
}



