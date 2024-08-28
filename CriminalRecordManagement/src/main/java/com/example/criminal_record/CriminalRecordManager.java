package com.example.criminal_record;

import com.example.Databases.DbConnections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CriminalRecordManager {

    private DbConnections dbc = new DbConnections();

    private String name;
    private String crimeType;
    private String crimeDetails;
    private String crimeLocation;
    private String punishmentLevel;
    private String location;
    private String bloodGroup;
    private String healthCondition;
    private String cellNo;
    private String jailName;
    private Map<String, String> meetings = new HashMap<>();
    private Map<String, String> assignedWorks = new HashMap<>();

    // Setters and Getters
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setCrimeDetails(String type, String details) {
        this.crimeType = type;
        this.crimeDetails = details;
    }

    public void setCrimeLocation(String crimeLocation) {
        this.crimeLocation = crimeLocation;
    }

    public void setLocation(String location) { this.location = location; }
    public String getLocation() { return location; }

    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public String getBloodGroup() { return bloodGroup; }

    public void setHealthCondition(String healthCondition) { this.healthCondition = healthCondition; }
    public String getHealthCondition() { return healthCondition; }

    public void setPunishmentLevel(String newPunishmentLevel) {
        this.punishmentLevel = newPunishmentLevel;
    }

    public String getPunishmentLevel() {
        return this.punishmentLevel;
    }

    public void setCurrentLocation(String cellNo, String jailName) {
        this.cellNo = cellNo;
        this.jailName = jailName;
    }

    public String getCellNo() { return cellNo; }
    public String getJailName() { return jailName; }

    public String getCrimeLocation() {
        return this.crimeLocation;
    }

    public void addMeeting(String outsiderName, String meetingDetails) {
        meetings.put(outsiderName, meetingDetails);
    }

    public Map<String, String> getMeetings() { return meetings; }

    public void assignWork(String taskName, String taskDetails) {
        assignedWorks.put(taskName, taskDetails);
    }

    public Map<String, String> getAssignedWorks() { return assignedWorks; }

    // CRUD Operations

    public boolean addCriminal() {
        return dbc.addCriminal(name, crimeType, crimeDetails, crimeLocation, bloodGroup);
    }

    public boolean updateCriminal(int id) {
        return dbc.updateCriminal(id, name, crimeType, crimeDetails, crimeLocation, bloodGroup);
    }

    public boolean deleteCriminal(int id) {
        return dbc.deleteCriminal(id);
    }

    public ResultSet searchCriminal(String name, String bloodGroup, String crimeType,String crimeLocation, String cellNo) {
        return dbc.searchCriminal(name, bloodGroup, crimeType,crimeLocation, cellNo);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CriminalRecordManager crm = new CriminalRecordManager();
        // Example usage
        crm.setName("Javed Qadiri");
        crm.setCrimeDetails("Robbery", "Robbed a bank");
        crm.setCrimeLocation("Lucknow");
        crm.setBloodGroup("O+");

        // Add a criminal record
        if (crm.addCriminal()) {
            System.out.println("Criminal added successfully.");
        } else {
            System.out.println("Failed to add criminal.");
        }

        // Search for a criminal

       System.out.println();
       ResultSet rs = crm.searchCriminal("Akshat",null, null,null, null);
       try {
           if (rs != null && rs.next()) {
               System.out.println("Criminal Found: " + rs.getString("name"));
           } else {
               System.out.println("No criminal found.");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }
}
