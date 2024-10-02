package com.project.Rakshak.services;

import com.project.Rakshak.entities.Criminal;
import com.project.Rakshak.entities.Crime;
import com.project.Rakshak.repositories.CriminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriminalService {

    private final CriminalRepository criminalRepository;

    @Autowired
    public CriminalService(CriminalRepository criminalRepository) {
        this.criminalRepository = criminalRepository;
    }

    public Criminal addCriminal(Criminal criminal) {
        return criminalRepository.save(criminal);
    }

    public Criminal getCriminalById(Long id) {
        return criminalRepository.findById(id).orElse(null);
    }

    public List<Criminal> getAllCriminals() {
        return criminalRepository.findAll();
    }

    public Criminal updateCriminal(Long id, Criminal updatedCriminal) {
        if (criminalRepository.existsById(id)) {
            updatedCriminal.setCriminalId(id);
            return criminalRepository.save(updatedCriminal);
        }
        return null;
    }

    public void deleteCriminal(Long id) {
        criminalRepository.deleteById(id);
    }

    public void addCrimeToCriminal(Long criminalId, Crime crime) {
        Criminal criminal = getCriminalById(criminalId);
        if (criminal != null) {
            criminal.getCrimes().add(crime);
            criminalRepository.save(criminal);
        }
    }
}
