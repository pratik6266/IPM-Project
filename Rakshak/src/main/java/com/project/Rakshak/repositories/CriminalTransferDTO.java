package com.project.Rakshak.repositories;

import java.time.LocalDate;
import java.time.LocalTime;

public class CriminalTransferDTO {

    private Long criminalId; // Criminal ID
    private String destination; // Transfer destination (new facility)
    private LocalDate transferDate; // Date of transfer
    private LocalTime transferTime; // Time of transfer
    private String transferReason; // Reason for the transfer
    private String officerName; // Officer name handling the transfer

    // Getters and Setters

    public Long getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(Long criminalId) {
        this.criminalId = criminalId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public LocalTime getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(LocalTime transferTime) {
        this.transferTime = transferTime;
    }

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }
}
