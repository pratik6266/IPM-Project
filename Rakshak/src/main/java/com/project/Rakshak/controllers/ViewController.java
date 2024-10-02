package com.project.Rakshak.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/all")
    public String viewAll(Model model)
    {
        return "ViewAll";
    }
}
