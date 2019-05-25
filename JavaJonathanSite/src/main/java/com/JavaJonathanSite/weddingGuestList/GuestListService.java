package com.JavaJonathanSite.weddingGuestList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class GuestListService 
{
	@Autowired
	GuestListRepository guestListRepository;
	
	//helper method for editList method, returns boolean for valid pin checking
	public boolean checkForValidPin(String id) 
	{

		boolean validPin = false;

		// creates an array list from the database to be cycled
		ArrayList<GuestListEntity> searchList = new ArrayList<>();

		guestListRepository.findAll().forEach(g -> searchList.add(g));

		for (GuestListEntity guestLists : searchList) 
		{
			if (guestLists.getId().equals(id)) 
			{
				validPin = true;
			}
		}

		return validPin;

	}

	// retrieves list from database for user to edit
	public Collection<Guest> editList(String id) 
	{
		// after checks, finds list
		return guestListRepository.findById(id).get().getGuestList();
	}

	// allows user to delete their list from database, returns boolean for error
	// checking
	public boolean deleteList(String id) {
		boolean validPin = false;

		// creates an array list from the database to be cycled
		ArrayList<GuestListEntity> searchList = new ArrayList<>();
		guestListRepository.findAll().forEach(g -> searchList.add(g));

		for (GuestListEntity guestLists : searchList)
		{
			if (guestLists.getId().equals(id)) 
			{
				validPin = true;

				// after checks, deletes list
				guestListRepository.deleteById(id);
			}
		}

		return validPin;

	}

	// allows user to update the list with their id, id is checked before this
	// method is called
	public void updateList(String id, ArrayList<Guest> guestList)
	{
		//sets new guestList
		guestListRepository.findById(id).get().setList(guestList);
		
		//saves it
		guestListRepository.save(guestListRepository.findById(id).get());
	}

	// saves list to database
	public String saveList(ArrayList<Guest> guestList)
	{
		// saves pin into variable and returns it to give to the user
		String userPin = getPin();

		//saves entity into database while putting the correct values in the
		//constructor
		guestListRepository.save(new GuestListEntity(userPin, guestList));

		return userPin;
	}

	// method to set the id for the database, gives each user a unique pin to
	// reference their list
	private String getPin() 
	{

		// string to be returned for the pin
		String pinNumber;

		// boolean to check whether pin is a duplicate before set
		boolean isDuplicate = false;

		// creates an array list from the database to be cycled
		ArrayList<GuestListEntity> searchList = new ArrayList<>();
		guestListRepository.findAll().forEach(g -> searchList.add(g));

		// do while loop to continue to retry to find a pin number that is not a
		// duplicate before the program moves on
		do 
		{
			pinNumber = giveFourDigits();
			for (GuestListEntity guestLists : searchList) 
			{
				if (guestLists.getId().equals(pinNumber)) {isDuplicate = true; break;}
			}
		} while (isDuplicate == true);

		return pinNumber;

	}

	// randomly assigns 4 digits to a string for the pin number
	private String giveFourDigits()
	{
		Random randomDigit = new Random();

		String fourDigits = "";

		for (int i = 0; i < 4; i++) 
		{
			fourDigits += randomDigit.nextInt(9);
		}

		return fourDigits;
	}

}
