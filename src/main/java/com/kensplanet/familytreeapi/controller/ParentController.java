package com.kensplanet.familytreeapi.controller;

import com.kensplanet.familytreeapi.model.Member;
import com.kensplanet.familytreeapi.model.Parent;
import com.kensplanet.familytreeapi.service.ParentService;
import com.kensplanet.familytreeapi.transform.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/parents")
@RestController
public class ParentController {

    @Autowired
    private ParentService parentService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Parent addCouple(@RequestBody Parent parent){
        return parentService.addCouple(parent);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Parent> getCouples() {
        return Transformer.parentTransformer(parentService.getCouples());
    }

    @RequestMapping(value = "/addParent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Member addParent(@RequestBody Member member) {
        return parentService.addParent(member);
    }
}
