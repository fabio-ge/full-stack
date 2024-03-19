package com.fabio.bookgiveaway.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class SecurityController {
    
    @GetMapping
    public String login(){
        return "login";
    }
}
