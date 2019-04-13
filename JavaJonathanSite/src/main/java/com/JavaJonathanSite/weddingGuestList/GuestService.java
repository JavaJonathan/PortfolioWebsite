package com.JavaJonathanSite.weddingGuestList;

import java.util.ArrayList;
import java.util.Collections;

public class GuestService
{
	public static ArrayList<Guest> guestArrayList = new ArrayList<>();

	// we are going to populate this array list with the elements of user search
	// then clear it after every use
	public static ArrayList<Guest> searchList = new ArrayList<>();

	// adds guest to list, return boolean to know whether name was in list or not
	// allows all values though because controller will have checks and set to
	// sensible defaults
	public static boolean addGuestToList(String firstName, String lastName, int tableNumber, RSVPStatus rsvpStatus,
			SpecialGuest specialGuest) 
	{
		//boolean to check if name is in list
		boolean isNameInList = false;

		//boolean to return if guest was added
		boolean guestAdded = false;
		
		//checks to see if the name is already in the list
		for (Guest guests : guestArrayList)
		{
			if (guests.getFirstName().equalsIgnoreCase(firstName) && guests.getLastName().equalsIgnoreCase(lastName))
			{
				guestAdded = false;
				return guestAdded;
			}

		}

		// if name isnt in the list after cycling through, we add it to the list,
		// written long way for readability
		if (isNameInList == false) {
			guestArrayList.add(new Guest(firstName, lastName, tableNumber, rsvpStatus, specialGuest));
			guestAdded = true;
		}

		return guestAdded;
	}
	
	//takes in string to decide which sort method to call
		public static void sortList(String sortMethod) 
		{
			switch(sortMethod) 
			{
				case "Last Name": Collections.sort(guestArrayList, (g1, g2) -> g1.getLastName().compareTo(g2.getLastName())); break;
				case "Table Number": Collections.sort(guestArrayList, (g1, g2) -> g2.getTableNumber() - g1.getTableNumber()); break;
				case "RSVP Status": Collections.sort(guestArrayList, (g1, g2) -> g1.getRsvpStatus().compareTo(g2.getRsvpStatus())); break;
				case "Guest Status": Collections.sort(guestArrayList, (g1, g2) -> g1.getSpecialGuest().compareTo(g2.getSpecialGuest())); break;
			}
		}
		
	// takes in string to decide which rsvpStatus to assign
	// no need to error check because the options will be preset in drop down
	public static RSVPStatus convertToRSVP(String rsvpStatusString) 
	{
		RSVPStatus rsvpStatus = RSVPStatus.MAYBE;

		switch (rsvpStatusString) 
		{
		case "Maybe": rsvpStatus = RSVPStatus.MAYBE; break;
		case "Yes": rsvpStatus = RSVPStatus.YES; break;
		case "No": rsvpStatus = RSVPStatus.NO; break;
		}

		return rsvpStatus;
	}
	
	// takes in string to decide which special guest status to assign
	// no need to error check because the options will be preset in drop down
	public static SpecialGuest convertToSepcialGuest(String specialGuestString) 
	{
		SpecialGuest specialGuest = SpecialGuest.FALSE;

		switch (specialGuestString) 
		{
		case "Groomsman": specialGuest = SpecialGuest.GROOMSMAN; break;
		case "Best Man": specialGuest = SpecialGuest.BESTMAN; break;
		case "Flower Girl": specialGuest = SpecialGuest.FLOWERGIRL; break;
		case "Maid of Honor": specialGuest = SpecialGuest.MAIDOFHONOR; break;
		case "Officiant": specialGuest = SpecialGuest.OFFICIANT; break;
		case "Ring Bearer": specialGuest = SpecialGuest.RINGBEARER; break;
		case "Brides Maid": specialGuest = SpecialGuest.BRIDESMAID; break;
		case "Regular Guest": specialGuest = SpecialGuest.FALSE; break;
		}

		return specialGuest;
	}

	// method to return number of people in the wedding party
	// return an int to be rendered in view
	public static int weddingPartyCount() 
	{
		// counter for # of wedding party to be returned
		int counter = 0;

		// cycles through list, then checks each guests status then counts
		// the number of special guests
		for (Guest guests : guestArrayList)
		{
			switch (guests.getSpecialGuest()) 
			{
			case BRIDESMAID: counter++; break;
			case GROOMSMAN: counter++; break;
			case MAIDOFHONOR: counter++; break;
			case BESTMAN: counter++; break;
			case FLOWERGIRL: counter++; break;
			case OFFICIANT: counter++; break;
			case RINGBEARER: counter++; break;
			}
		}

		return counter;
	}

	// method to return number of people that have said yes
	// return an int to be rendered in view
	public static int numOfYes() {
		// counter for # of yes to be returned
		int counter = 0;

		// cycles through list, then checks each guests rsvp status then counts
		// the number of yes replies
		for (Guest guests : guestArrayList) {
			if (guests.getRsvpStatus().equals(RSVPStatus.YES)) {
				counter++;
			}
		}

		return counter;
	}
	
	// method to return number of people that are not assigned a table #
	// return an int to be rendered in view
	public static int guestsWithoutTable()
	{
		// counter for # of guests w/o table # assigned to be returned
		int counter = 0;

		// cycles through list, then checks each guests table number then counts
		// the number of people that have their table set to 0
		for (Guest guests : guestArrayList)
		{
			if (guests.getTableNumber() == 0) 
			{
				counter++;
			}
		}

		return counter;
	}
	
	

