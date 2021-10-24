package com.codegenerator.core.controller;

import com.codegenerator.core.component.SeedContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/test")
public class TestController {


    @RequestMapping(method = RequestMethod.GET,path = "/getOne")
    public Long  getOne(String prefix){
        Long increase = SeedContext.getter.getAndIncrease(prefix, 1);
        return increase;
    }
}
