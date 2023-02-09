package com.noticeboard.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuperController {
    @RequestMapping("/")
    public String index(){
        return "login";
    }
}
