package com.criminalRecord.CRM.controller;

import com.criminalRecord.CRM.entity.Role;
import com.criminalRecord.CRM.entity.User;
import com.criminalRecord.CRM.service.RoleService;
import com.criminalRecord.CRM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String home()
    {
        return "home";
    }

    @GetMapping("/signup")
    public String signup(Model model)
    {
        return "SignupPage";
    }

    @PostMapping("/signup")
    public String signupDetails(@ModelAttribute User user,@RequestParam("confirmPassword") String confirmPassword, Model model)
    {
        if(!user.getPassword().equals(confirmPassword))
        {
            model.addAttribute("error","Passwords do not match.");
            return "SignupPage";
        }

        try{
            Optional<Role> roleOptional = roleService.getRoleByName(user.getRole().getRoleName());
            if(roleOptional.isEmpty())
            {
                model.addAttribute("error","Invalid role.");
                return "SignupPage";
            }
            user.setRole(roleOptional.get());

            userService.registerUser(user);

            model.addAttribute("message","Registration successful! Please login.");
            return "redirect:/home/login";
        }
        catch(Exception e)
        {
            model.addAttribute("error","Registration failed : "+e.getMessage());
            return "SignupPage";
        }
    }

    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("user",new User());
        return "LoginPage";
    }

    @PostMapping("/login")
    public String loginDetails(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("role") String roleName, Model model) {
        // Validating user credentials
        User user = userService.validateUser(username, password, roleName);
        if (user != null) {
            return "redirect:/profile/" + user.getId();
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "LoginPage";
        }
    }

    @GetMapping("/jailer")
    public String jailerLogin(Model model)
    {
       return "";
    }

}
