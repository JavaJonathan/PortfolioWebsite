package com.JavaJonathanSite.weddingGuestList;

enum RSVPStatusEnum
{
    YES("Yes"), NO("No"), MAYBE("Maybe");

    private String rsvpStatus;

    RSVPStatusEnum(String status)
    {
        rsvpStatus = status;
    }

    public String toString()
    {
        return rsvpStatus;
    }
}
