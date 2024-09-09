package com.criminalRecord.CRM.repository;

import com.criminalRecord.CRM.entity.Role;
import com.criminalRecord.CRM.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //Finding a user by username and role (Excluding password)
    Optional<User> findByUsernameAndRole_RoleName(String username, String roleName);
}
