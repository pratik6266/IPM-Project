package com.project.Rakshak.repositories;

import com.project.Rakshak.entities.PunishmentPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PunishmentRepository extends JpaRepository<PunishmentPeriod, Long> {
    Optional<PunishmentPeriod> findByCriminal_CriminalId(Long criminalId);
}
