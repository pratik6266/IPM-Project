package com.project.Rakshak.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PunishmentPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String punishmentPeriod;

    private String startDate;

    private String endDate;

    // Many-to-One relationship: Each PunishmentPeriod is associated with one Criminal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criminal_id") // Column name for the foreign key
    private Criminal criminal;

    // Default constructor (no-arg constructor required by JPA)
    public PunishmentPeriod() {
    }

    // Constructor for easier instantiation
    public PunishmentPeriod(String punishmentPeriod, String startDate, String endDate, Criminal criminal) {
        this.punishmentPeriod = punishmentPeriod;
        this.startDate = startDate;
        this.endDate = endDate;
        this.criminal = criminal;
    }
}
