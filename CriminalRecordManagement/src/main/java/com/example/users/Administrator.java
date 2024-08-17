package com.example.users;

import com.example.criminal_record.CriminalRecordManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class Administrator {

    private Map<String, CriminalRecordManager> criminalDatabase = new HashMap<>();
    private Map<String, String> userRoles = new HashMap<>();

    // 1. Maintain the database
    public void addCriminal(String id, CriminalRecordManager crm) {
        criminalDatabase.put(id, crm);
    }

    public CriminalRecordManager getCriminal(String id) {
        return criminalDatabase.get(id);
    }

    public void removeCriminal(String id) {
        criminalDatabase.remove(id);
    }

    public Map<String, CriminalRecordManager> getAllCriminals() {
        return criminalDatabase;
    }

    // 2. Grant/Revoke role to/from other Users
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
        Files.copy(new File("database.txt").toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Backup created at " + backupFilePath);
    }

    public void restoreData(String backupFilePath) throws IOException {
        File backupFile = new File(backupFilePath);
        if (backupFile.exists()) {
            Files.copy(backupFile.toPath(), new File("database.txt").toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Data restored from " + backupFilePath);
        } else {
            System.out.println("Backup file does not exist.");
        }
    }

    // 4. Monitor the Jail Administration
    public void monitorJailAdministration() {
        System.out.println("Monitoring Jail Administration:");
        System.out.println("Total criminals: " + criminalDatabase.size());
        System.out.println("User roles: " + userRoles);
    }
}
