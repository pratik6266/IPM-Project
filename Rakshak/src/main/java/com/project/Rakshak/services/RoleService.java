package com.project.Rakshak.services;

import com.project.Rakshak.entities.Role;
import com.project.Rakshak.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    public Optional<Role> getRoleByName(String roleName)
    {
        Optional<Role> roleObtained = roleRepo.findByName(roleName);
        return roleObtained;
    }
}
