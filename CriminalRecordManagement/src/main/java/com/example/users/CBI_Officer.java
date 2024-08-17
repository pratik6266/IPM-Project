package com.example.users;

import com.example.criminal_record.CriminalRecordManager;

import java.util.HashMap;
import java.util.Map;

public class CBI_Officer {

    private Map<String, CriminalRecordManager> criminalDatabase = new HashMap<>();

    // Add a criminal record to the database
    public void addCriminal(String id, CriminalRecordManager crm) {
        criminalDatabase.put(id, crm);
    }

    // Search for criminals by different criteria
    public CriminalRecordManager searchCriminal(String name, String bloodGroup, String crimeType,
                                                String cellNo, String dnaInfo, String photoView) {
        for (CriminalRecordManager crm : criminalDatabase.values()) {
            if (crm.matchesSearchCriteria(name, bloodGroup, crimeType, cellNo, dnaInfo, photoView)) {
                return crm;
            }
        }
        return null; // Criminal not found
    }

    // Access full information of a criminal
    public CriminalRecordManager getCriminalInfo(String id) {
        return criminalDatabase.get(id);
    }
}
