package com.project.Rakshak.repositories;

import com.project.Rakshak.entities.UserT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserT, Integer> {

    UserT findByusername(String username);
}
