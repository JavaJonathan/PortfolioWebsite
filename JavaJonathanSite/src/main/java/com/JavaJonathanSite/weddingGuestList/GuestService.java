package com.JavaJonathanSite.weddingGuestList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = "session")
public class GuestService
{
	public ArrayList<Guest> guestArrayList = new ArrayList<>();

	// we are going to populate this array list with the elements of user search
	// then clear it after every use
	public ArrayList<Guest> searchList = new ArrayList<>();

	// adds guest to list, return boolean to know whether name was in list or not
	// allows all values though because controller will have checks and set to
	// sensible defaults
	public boolean addGuestToList(String firstName, String lastName, int tableNumber, RSVPStatusEnum rsvpStatus,
			SpecialGuestEnum specialGuest) 
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
		if (isNameInList == false) 
		{
			guestArrayList.add(new Guest(firstName, lastName, tableNumber, rsvpStatus, specialGuest));
			guestAdded = true;
		}

		return guestAdded;
	}
	
	//cycles through collection  and adds all guests from collection to array list, spring says collection cannot be casted into array list
	//this is how we bypass that
	public void setGuestListFromDB(Collection<Guest> guestList) 
	{
		//clears list before populated from database just in case user already has the table popluated 
		guestArrayList.clear();
		guestList.stream().forEach(g -> guestArrayList.add(g));
	}
	
	//takes in string to decide which sort method to call
		public void sortList(String sortMethod) 
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
	public RSVPStatusEnum convertToRSVP(String rsvpStatusString) 
	{
		RSVPStatusEnum rsvpStatus = RSVPStatusEnum.MAYBE;

		switch (rsvpStatusString) 
		{
		case "Maybe": rsvpStatus = RSVPStatusEnum.MAYBE; break;
		case "Yes": rsvpStatus = RSVPStatusEnum.YES; break;
		case "No": rsvpStatus = RSVPStatusEnum.NO; break;
		}

		return rsvpStatus;
	}
	
	// takes in string to decide which special guest status to assign
	// no need to error check because the options will be preset in drop down
	public SpecialGuestEnum convertToSepcialGuest(String specialGuestString) 
	{
		SpecialGuestEnum specialGuest = SpecialGuestEnum.FALSE;

		switch (specialGuestString) 
		{
		case "Groomsman": specialGuest = SpecialGuestEnum.GROOMSMAN; break;
		case "Best Man": specialGuest = SpecialGuestEnum.BESTMAN; break;
		case "Flower Girl": specialGuest = SpecialGuestEnum.FLOWERGIRL; break;
		case "Maid of Honor": specialGuest = SpecialGuestEnum.MAIDOFHONOR; break;
		case "Officiant": specialGuest = SpecialGuestEnum.OFFICIANT; break;
		case "Ring Bearer": specialGuest = SpecialGuestEnum.RINGBEARER; break;
		case "Brides Maid": specialGuest = SpecialGuestEnum.BRIDESMAID; break;
		case "Regular Guest": specialGuest = SpecialGuestEnum.FALSE; break;
		}

		return specialGuest;
	}

	// method to return number of people in the wedding party
	// return an int to be rendered in view
	public int weddingPartyCount() 
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
	public int numOfYes() {
		// counter for # of yes to be returned
		int counter = 0;

		// cycles through list, then checks each guests rsvp status then counts
		// the number of yes replies
		for (Guest guests : guestArrayList) {
			if (guests.getRsvpStatus().equals(RSVPStatusEnum.YES)) {
				counter++;
			}
		}

		return counter;
	}
	
	// method to return number of people that are not assigned a table #
	// return an int to be rendered in view
	public int guestsWithoutTable()
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
	
	

	public boolean editTableNumber(String firstName, String lastName, int newTableNumber) {
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

	public boolean editGuestFirstName(String firstName, String lastName, String newFirstName) 
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
	
	public boolean editGuestLastName(String firstName, String lastName, String newLastName) 
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

	public boolean editRsvpStatus(String firstName, String lastName, RSVPStatusEnum newRsvpStatus) 
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

	public boolean editSpecialGuest(String firstName, String lastName, SpecialGuestEnum newSpecialGuest) 
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
	public boolean searchByFirstName(String firstName) 
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
	public boolean searchByLastName(String lastName) {
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
	public boolean removeGuest(String firstName, String lastName)
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
	
