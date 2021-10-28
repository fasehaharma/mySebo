package com.example.mysebo.role.user.model;


import com.google.firebase.Timestamp;

import java.util.List;

public class Reservation {
    private Timestamp dateEnd;
    private Timestamp dateStart;

    private List<Equipment> equipment;

    private String eventName;
    private String eventOrganization;
    private String id;
    private String userId;
    private String phoneNumber;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Reservation() {
    }

    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventOrganization() {
        return eventOrganization;
    }

    public void setEventOrganization(String eventOrganization) {
        this.eventOrganization = eventOrganization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
