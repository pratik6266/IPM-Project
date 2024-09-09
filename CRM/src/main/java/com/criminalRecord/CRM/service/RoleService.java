package com.criminalRecord.CRM.service;

import com.criminalRecord.CRM.entity.Role;
import com.criminalRecord.CRM.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> getRoleByName(String roleName)
    {
        return roleRepository.findByRoleName(roleName);
    }

    public Role saveRole(Role role)
    {
        return roleRepository.save(role);
    }
}
