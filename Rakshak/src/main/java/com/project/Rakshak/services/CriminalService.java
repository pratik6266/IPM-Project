package com.project.Rakshak.services;

import com.project.Rakshak.entities.Criminal;
import com.project.Rakshak.repositories.CriminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriminalService {

    @Autowired
    private CriminalRepository criminalRepository;

    // Method to add a criminal
    public void addCriminal(Criminal criminal) {
        criminalRepository.save(criminal);  // Save the new criminal to the repository
    }

    // Method to get all criminals
    public List<Criminal> getAllCriminals() {
        return criminalRepository.findAll();  // Retrieve all criminals from the database
    }

    // Method to search a criminal by ID
    public Optional<Criminal> searchCriminal(Long id) {
        return criminalRepository.findById(id);  // Find a criminal by their ID
    }

    // Method to find a criminal by name (if needed in future)
    public Optional<Criminal> findCriminalByName(String name) {
        return criminalRepository.findByName(name);  // Custom query to find by name (if implemented in the repo)
    }

    // Method to delete a criminal by ID
    public void deleteCriminal(Long id) {
        criminalRepository.deleteById(id);  // Delete a criminal by their ID
    }

    // Method to update criminal details
    public void updateCriminal(Criminal criminal) {
        criminalRepository.save(criminal);  // This will update the criminal if the ID exists
    }


}
