package com.JavaJonathanSite.weddingGuestList;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.WebSession;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class GuestListMainController 
{
	@Autowired
	GuestService guestService;
	
	//string to determine which sort method to take
	String sortMethod;
	
	String mainMessage = "To get started, please add a guest to the list:";
	
	//adds all of the values on the main page into the view (wedding party ct, guest count, etc.)
	@GetMapping("GuestListMainPage")
	public ModelAndView displayListCounts(Model model, HttpSession session) 
	{
		
		ModelAndView mav = new ModelAndView("GuestListMainPage");
		
		mainMessage="To get started, please add a guest to the list.";
		model.addAttribute("mainMessage", mainMessage);
		
		//adds entire list to view, access session to be used after user leaves web app
		session.setAttribute("allGuests", guestService.guestArrayList);
		setCountAttributes(model, session);
	
		
		return mav; 
		
	}
	
	//allows user to search list by first name
	@GetMapping("/searchList")
	public ModelAndView searchList(Model model, @ModelAttribute("searchFirstName") String searchFirstName, HttpSession session) 
	{
		
		ModelAndView mav = new ModelAndView();
		
		//Searches list by first name, method returns false if name isnt in list
		if(guestService.searchByFirstName(searchFirstName) == false) 
		{
			mav.setViewName("GuestListMainPage");
			mainMessage = "No results found. Showing entire list.";
			model.addAttribute("mainMessage", mainMessage);
			session.setAttribute("allGuests", guestService.guestArrayList);
			setCountAttributes(model, session);
		
			return mav;
		} 
		else 
		{
			mav.setViewName("GuestListMainPage");
			mainMessage = "Showing all guests with the name " + searchFirstName; 
			model.addAttribute("mainMessage", mainMessage);
			session.setAttribute("allGuests", guestService.searchList);
			setCountAttributes(model, session);
		}
		
		return mav;
	}
	
	//allows user to remove guest by inputting first and last name
	@PostMapping("/removeGuest")
	public ModelAndView removeGuest(Model model, @ModelAttribute("removeFirstName") String removeFirstName, 
			@ModelAttribute("removeLastName") String removeLastName, HttpSession session) 
	{
		ModelAndView mav = new ModelAndView();
		
		setCountAttributes(model, session);
		
		//remove guest method return boolean if name is in list or not
		if(guestService.removeGuest(removeFirstName, removeLastName) == false) 
		{
			mav.setViewName("GuestListMainPage");
			mainMessage = "Error. Guest not found, please check for typos.";
			model.addAttribute("mainMessage", mainMessage);
			return mav;	
		}
		else 
		{
			mav.setViewName("GuestListMainPage");
			mainMessage = removeFirstName + " " + removeLastName + " has been removed from the list.";
			model.addAttribute("mainMessage", mainMessage);
			setCountAttributes(model, session);
		}
		
		return mav;
	}
	
	//shows user full list after a search
	@GetMapping("/showFullList")
	public ModelAndView showFullList(Model model, HttpSession session) 
	{
		ModelAndView mav = new ModelAndView("GuestListMainPage");
		mainMessage = "Showing full Guest List.";
		model.addAttribute("mainMessage", mainMessage);
		session.setAttribute("allGuests", guestService.guestArrayList);
		return mav;
	}
	
	//allows user to choose which way to sort the list
	//split into separate methods to try new drop down
	@GetMapping("/sortListByLastName")
	public ModelAndView sortListByLastName(Model model, HttpSession session) 
	{
		sortMethod = "Last Name";
		ModelAndView mav = new ModelAndView();
		mav.setViewName("GuestListMainPage");
		guestService.sortList(sortMethod);
		mainMessage = "List sorted by Last Name.";
		model.addAttribute("mainMessage", mainMessage);
		session.setAttribute("allGuests", guestService.guestArrayList);
		return mav;
	}
	
	// allows user to choose which way to sort the list
	// split into separate methods to try new drop down
	@GetMapping("/sortListByTableNumber")
	public ModelAndView sortListByTableNumber(Model model, HttpSession session) {
		sortMethod = "Table Number";
		ModelAndView mav = new ModelAndView();
		mav.setViewName("GuestListMainPage");
		guestService.sortList(sortMethod);
		mainMessage = "List sorted by Table Number.";
		model.addAttribute("mainMessage", mainMessage);
		session.setAttribute("allGuests", guestService.guestArrayList);
		return mav;
	}

	// allows user to choose which way to sort the list
	// split into separate methods to try new drop down
	@GetMapping("/sortListByRSVPStatus")
	public ModelAndView sortListByRSVPStatus(Model model, HttpSession session) 
	{
		sortMethod = "RSVP Status";
		ModelAndView mav = new ModelAndView();
		mav.setViewName("GuestListMainPage");
		guestService.sortList(sortMethod);
		mainMessage = "List sorted by RSVP Status.";
		model.addAttribute("mainMessage", mainMessage);
		session.setAttribute("allGuests", guestService.guestArrayList);
		return mav;
	}

	// allows user to choose which way to sort the list
	// split into separate methods to try new drop down
	@GetMapping("/sortListByGuestStatus")
	public ModelAndView sortListByGuestStatus(Model model, HttpSession session) 
	{
		sortMethod = "Guest Status";
		ModelAndView mav = new ModelAndView();
		mav.setViewName("GuestListMainPage");
		guestService.sortList(sortMethod);
		mainMessage = "List sorted by Guest Status.";
		model.addAttribute("mainMessage", mainMessage);
		session.setAttribute("allGuests", guestService.guestArrayList);
		return mav;
	}
	
	//packages setting the count variables in the session away because they have to be called often
	//made static so add controller can also use with ease
	public void setCountAttributes(Model model, HttpSession session) 
	{
		String guestsWithoutTable = Integer.toString(guestService.guestsWithoutTable()) + " Guest(s) w/o Table #";
		session.setAttribute("guestsWithoutTable", guestsWithoutTable);
		
		//guests with yes replies count
		String numberOfYesReplies = Integer.toString(guestService.numOfYes()) + " Guest(s) replied 'Yes'";
		session.setAttribute("numOfYes", numberOfYesReplies);
		
		//number of guests in wedding
		String weddingPartyCount = Integer.toString(guestService.weddingPartyCount()) + " Wedding Party Count";
		session.setAttribute("weddingPartyCount", weddingPartyCount);
		
		//number of guests invited
		String guestCount = Integer.toString(guestService.guestArrayList.size()) + " Guest(s) Invited";
		session.setAttribute("guestCount", guestCount);
	}
	
}