	public static boolean editTableNumber(String firstName, String lastName, int newTableNumber) {
		boolean isNameInList = false;

		// cycles through list
		for (Guest guests : guestArrayList) {
			// checks to make sure entered name matches first and last name to ensure nobody
			// will be edited on accident
			if (firstName.trim().equalsIgnoreCase(guests.getFirstName()) && lastName.trim().equalsIgnoreCase(guests.getLastName())) {
				// sets new rsvpStatus if person is in list then breaks for loop
				guests.setTableNumber(newTableNumber);
				isNameInList = true;
				break;
			} else {
				isNameInList = false;
			}
		}

		return isNameInList;

	}

	public static boolean editGuestFirstName(String firstName, String lastName, String newFirstName) 
	{
		boolean isNameInList = false;

		// cycles through list
		for (Guest guests : guestArrayList) {
			// checks to make sure entered name matches first and last name to ensure nobody
			// will be edited on accident
			if (firstName.equalsIgnoreCase(guests.getFirstName()) && lastName.equalsIgnoreCase(guests.getLastName()))
			{
				// sets new name if person is in list then breaks for loop
				guests.setFirstName(newFirstName);
				isNameInList = true;
				break;
			} else
			{
				isNameInList = false;
			}
		}

		return isNameInList;
	}
	
	public static boolean editGuestLastName(String firstName, String lastName, String newLastName) 
	{
		boolean isNameInList = false;

		// cycles through list
		for (Guest guests : guestArrayList) {
			// checks to make sure entered name matches first and last name to ensure nobody
			// will be edited on accident
			if (firstName.equalsIgnoreCase(guests.getFirstName()) && lastName.equalsIgnoreCase(guests.getLastName()))
			{
				// sets new name if person is in list then breaks for loop
				guests.setLastName(newLastName);
				isNameInList = true;
				break;
			} else
			{
				isNameInList = false;
			}
		}

		return isNameInList;
	}

	public static boolean editRsvpStatus(String firstName, String lastName, RSVPStatus newRsvpStatus) 
	{
		boolean isNameInList = false;

		// cycles through list
		for (Guest guests : guestArrayList)
		{
			// checks to make sure entered name matches first and last name to ensure nobody
			// will be edited on accident
			if (firstName.trim().equalsIgnoreCase(guests.getFirstName()) && lastName.trim().equalsIgnoreCase(guests.getLastName())) 
			{
				// sets new rsvpStatus if person is in list then breaks for loop
				guests.setRsvpStatus(newRsvpStatus);
				isNameInList = true;
				break;
			} else 
			{
				isNameInList = false;
			}
		}

		return isNameInList;

	}

	public static boolean editSpecialGuest(String firstName, String lastName, SpecialGuest newSpecialGuest) 
	{
		boolean isNameInList = false;

		// cycles through list
		for (Guest guests : guestArrayList)
		{
			// checks to make sure entered name matches first and last name to ensure nobody
			// will be edited on accident
			if (firstName.trim().equalsIgnoreCase(guests.getFirstName()) && lastName.trim().equalsIgnoreCase(guests.getLastName())) 
			{
				// sets new rsvpStatus if person is in list then breaks for loop
				guests.setSpecialGuest(newSpecialGuest);
				isNameInList = true;
				break;
			}
		}

		return isNameInList;

	}

	// ALLOWS USER TO SEARCH GUESTS BY FIRST NAME, adds any name found to match to
	// the search list
	public static boolean searchByFirstName(String firstName) 
	{
		//clears search list before each use
		if(!searchList.isEmpty()) 
		{
			searchList.clear();
		}
		
		boolean isNameInList = true;

		// cycles through list
		for (Guest guests : guestArrayList)
		{

			if (firstName.trim().equalsIgnoreCase(guests.getFirstName())) 
			{
				searchList.add(guests);
			}

		}

		// if list is empty, then guests name was not in the list
		if (searchList.isEmpty()) 
		{
			isNameInList = false;
		}

		return isNameInList;
	}

	// ALLOWS USER TO SEARCH GUESTS BY LAST NAME, adds any name found to match to
	// the search list
	public static boolean searchByLastName(String lastName) {
		boolean isNameInList = true;

		// cycles through list
		for (Guest guests : guestArrayList) {

			if (lastName.trim().equalsIgnoreCase(guests.getLastName())) {
				searchList.add(guests);
			}

		}

		// if list is empty, then guests name was not in the list
		if (searchList.isEmpty()) {
			isNameInList = false;
		}

		return isNameInList;
	}

	// ALLOWS USER TO REMOVE GUESTS BY NAME, removes name found in list
	public static boolean removeGuest(String firstName, String lastName)
	{
		boolean isNameInList = false;

		// cycles through list
		for (Guest guests : guestArrayList) 
		{

			if (guests.getFirstName().equalsIgnoreCase(firstName.trim()) && guests.getLastName().equalsIgnoreCase(lastName.trim())) 
			{
				guestArrayList.remove(guests);
				isNameInList = true; break;
			}

		}

		return isNameInList;
	}

}
	
