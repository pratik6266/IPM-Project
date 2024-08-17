package com.example.users;

import com.example.criminal_record.CriminalRecordManager;

import java.util.Scanner;

public class Jail_Superintendent {

    private String name;
    private String JailerID;
    private String location;

    public void setName(String name) {
        this.name = name;
    }

    public void setJailerID(String JailerID) {
        this.JailerID = JailerID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public String getJailerID() {
        return this.JailerID;
    }

    public String getLocation() {
        return this.location;
    }

    public void addCriminal(String crName, String crType, String crDetails, String bloodGroup, String location, Boolean healthIssues) {
        Scanner sc = new Scanner(System.in);
        CriminalRecordManager crm = new CriminalRecordManager();
        crm.setName(crName);
        crm.setCrimeDetails(crType, crDetails);
        crm.setLocation(location);
        crm.setBloodGroup(bloodGroup);

        System.out.println("Enter photo view 1:");
        String photo1 = sc.nextLine();
        System.out.println("Enter photo view 2:");
        String photo2 = sc.nextLine();
        System.out.println("Enter photo view 3:");
        String photo3 = sc.nextLine();
        crm.setPhotos(photo1, photo2, photo3);

        System.out.println("Enter fingerprint:");
        String fingerprint = sc.nextLine();
        crm.setFingerprint(fingerprint);

        System.out.println("Enter retina scan:");
        String retinaScan = sc.nextLine();
        crm.setRetinaScan(retinaScan);

        System.out.println("Enter DNA information:");
        String dnaInfo = sc.nextLine();
        crm.setDnaInfo(dnaInfo);

        if (healthIssues) {
            System.out.println("Enter health details:");
            String healthDetails = sc.nextLine();
            crm.setHealthCondition(healthDetails);
        } else {
            crm.setHealthCondition("N/A");
        }
    }

    public void updateCriminal(CriminalRecordManager crm, String crName, String newCrimeType, String newCrimeDetails) {
        // Implement the update functionality
        crm.setName(crName);
        crm.setCrimeDetails(newCrimeType, newCrimeDetails);
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
