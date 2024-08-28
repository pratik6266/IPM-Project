package com.example.users;

import com.example.criminal_record.CriminalRecordManager;

import java.util.Scanner;

public class Jail_Superintendent {

    private String name;
    private String jailerID;
    private String location;

    public void setName(String name) {
        this.name = name;
    }

    public void setJailerID(String jailerID) {
        this.jailerID = jailerID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public String getJailerID() {
        return this.jailerID;
    }

    public String getLocation() {
        return this.location;
    }

    public void addCriminal(String crName, String crType, String crDetails, String bloodGroup, String location, Boolean healthIssues) {
        Scanner sc = new Scanner(System.in);
        CriminalRecordManager crm = new CriminalRecordManager();
        crm.setName(crName);
        crm.setCrimeDetails(crType, crDetails);
        crm.setCrimeLocation(location);
        crm.setBloodGroup(bloodGroup);

        if (healthIssues) {
            System.out.println("Enter health details:");
            String healthDetails = sc.nextLine();
            crm.setHealthCondition(healthDetails);
        } else {
            crm.setHealthCondition("N/A");
        }

        if (crm.addCriminal()) {
            System.out.println("Criminal added successfully.");
        } else {
            System.out.println("Failed to add criminal.");
        }
    }

    public void updateCriminal(CriminalRecordManager crm, int criminalID, String newName, String newCrimeType, String newCrimeDetails) {
        // Set the updated values
        crm.setName(newName);
        crm.setCrimeDetails(newCrimeType, newCrimeDetails);

        if (crm.updateCriminal(criminalID)) {
            System.out.println("Criminal record updated successfully.");
        } else {
            System.out.println("Failed to update criminal record.");
        }
    }

    public void recordMeeting(CriminalRecordManager crm, String outsiderName, String meetingDetails) {
        crm.addMeeting(outsiderName, meetingDetails);
    }

    public void assignWork(CriminalRecordManager crm, String taskName, String taskDetails) {
        crm.assignWork(taskName, taskDetails);
    }

    public void maintainCurrentLocation(CriminalRecordManager crm, String cellNo, String jailName) {
        crm.setCurrentLocation(cellNo, jailName);
    }
}
