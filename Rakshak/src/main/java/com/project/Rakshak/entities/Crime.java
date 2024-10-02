package com.project.Rakshak.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crimes")
public class Crime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crimeId;

    private String description;
    private String date;
    private String location;
    private String timeOfCrime;
    private String victimDetails;

    @ManyToOne
    @JoinColumn(name = "criminal_id", nullable = false)
    private Criminal criminal;

    public Crime(String description, String date, String location, String timeOfCrime, String victimDetails) {
        this.description = description;
        this.date = date;
        this.location = location;
        this.timeOfCrime = timeOfCrime;
        this.victimDetails = victimDetails;
    }
}
