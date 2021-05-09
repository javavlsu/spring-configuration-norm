package com.company.labaforspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class ZeroController {
    @GetMapping("/index")
    public String getIndex(){
        return "hello/index";
    }
}
