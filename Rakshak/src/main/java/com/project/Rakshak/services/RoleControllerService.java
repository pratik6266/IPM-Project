package com.project.Rakshak.services;

import com.project.Rakshak.entities.UserT;
import com.project.Rakshak.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RoleControllerService {

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserT register(UserT user){
        user.setPassword(encoder.encode(user.getPassword()));
       return repo.save(user);
    }
}
