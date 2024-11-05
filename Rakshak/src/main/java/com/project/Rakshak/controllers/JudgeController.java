package com.project.Rakshak.controllers;

import com.project.Rakshak.entities.Criminal;
import com.project.Rakshak.entities.User;
import com.project.Rakshak.services.CriminalService;
import com.project.Rakshak.services.JudService;
import com.project.Rakshak.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class JudgeController {

    @Autowired
    private CriminalService criminalService;

    @Autowired
    private JudService judService;

    @GetMapping("/view")
    public String criminalDetails(Model model) {
        List<Criminal> criminals = criminalService.getAllCriminals();
        model.addAttribute("criminals", criminals);
        return "ViewAll";
    }

    @GetMapping("/users")
    public String usersDetails(Model mod){
        List<User> users = judService.getAllUser();
        mod.addAttribute("users",users);
        return "UserAll";
    }
}
