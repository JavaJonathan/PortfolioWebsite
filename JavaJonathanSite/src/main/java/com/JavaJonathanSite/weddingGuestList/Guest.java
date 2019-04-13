package com.JavaJonathanSite.weddingGuestList;

public class Guest
{

    private String firstName;
    private String lastName;
    private int tableNumber;
    private RSVPStatus rsvpStatus;
    private SpecialGuest specialGuest;

    public Guest(String firstName, String lastName, int tableNumber, RSVPStatus rsvpStatus, SpecialGuest specialGuest)
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

    public void setRsvpStatus(RSVPStatus rsvpStatus) { this.rsvpStatus = rsvpStatus; }
    public RSVPStatus getRsvpStatus() { return rsvpStatus; }

    public void setSpecialGuest(SpecialGuest specialGuest) { this.specialGuest = specialGuest; }
    public SpecialGuest getSpecialGuest() { return specialGuest; }

}

enum RSVPStatus
{
    YES("Yes"), NO("No"), MAYBE("Maybe");

    private String rsvpStatus;

    RSVPStatus(String status)
    {
        rsvpStatus = status;
    }

    public String toString()
    {
        return rsvpStatus;
    }
}

//creates an enum for special guest and has a false option for everyone else, and set false to default to make it easier on user
enum SpecialGuest
{
    BRIDESMAID("Brides Maid"), GROOMSMAN("Groomsman"), MAIDOFHONOR("Maid of Honor"), BESTMAN("Best Man"),
    FLOWERGIRL("Flower Girl"), OFFICIANT("Officiant"), RINGBEARER("Ring Bearer"), FALSE("Regular Guest");

    private String description;

    SpecialGuest(String description)
    {
        this.description = description;
    }

    //overrides to string method so table column can print correct string when populating table, instead of enum name
    public String toString()
    {
        return description;
    }
}