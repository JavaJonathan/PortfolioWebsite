package com.JavaJonathanSite.weddingGuestList;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//import javax.persistence.Embeddable;

//needs to be serializable for DB
@Embeddable
public class Guest
{
	private String firstName;
    private String lastName;
    private int tableNumber;
    private RSVPStatusEnum rsvpStatus;
    private SpecialGuestEnum specialGuest;
    
    public Guest() {}

    public Guest(String firstName, String lastName, int tableNumber, RSVPStatusEnum rsvpStatus, SpecialGuestEnum specialGuest)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tableNumber = tableNumber;
        this.rsvpStatus = rsvpStatus;
        this.specialGuest = specialGuest;
    }

    public void setFirstName(String firstName){ this.firstName = firstName; }
    public String getFirstName() { return firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getLastName() { return lastName; }

    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }
    public int getTableNumber() { return tableNumber; }

    public void setRsvpStatus(RSVPStatusEnum rsvpStatus) { this.rsvpStatus = rsvpStatus; }
    public RSVPStatusEnum getRsvpStatus() { return rsvpStatus; }

    public void setSpecialGuest(SpecialGuestEnum specialGuest) { this.specialGuest = specialGuest; }
    public SpecialGuestEnum getSpecialGuest() { return specialGuest; }

}