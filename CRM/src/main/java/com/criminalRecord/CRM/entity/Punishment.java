package com.criminalRecord.CRM.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name="punishments")
public class Punishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long punishmentId;

    @ManyToOne
    @JoinColumn(name="criminalId",nullable = false)
    private Criminal criminal;

    @Column(name="punishmentType")
    private String punishmentType;//ex - Imprisonment, Community Service

    @Column(name = "details")
    private String details;

    @Column(name = "dateAssigned")
    @Temporal(TemporalType.DATE)
    private Date dateAssigned;

    @Column(name = "durationYears")
    private Double durationYears;
}
