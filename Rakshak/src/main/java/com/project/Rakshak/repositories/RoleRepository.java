package com.project.Rakshak.repositories;

import com.project.Rakshak.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String>{

    @Query(value = "SELECT * FROM roles WHERE roles.role_name = :roleName",nativeQuery = true)
    Optional<Role> findByName(@Param("roleName") String roleName);
}
