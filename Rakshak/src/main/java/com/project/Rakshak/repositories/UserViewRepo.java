package com.project.Rakshak.repositories;

import com.project.Rakshak.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserViewRepo extends JpaRepository<User, Long> {

}
