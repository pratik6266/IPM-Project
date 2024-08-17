package com.example.criminal_record;

import java.util.HashMap;
import java.util.Map;

public class CriminalRecordManager {
    private String name;
    private String crimeType;
    private String crimeDetails;
    private String punishmentLevel;
    private String location;
    private String bloodGroup;
    private String healthCondition;
    private String photoView1;
    private String photoView2;
    private String photoView3;
    private String fingerprint;
    private String retinaScan;
    private String dnaInfo;
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

    public void setLocation(String location) { this.location = location; }
    public String getLocation() { return location; }

    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public String getBloodGroup() { return bloodGroup; }

    public void setHealthCondition(String healthCondition) { this.healthCondition = healthCondition; }
    public String getHealthCondition() { return healthCondition; }

    public void setPhotos(String view1, String view2, String view3) {
        this.photoView1 = view1;
        this.photoView2 = view2;
        this.photoView3 = view3;
    }

    public void setPunishmentLevel(String newPunishmentLevel)
    {
        this.punishmentLevel = newPunishmentLevel;
    }

    public String getPunishmentLevel()
    {
        return this.punishmentLevel;
    }

    public String getPhotoView1() { return photoView1; }
    public String getPhotoView2() { return photoView2; }
    public String getPhotoView3() { return photoView3; }

    public void setFingerprint(String fingerprint) { this.fingerprint = fingerprint; }
    public String getFingerprint() { return fingerprint; }

    public void setRetinaScan(String retinaScan) { this.retinaScan = retinaScan; }
    public String getRetinaScan() { return retinaScan; }

    public void setDnaInfo(String dnaInfo) { this.dnaInfo = dnaInfo; }
    public String getDnaInfo() { return dnaInfo; }

    public void setCurrentLocation(String cellNo, String jailName) {
        this.cellNo = cellNo;
        this.jailName = jailName;
    }

    public String getCellNo() { return cellNo; }
    public String getJailName() { return jailName; }

    public void addMeeting(String outsiderName, String meetingDetails) {
        meetings.put(outsiderName, meetingDetails);
    }

    public Map<String, String> getMeetings() { return meetings; }

    public void assignWork(String taskName, String taskDetails) {
        assignedWorks.put(taskName, taskDetails);
    }

    public Map<String, String> getAssignedWorks() { return assignedWorks; }

    // Search functionality
    public boolean matchesSearchCriteria(String name, String bloodGroup, String crimeType,
                                         String cellNo, String dnaInfo, String photoView) {
        return (name != null && name.equals(this.name)) ||
                (bloodGroup != null && bloodGroup.equals(this.bloodGroup)) ||
                (crimeType != null && crimeType.equals(this.crimeType)) ||
                (cellNo != null && cellNo.equals(this.cellNo)) ||
                (dnaInfo != null && dnaInfo.equals(this.dnaInfo)) ||
                (photoView != null && (photoView.equals(this.photoView1) ||
                        photoView.equals(this.photoView2) ||
                        photoView.equals(this.photoView3)));
    }
}
