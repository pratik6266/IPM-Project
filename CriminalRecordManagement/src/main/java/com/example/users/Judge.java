package com.example.users;

import com.example.criminal_record.CriminalRecordManager;
import com.example.Databases.DbConnections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Judge {

    private DbConnections dbc = new DbConnections();

    // 1. Access information of criminals
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
                    System.out.println("Criminal not found.");
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

    // 2. Access information about other Users
    public String getUserInfo(String userId) {
        return userDatabase.get(userId);
    }

    // 3. Reduce/Increase the level of punishment
    public void adjustPunishment(int criminalId, String newPunishmentLevel) {
        CriminalRecordManager crm = getCriminalInfo(criminalId);
        if (crm != null) {
            crm.setPunishmentLevel(newPunishmentLevel);
            if (crm.updateCriminal(criminalId)) { // Assuming updateCriminal method updates punishment level in the database
                System.out.println("Punishment level for criminal " + criminalId + " adjusted to: " + newPunishmentLevel);
            } else {
                System.out.println("Failed to adjust punishment level.");
            }
        } else {
            System.out.println("Criminal not found.");
        }
    }

    // 4. Transfer the criminal to other location
    public void transferCriminal(int criminalId, String newCellNo, String newJailName) {
        CriminalRecordManager crm = getCriminalInfo(criminalId);
        if (crm != null) {
            crm.setCurrentLocation(newCellNo, newJailName);
            if (crm.updateCriminal(criminalId)) { // Assuming updateCriminal method updates location in the database
                System.out.println("Criminal " + criminalId + " transferred to cell " + newCellNo + " in " + newJailName);
            } else {
                System.out.println("Failed to transfer criminal.");
            }
        } else {
            System.out.println("Criminal not found.");
        }
    }

    // Methods to manage the databases
    private Map<String, String> userDatabase = new HashMap<>();

    public void addUser(String id, String role) {
        userDatabase.put(id, role);
    }

    // Optionally, you might want to provide methods to interact with the criminal database
    public void close() {
        dbc.close();
    }
}
