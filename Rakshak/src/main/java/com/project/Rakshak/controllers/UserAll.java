package com.project.Rakshak.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserAll {
    @GetMapping("/all")
    public String userAll(Model mod){
        return "UserAll";
    }
}
