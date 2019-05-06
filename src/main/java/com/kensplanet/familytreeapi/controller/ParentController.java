package com.kensplanet.familytreeapi.controller;

import com.kensplanet.familytreeapi.model.Member;
import com.kensplanet.familytreeapi.model.Parent;
import com.kensplanet.familytreeapi.service.ParentService;
import com.kensplanet.familytreeapi.transform.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/parents")
@RestController
public class ParentController {

    private final static Logger logger = LoggerFactory.getLogger(ParentController.class);

    @Autowired
    private ParentService parentService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Parent addCouple(@RequestBody Parent parent){
        logger.info("Request for adding couple {} received.", parent);
        return parentService.addCouple(parent);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Parent> getCouples() {
        logger.info("Request for getting all couples received.");
        return Transformer.parentTransformer(parentService.getCouples());
    }

    @RequestMapping(value = "/addParent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Member addParent(@RequestBody Member member) {
        logger.info("Request for adding parent {} received.", member);
        return parentService.addParent(member);
    }
}
