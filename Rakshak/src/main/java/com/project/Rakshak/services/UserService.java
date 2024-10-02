package com.project.Rakshak.services;

import com.project.Rakshak.entities.Role;
import com.project.Rakshak.entities.User;
import com.project.Rakshak.repositories.RoleRepository;
import com.project.Rakshak.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User validateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isPresent())
        {
            User user = userOptional.get();
            if(passwordEncoder.matches(password,user.getPassword()))
                return user;
            else
            {
                System.out.println("Passwords do not match.");
                return null;
            }
        }
        return null;
    }

    public boolean validateUsername(String username)
    {
        Optional<User> userIsThere = userRepository.findByUsername(username);
        if(userIsThere.isPresent())
            return false;
        else
            return true;
    }

    public void registerUser(User user)
    {
        try{
            userRepository.save(user);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
