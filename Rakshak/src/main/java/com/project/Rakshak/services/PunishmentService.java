package com.project.Rakshak.services;

import com.project.Rakshak.entities.Criminal;
import com.project.Rakshak.entities.PunishmentPeriod;
import com.project.Rakshak.repositories.PunishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PunishmentService {

    private final PunishmentRepository punishmentRepository;
    private final CriminalService criminalService;

    @Autowired
    public PunishmentService(PunishmentRepository punishmentRepository, CriminalService criminalService) {
        this.punishmentRepository = punishmentRepository;
        this.criminalService = criminalService;
    }

    // Get punishment by criminal ID
    public Optional<PunishmentPeriod> getPunishmentByCriminal(Criminal criminal) {
        return punishmentRepository.findByCriminal_CriminalId(criminal.getCriminalId());
    }

    public PunishmentPeriod savePunishment(PunishmentPeriod punishment) {
        return punishmentRepository.save(punishment);
    }


    // Create a new punishment period for a criminal if none exists
    public PunishmentPeriod createPunishmentForCriminal(Criminal criminal, String punishmentPeriod, String startDate, String endDate) {
        PunishmentPeriod punishment = new PunishmentPeriod();
        punishment.setCriminal(criminal);
        punishment.setPunishmentPeriod(punishmentPeriod);

        // Parse start date and handle potential parsing exceptions
        try {
            punishment.setStartDate(String.valueOf(LocalDate.parse(startDate)));
        } catch (Exception e) {
            // Handle invalid date format (you can log the error or handle it accordingly)
            throw new IllegalArgumentException("Invalid start date format");
        }

        if (endDate != null && !endDate.isEmpty()) {
            try {
                punishment.setEndDate(String.valueOf(LocalDate.parse(endDate)));
            } catch (Exception e) {
                // Handle invalid end date format
                throw new IllegalArgumentException("Invalid end date format");
            }
        }
        return punishmentRepository.save(punishment); // Save directly
    }

    // Get all punishments from the repository
    public List<PunishmentPeriod> getAllPunishments() {
        return punishmentRepository.findAll();
    }
}
