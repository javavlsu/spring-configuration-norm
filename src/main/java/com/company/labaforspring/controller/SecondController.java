package com.company.labaforspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Neil Alishev
 */
@Controller
public class SecondController {

    @GetMapping("/exit")
    public String exit() {
        return "second/exit";
    }
}
