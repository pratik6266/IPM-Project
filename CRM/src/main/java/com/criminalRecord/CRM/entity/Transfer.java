package com.criminalRecord.CRM.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "criminalId", nullable = false)
    private Criminal criminal;

    @ManyToOne
    @JoinColumn(name = "fromJailId", nullable = false)
    private Jail fromJail;

    @ManyToOne
    @JoinColumn(name = "toJailId", nullable = false)
    private Jail toJail;

    @Column(name = "transferDate")
    @Temporal(TemporalType.DATE)
    private Date transferDate;

    @Column(name = "reason")
    private String reason;

}
