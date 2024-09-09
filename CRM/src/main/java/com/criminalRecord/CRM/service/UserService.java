package com.criminalRecord.CRM.service;

import com.criminalRecord.CRM.entity.Role;
import com.criminalRecord.CRM.entity.User;
import com.criminalRecord.CRM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User validateUser(String username, String password, String role) {
        Optional<User> userOptional = userRepository.findByUsernameAndRole_RoleName(username,role);
        if(userOptional.isPresent())
        {
            User user = userOptional.get();
            if(passwordEncoder.matches(password,user.getPassword()))
                    return user;
            else
                    return null;
        }
        return null;
    }

    public void registerUser(User user)
    {
        Optional<Role> roleOptional = roleService.getRoleByName(user.getRole().getRoleName());
        if(roleOptional.isPresent())
        {
            user.setRole(roleOptional.get());
        }
        else {
            throw new RuntimeException("Role not found : "+user.getRole().getRoleName());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
