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

    // Changed from byte[] to String to store image URLs (optional fields)
    private String leftPhotoUrl;
    private String rightPhotoUrl;
    private String frontPhotoUrl;
    private String retinaScanUrl;
    private String fingerprintScanUrl;

    // One-to-many relationship with Crime, cascade on save, update, and delete, lazy loading
    @OneToMany(mappedBy = "criminal", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Crime> crimes = new ArrayList<>(); // Ensures crimes list is initialized

    // Constructor to initialize a Criminal without crimes (useful when adding a new criminal)
    public Criminal(String name, String leftPhotoUrl, String rightPhotoUrl,
                    String frontPhotoUrl, String retinaScanUrl, String fingerprintScanUrl) {
        this.name = name;
        this.leftPhotoUrl = leftPhotoUrl;
        this.rightPhotoUrl = rightPhotoUrl;
        this.frontPhotoUrl = frontPhotoUrl;
        this.retinaScanUrl = retinaScanUrl;
        this.fingerprintScanUrl = fingerprintScanUrl;
    }

    // Helper method to maintain bidirectional relationship with Crime
    public void addCrime(Crime crime) {
        crimes.add(crime);
        crime.setCriminal(this); // Ensures the crime references this criminal
    }

    // Helper method to remove a crime and maintain the bidirectional relationship
    public void removeCrime(Crime crime) {
        crimes.remove(crime);
        crime.setCriminal(null); // Nullify the reference in Crime entity
    }

    // Optional: Method to validate photo URLs (if needed)
    public boolean hasValidPhotoUrls() {
        return (leftPhotoUrl != null && !leftPhotoUrl.isEmpty()) ||
                (rightPhotoUrl != null && !rightPhotoUrl.isEmpty()) ||
                (frontPhotoUrl != null && !frontPhotoUrl.isEmpty()) ||
                (retinaScanUrl != null && !retinaScanUrl.isEmpty()) ||
                (fingerprintScanUrl != null && !fingerprintScanUrl.isEmpty());
    }

    // Optional: Method to check if the name is valid
    public boolean hasValidName() {
        return name != null && !name.trim().isEmpty();
    }

    // Optional: Utility method to return a short description for the criminal
    public String getShortDescription() {
        return "Criminal Name: " + name;
    }
}
