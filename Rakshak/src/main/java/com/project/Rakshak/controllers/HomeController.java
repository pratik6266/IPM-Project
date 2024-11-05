package com.project.Rakshak.controllers;

import com.project.Rakshak.entities.Role;
import com.project.Rakshak.entities.User;
import com.project.Rakshak.services.RoleService;
import com.project.Rakshak.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/")
    public String rakshakHome()
    {
        return "HomePage";
    }

    @GetMapping("/home/login")
    public String loginPage()
    {
        return "LoginPage";
    }

    @PostMapping("/home/login")
    public String loginDetails(@ModelAttribute User user,Model model)
    {
        User userObtained = userService.validateUser(user.getUsername(),user.getPassword());
        if(userObtained!=null)
        {
            String roleName = userObtained.getRole().getRoleName();
            return switch (roleName) {
                case "Admin" -> "redirect:/profile/admin/" + userObtained.getUsername();
                case "Jailer" -> "redirect:/profile/jailer/" + userObtained.getUsername();
                case "CBI" -> "redirect:/profile/cbi/" + userObtained.getUsername();
                case "Police" -> "redirect:/profile/police/" + userObtained.getUsername();
                case "Judge" -> "redirect:/profile/judge/" + userObtained.getUsername();
                default -> {
                    model.addAttribute("error", "Unknown role");
                    yield "LoginPage";
                }
            };
        }
        else
        {
            model.addAttribute("error","Invalid Credentials!");
            return "LoginPage";
        }
    }

    @GetMapping("/home/signup")
    public String signupPage()
    {
        return "SignupPage";
    }

    @PostMapping("/home/signup")
    public String signupDetails(@ModelAttribute User user,
                                @RequestParam("password") String password,
                                @RequestParam("confirmPassword") String confirmPassword,
                                @RequestParam("role") String roleName,
                                Model model) {

        if(!userService.validateUsername(user.getUsername()))
        {
            model.addAttribute("error", "Username already exists!");
            return "SignupPage";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords didn't match");
            return "SignupPage";
        }

        user.setPassword(passwordEncoder.encode(password));

        try {
            Optional<Role> roleOptional = roleService.getRoleByName(roleName);
            if (roleOptional.isPresent()) {
                user.setRole(roleOptional.get());
            } else {
                model.addAttribute("error", "Role not found: " + roleName);
                return "SignupPage";
            }

            userService.registerUser(user);
            model.addAttribute("message", "Registration successful! Please login.");
            return "redirect:/home/login";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "SignupPage";
        }
    }

}
