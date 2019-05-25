package com.JavaJonathanSite.weddingGuestList;

public enum SpecialGuestEnum
{
    BRIDESMAID("Brides Maid"), GROOMSMAN("Groomsman"), MAIDOFHONOR("Maid of Honor"), BESTMAN("Best Man"),
    FLOWERGIRL("Flower Girl"), OFFICIANT("Officiant"), RINGBEARER("Ring Bearer"), FALSE("Regular Guest");

    private String description;

    SpecialGuestEnum(String description)
    {
        this.description = description;
    }

    //overrides to string method so table column can print correct string when populating table, instead of enum name
    public String toString()
    {
        return description;
    }
}
