package com.example.users;

import com.example.Databases.DbConnections;
import com.example.criminal_record.CriminalRecordManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Administrator {

    private DbConnections dbc = new DbConnections();

    // 1. Maintain the database
    public void addCriminal(String name, String crimeType, String crimeDetails, String crimeLocation, String bloodGroup) {
        if (dbc.addCriminal(name, crimeType, crimeDetails, crimeLocation, bloodGroup)) {
            System.out.println("Criminal added: " + name);
        } else {
            System.out.println("Failed to add criminal: " + name);
        }
    }

    public CriminalRecordManager getCriminal(int id) {
        ResultSet rs = dbc.getCriminal(id);
        try {
            if (rs != null && rs.next()) {
                CriminalRecordManager crm = new CriminalRecordManager();
                crm.setName(rs.getString("name"));
                crm.setCrimeDetails(rs.getString("crimeType"), rs.getString("crimeDetails"));
                crm.setCrimeLocation(rs.getString("location"));
                crm.setBloodGroup(rs.getString("bloodgroup"));
                crm.setLocation(rs.getString("location"));
                return crm;
            } else {
                System.out.println("No record found for the given ID.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeCriminal(int id) {
        if (dbc.deleteCriminal(id)) {
            System.out.println("Criminal with ID " + id + " removed.");
        } else {
            System.out.println("Failed to remove criminal with ID " + id);
        }
    }

    public void updateCriminal(int id, String name, String crimeType, String crimeDetails, String crimeLocation, String bloodGroup) {
        if (dbc.updateCriminal(id, name, crimeType, crimeDetails, crimeLocation, bloodGroup)) {
            System.out.println("Criminal with ID " + id + " updated.");
        } else {
            System.out.println("Failed to update criminal with ID " + id);
        }
    }

    // 2. Grant/Revoke role to/from other Users
    private Map<String, String> userRoles = new HashMap<>();

    public void grantRole(String userId, String role) {
        userRoles.put(userId, role);
        System.out.println("Role " + role + " granted to user " + userId);
    }

    public void revokeRole(String userId) {
        userRoles.remove(userId);
        System.out.println("Role revoked from user " + userId);
    }

    public String getRole(String userId) {
        return userRoles.get(userId);
    }

    // 3. Backup and restore of data
    public void backupData(String backupFilePath) throws IOException {
        File backupFile = new File(backupFilePath);
        if (!backupFile.exists()) {
            backupFile.createNewFile();
        }
        File databaseFile = new File("database.txt");
        Files.copy(databaseFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Backup created at " + backupFilePath);
    }

    public void restoreData(String backupFilePath) throws IOException {
        File backupFile = new File(backupFilePath);
        if (backupFile.exists()) {
            File databaseFile = new File("database.txt");
            Files.copy(backupFile.toPath(), databaseFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Data restored from " + backupFilePath);
        } else {
            System.out.println("Backup file does not exist.");
        }
    }

    // 4. Monitor the Jail Administration
    public void monitorJailAdministration() {
        System.out.println("Monitoring Jail Administration:");
        System.out.println("User roles: " + userRoles);
    }

    // Cleanup resources
    public void close() {
        dbc.close();
    }

    public static void main(String[] args) {
        Administrator admin = new Administrator();

        // Example usage
        admin.addCriminal("Baibhav Anand", "Theft", "Stole a car", "Munger", "B+");

        CriminalRecordManager crm = admin.getCriminal(1);
        if (crm != null) {
            System.out.println("Criminal retrieved: " + crm.getName());
        }

        admin.updateCriminal(1, "Baibhav Anand", "Theft", "Stole a bike", "Muzaffarpur", "B+");

        admin.removeCriminal(1);

        admin.monitorJailAdministration();
        admin.close();
    }
}
