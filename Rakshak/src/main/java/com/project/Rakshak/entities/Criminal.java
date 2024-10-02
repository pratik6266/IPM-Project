package com.project.Rakshak.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "criminals")
public class Criminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long criminalId;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] leftPhoto;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] rightPhoto;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] frontPhoto;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] retinaScan;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] fingerprintScan;

    @OneToMany(mappedBy = "criminal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Crime> crimes = new ArrayList<>(); // Initialize here

    public void addCrime(Crime crime) {
        crimes.add(crime);
        crime.setCriminal(this); // Set the criminal reference in the crime
    }

    public Criminal(String name, byte[] leftPhoto, byte[] rightPhoto, byte[] frontPhoto, byte[] retinaScan, byte[] fingerprintScan) {
        this.name = name;
        this.leftPhoto = leftPhoto;
        this.rightPhoto = rightPhoto;
        this.frontPhoto = frontPhoto;
        this.retinaScan = retinaScan;
        this.fingerprintScan = fingerprintScan;
        this.crimes = new ArrayList<>(); // Also initialize here
    }
}
