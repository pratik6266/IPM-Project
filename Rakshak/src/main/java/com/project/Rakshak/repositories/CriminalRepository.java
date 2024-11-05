package com.project.Rakshak.repositories;

import com.project.Rakshak.entities.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriminalRepository extends JpaRepository<Criminal, Long> {

//    @Query("SELECT p from Criminal p WHERE LOWER(p.criminalId) LIKE LOWER(CONCAT('%', :keyword, '%')) ")
//    List<Criminal> searchCriminal(String keyword);

}
