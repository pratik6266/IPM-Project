package com.project.Rakshak.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("/admin/{username}")
    public String adminProfile(@PathVariable("username") String username, Model model) {
        model.addAttribute("username", username); // Add username to model for use in the view
        return "profile/admin";
    }

    @GetMapping("/jailer/{username}")
    public String jailerProfile(@PathVariable("username") String username, Model model) {
        model.addAttribute("username", username); // Add username to model for use in the view
        return "profile/jailer";
    }

    @GetMapping("/cbi/{username}")
    public String cbiProfile(@PathVariable("username") String username, Model model) {
        model.addAttribute("username", username); // Add username to model for use in the view
        return "profile/cbi";
    }

    @GetMapping("/police/{username}")
    public String policeProfile(@PathVariable("username") String username, Model model) {
        model.addAttribute("username", username); // Add username to model for use in the view
        return "profile/police";
    }

    @GetMapping("/judge/{username}")
    public String judgeProfile(@PathVariable("username") String username, Model model) {
        model.addAttribute("username", username); // Add username to model for use in the view
        return "profile/judge";
    }
}
