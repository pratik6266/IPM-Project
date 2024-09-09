package com.criminalRecord.CRM.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "jails")
public class Jail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "capacity")
    private Integer capacity;

    @OneToMany(mappedBy = "fromJail")
    private List<Transfer> outgoingTransfers;

    @OneToMany(mappedBy = "toJail")
    private List<Transfer> incomingTransfers;

    @OneToMany(mappedBy = "jailName")
    private List<Criminal> criminals;

}
