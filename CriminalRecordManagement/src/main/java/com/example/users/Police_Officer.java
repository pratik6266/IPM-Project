package com.example.users;

import com.example.criminal_record.CriminalRecordManager;
import com.example.Databases.DbConnections;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Police_Officer {

    private DbConnections dbc = new DbConnections();

    // Adding a criminal record to the database
    public void addCriminal(CriminalRecordManager crm) {
        if (crm.addCriminal()) { // Using addCriminal method from CriminalRecordManager
            System.out.println("Criminal record added successfully.");
        } else {
            System.out.println("Failed to add criminal record.");
        }
    }

    // Searching for criminals by different criteria
    public CriminalRecordManager searchCriminal(String name, String bloodGroup, String crimeType,String crimeLocation,
                                                String cellNo, String dnaInfo, String photoView) {
        ResultSet rs = dbc.searchCriminal(name, bloodGroup, crimeType,crimeLocation, cellNo);
        if (rs != null) {
            try {
                if (rs.next()) {
                    CriminalRecordManager crm = new CriminalRecordManager();
                    crm.setName(rs.getString("name"));
                    crm.setCrimeDetails(rs.getString("crimeType"), rs.getString("crimeDetails"));
                    crm.setCrimeLocation(rs.getString("location"));
                    crm.setBloodGroup(rs.getString("bloodgroup"));
                    crm.setHealthCondition(rs.getString("healthCondition"));
                    crm.setPunishmentLevel(rs.getString("punishmentLevel")); // Assuming punishmentLevel is stored
                    return crm;
                } else {
                    System.out.println("No matching criminal found.");
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("Error searching for criminal.");
            return null;
        }
    }

    // Transferring a criminal to another jail
    public void transferCriminal(int id, String newCellNo, String newJailName) {
        CriminalRecordManager crm = getCriminalInfo(id);
        if (crm != null) {
            crm.setCurrentLocation(newCellNo, newJailName);
            if (crm.updateCriminal(id)) { // Update criminal record in the database
                System.out.println("Criminal transferred to cell " + newCellNo + " in " + newJailName);
            } else {
                System.out.println("Failed to transfer criminal.");
            }
        } else {
            System.out.println("Criminal not found.");
        }
    }

    // Access full information of a criminal
    public CriminalRecordManager getCriminalInfo(int id) {
        ResultSet rs = dbc.getCriminal(id);
        if (rs != null) {
            try {
                if (rs.next()) {
                    CriminalRecordManager crm = new CriminalRecordManager();
                    crm.setName(rs.getString("name"));
                    crm.setCrimeDetails(rs.getString("crimeType"), rs.getString("crimeDetails"));
                    crm.setCrimeLocation(rs.getString("location"));
                    crm.setBloodGroup(rs.getString("bloodgroup"));
                    crm.setHealthCondition(rs.getString("healthCondition"));
                    crm.setPunishmentLevel(rs.getString("punishmentLevel")); // Assuming punishmentLevel is stored
                    return crm;
                } else {
                    System.out.println("No record found for the given ID.");
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("Error retrieving criminal information.");
            return null;
        }
    }
}
