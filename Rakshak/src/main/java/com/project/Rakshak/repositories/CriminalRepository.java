package com.project.Rakshak.repositories;

import com.project.Rakshak.entities.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalRepository extends JpaRepository<Criminal, Long> {
}
