package com.JavaJonathanSite.weddingGuestList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class AddPageController {
	
	@Autowired
	GuestService guestService;
	
	@Autowired
	GuestListMainController guestListMainController;
	
	//String to be set to the main text on the webpage, telling user what to do next
	//we will concatenate for multiple steps/errors
	String mainMessage = "You can either Add or Edit a Guest Here:";
	
	@GetMapping("/GuestListAddPage")
	public ModelAndView guestListAddPagePageView(Model model) 
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("GuestListAddPage");
		mainMessage="You can either add a guest or edit a guest here:";
		model.addAttribute("mainMessage", mainMessage);
		return mv;
	}
	
	
	
	
	//takes in all of the parameters of the add page, then checks if they are null and 
	//sets them to sensible defaults
	//table number must be a string to check if it is null, so we convert it into an int
	@PostMapping("/addGuest")
	public ModelAndView addGuest(@ModelAttribute("addFirstName")String addFirstName, 
			@ModelAttribute("addLastName") String addLastName, @ModelAttribute("addTableNumber") String addTableNumber,
			@ModelAttribute("addRsvpStatus") String addRsvpStatus, 
			@ModelAttribute("addSpecialGuest") String addSpecialGuest, Model model, HttpSession session) 
	{
		
		ModelAndView mav = new ModelAndView();
		
		//int for table number to be stored properly after check if null
		int tableNumber;
		
		if(addFirstName.length() == 0 || addLastName.length() == 0) 
		{
			mav.setViewName("GuestListAddPage");
			mainMessage = "Invalid Input. Please enter the guests' name.";
			model.addAttribute("mainMessage", mainMessage);
			
			return mav;
		}
		
		//using a try catch instead of if statement to catch number format exception
		try{tableNumber = Integer.parseInt(addTableNumber);}
		catch(NumberFormatException nfe) 
		{
			mainMessage = mainMessage + "\n" + "Please enter a proper table number.";
			tableNumber = 0;
		}
		
		//final check to make sure everything goes well, returns if guest was added to list, trims name so it doesnt mess up any searches
		if(guestService.addGuestToList(addFirstName.trim(), addLastName.trim(), tableNumber, guestService.convertToRSVP(addRsvpStatus),
			guestService.convertToSepcialGuest(addSpecialGuest))) 
		{
			mav.setViewName("GuestListMainPage");
			mainMessage = addFirstName +" "+ addLastName +" "+ "was added to the list.";
			model.addAttribute("mainMessage", mainMessage);
			session.setAttribute("allGuests", guestService.guestArrayList);
			
			//adds all counts to main view, stolen method from main controller
			guestListMainController.setCountAttributes(model, session);
			
			return mav;
		}
		
		//if program makes it here, there was an error
		mainMessage = "Error. Please try adding a guest again and check for mistakes.";
		mav.setViewName("GuestListAddPage");
		model.addAttribute("mainMessage", mainMessage);
		return mav;
		
	}
	
	
	
	
	
	//allows user to edit guest
	@PostMapping("/editGuest")
	public ModelAndView editGuest(@ModelAttribute("editOldFirstName")String editOldFirstName,
			@ModelAttribute("editOldLastName") String editOldLastName,
			@ModelAttribute("editNewFirstName")String editNewFirstName,
			@ModelAttribute("editNewLastName")String editNewLastName,
			@ModelAttribute("editTableNumber") String editTableNumber,
			@ModelAttribute("editRsvpStatus") String editRsvpStatus, 
			@ModelAttribute("editSpecialGuest") String editSpecialGuest, Model model, HttpSession session) 
	{
		ModelAndView mav = new ModelAndView();
		
		//we need this variable for a weird interaction where the edit last name method cannot edit the guest 
		//if the name is edited by the edit first name method
		//set to old name by default just in case it is not changed to a  new name
		String guestNewFirstName = editOldFirstName;
		
		//declaration to hold converted tableNumber
		int tableNumber;
		
		//clear mainMessage after each call
		mainMessage = ""; 
		
		//checks to make sure they inputed a guest to be edited
		if(editOldFirstName.length() == 0 || editOldLastName.length() == 0) 
		{
			mav.setViewName("GuestListAddPage");
			mainMessage = "Invalid Input. Please enter the guests' name.";
			model.addAttribute("mainMessage", mainMessage);
			
			return mav;
		}
		
		//if block to catch if table number text box has value
		if(editTableNumber.length() != 0) 
		{
			//using a try catch instead of if statement to catch number format exception
			try{tableNumber = Integer.parseInt(editTableNumber);}
			catch(NumberFormatException nfe) 
			{
				mainMessage = mainMessage + "\nPlease enter a proper table number.";
				tableNumber = 0;
			}
			
			//allows user to edit table number
			if(guestService.editTableNumber(editOldFirstName, editOldLastName, tableNumber) == false)
			{
				mav.setViewName("GuestListAddPage");
				mainMessage = "Input error. Guest not in list.";
				model.addAttribute("mainMessage", mainMessage);
				
				//returns user out of method on failed check of name
				return mav;
				
			} else 
			{
				mav.setViewName("GuestListMainPage");
				mainMessage = editOldFirstName + " " + editOldLastName + " has been edited." + "\n";
				model.addAttribute("mainMessage", mainMessage);
			}
			
		}	
		
			//checks guest status
			if(guestService.editSpecialGuest(editOldFirstName, editOldLastName, guestService.convertToSepcialGuest(editSpecialGuest)) == false)
			{
				mav.setViewName("GuestListAddPage");
				mainMessage = "Input error. Guest not in list.";
				model.addAttribute("mainMessage", mainMessage);
				
				//returns user out of method on failed check of name
				return mav;
				
			} else 
			{
				mav.setViewName("GuestListMainPage");
				mainMessage = editOldFirstName + " " + editOldLastName + " has been edited.";
				model.addAttribute("mainMessage", mainMessage);
			}
		
			//checks rsvp status
			if(guestService.editRsvpStatus(editOldFirstName, editOldLastName, guestService.convertToRSVP(editRsvpStatus)) == false)
			{
				mav.setViewName("GuestListAddPage");
				mainMessage = "Input error. Guest not in list.";
				model.addAttribute("mainMessage", mainMessage);
				
				//returns user out of method on failed check of name
				return mav;
				
			} else 
			{
				mav.setViewName("GuestListMainPage");
				mainMessage = editOldFirstName + " " + editOldLastName + " has been edited.";
				model.addAttribute("mainMessage", mainMessage);
			}
			
			//checks for first name, edits name last so all other methods work xD
			if(editNewFirstName.length() != 0) 
			{
				if(guestService.editGuestFirstName(editOldFirstName, editOldLastName, editNewFirstName) == false)
				{
					mav.setViewName("GuestListAddPage");
					mainMessage = "Input error. Guest not in list.";
					model.addAttribute("mainMessage", mainMessage);
					
					
					//returns user out of method on failed check of name
					return mav; 
				} else 
				{
					guestNewFirstName = editNewFirstName;
					mav.setViewName("GuestListMainPage");
					mainMessage = editOldFirstName + " " + editOldLastName + " has been edited.";
					model.addAttribute("mainMessage", mainMessage);
					session.setAttribute("guestList", guestService.guestArrayList);
				}
			}
			
			//checks for last name, edits name last so all other methods work xD
			//takes in guest new first name just in case it is changed in the same form
			if(editNewLastName.length() != 0) 
			{
				if(guestService.editGuestLastName(guestNewFirstName, editOldLastName, editNewLastName) == false)
				{
					mav.setViewName("GuestListAddPage");
					mainMessage = "Input error. Guest not in list. 3";
					model.addAttribute("mainMessage", mainMessage);
					
					
					//returns user out of method on failed check of name
					return mav; 
				} else 
				{
					mav.setViewName("GuestListMainPage");
					mainMessage = editOldFirstName + " " +editOldLastName + " has been edited.";
					model.addAttribute("mainMessage", mainMessage);
					session.setAttribute("guestList", guestService.guestArrayList);
				}
			}
			
			//adds all counts to main view, stolen method from main controller
			guestListMainController.setCountAttributes(model, session);
			
		return mav;
	}

}
