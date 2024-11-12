package com.project.Rakshak.repositories;

import com.project.Rakshak.entities.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CriminalRepository extends JpaRepository<Criminal, Long> {

    // Find a criminal by name
    Optional<Criminal> findByName(String name);

    // Find a criminal by id (JpaRepository already provides this)
    Optional<Criminal> findById(Long id);

    // Find criminals by specific photo URLs (left, right, or front)
    Optional<Criminal> findByLeftPhotoUrl(String leftPhotoUrl);
    Optional<Criminal> findByRightPhotoUrl(String rightPhotoUrl);
    Optional<Criminal> findByFrontPhotoUrl(String frontPhotoUrl);

    // Custom query: Find criminals by crime description (case-insensitive search)
    List<Criminal> findByCrimesDescriptionContainingIgnoreCase(String description);

    // Custom query: Find criminals by place of crime (case-insensitive search)
    List<Criminal> findByCrimesLocationContainingIgnoreCase(String place);

    // Custom query: Find criminals by crime date (assuming Crime entity has a date field)
    List<Criminal> findByCrimesDate(String date);

    // Custom query: Find criminals by a combination of name, crime description, or place (OR condition)
    List<Criminal> findByNameContainingIgnoreCaseOrCrimesDescriptionContainingIgnoreCaseOrCrimesLocationContainingIgnoreCase(
            String name, String crimeDescription, String crimeLocation);

    // Example: Search by photo URLs (could be useful for advanced searches)
    List<Criminal> findByLeftPhotoUrlContainingIgnoreCaseOrRightPhotoUrlContainingIgnoreCaseOrFrontPhotoUrlContainingIgnoreCase(
            String leftPhotoUrl, String rightPhotoUrl, String frontPhotoUrl);
}
