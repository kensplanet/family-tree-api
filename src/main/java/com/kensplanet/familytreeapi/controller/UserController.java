package com.kensplanet.familytreeapi.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping(value = "/userContext", method = RequestMethod.GET)
    @ResponseBody
    public String getUserContext(Principal principal) {
        return principal.getName();
    }
}