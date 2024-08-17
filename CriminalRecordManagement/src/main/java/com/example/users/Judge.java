package com.example.users;

import com.example.criminal_record.CriminalRecordManager;

import java.util.HashMap;
import java.util.Map;

public class Judge {

    private Map<String, CriminalRecordManager> criminalDatabase = new HashMap<>();
    private Map<String, String> userDatabase = new HashMap<>();

    // 1. Access information of criminals
    public CriminalRecordManager getCriminalInfo(String id) {
        return criminalDatabase.get(id);
    }

    // 2. Access information about other Users
    public String getUserInfo(String userId) {
        return userDatabase.get(userId);
    }

    // 3. Reduce/Increase the level of punishment
    public void adjustPunishment(String criminalId, String newPunishmentLevel) {
        CriminalRecordManager crm = criminalDatabase.get(criminalId);
        if (crm != null) {
            crm.setPunishmentLevel(newPunishmentLevel);
            System.out.println("Punishment level for criminal " + criminalId + " adjusted to: " + newPunishmentLevel);
        } else {
            System.out.println("Criminal not found.");
        }
    }

    // 4. Transfer the criminal to other location
    public void transferCriminal(String criminalId, String newCellNo, String newJailName) {
        CriminalRecordManager crm = criminalDatabase.get(criminalId);
        if (crm != null) {
            crm.setCurrentLocation(newCellNo, newJailName);
            System.out.println("Criminal " + criminalId + " transferred to cell " + newCellNo + " in " + newJailName);
        } else {
            System.out.println("Criminal not found.");
        }
    }

    // Methods to manage the databases
    public void addCriminal(String id, CriminalRecordManager crm) {
        criminalDatabase.put(id, crm);
    }

    public void addUser(String id, String role) {
        userDatabase.put(id, role);
    }
}
