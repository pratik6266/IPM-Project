package com.example.criminal_record;

import java.util.List;
import java.util.Map;

public class CriminalRecordManager {
    private String name;
    private String crimeType;
    private String crimeDetails;

    //few details like photo, fingerprints are to be added
    private String bloodGroup;

    private String location;
    private Map<String,String> meetups; //name and relation to the person; date and time of meetup
    private String healthCondition;
    private List<String> AssignedWorks;
    private String punishmentPeriod;//in years, months and days

    public String getName()
    {
        return this.name;
    }

    public String getCrimeDetails()
    {
        return this.crimeType+" : \n"+crimeDetails;
    }

    public String getBloodGroup()
    {
        return this.bloodGroup;
    }

    public String getLocation()
    {
        return this.location;
    }

    public Map<String,String> getMeetups()
    {
        return this.meetups;
    }

    public String getHealthCondition(){
        return this.healthCondition;
    }

    public List<String> getAssignedWorks() {
        return this.AssignedWorks;
    }

    public String getPunishmentPeriod()
    {
        return this.punishmentPeriod;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCrimeDetails(String crimeType,String crimeDetails)
    {
        this.crimeType = crimeType;
        this.crimeDetails = crimeDetails;
    }

    public void setBloodGroup(String bloodGroup)
    {
        this.bloodGroup = bloodGroup;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setMeetups(Map<String,String> meetups)
    {
        this.meetups = meetups;
    }

    public void setHealthCondition(String healthCondition)
    {
        this.healthCondition = healthCondition;
    }

    public void setAssignedWorks(List<String> Works)
    {
        this.AssignedWorks = Works;
    }

    public void setPunishmentPeriod(String period)
    {
        this.punishmentPeriod = period;
    }
}
