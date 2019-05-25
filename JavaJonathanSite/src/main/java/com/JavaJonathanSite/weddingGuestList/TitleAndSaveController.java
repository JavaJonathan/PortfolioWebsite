package com.JavaJonathanSite.weddingGuestList;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class TitleAndSaveController
{
	@Autowired
	GuestListService guestListService;
	
	GuestService guestService =  new GuestService();
	
	@GetMapping("GuestListTitlePage")
	public ModelAndView displayTitlePage(Model model) 
	{
		ModelAndView mav = new ModelAndView("GuestListTitlePage");
		model.addAttribute("mainMessage", "Edit existing list with PIN:");
		return mav;
	}
	
	@GetMapping("GuestListSavePage")
	public ModelAndView displaySavePage(Model model) 
	{
		ModelAndView mav = new ModelAndView("GuestListSavePage");
		
		//we need these attributes set on launch of page because thymeleaf code will overwrite if only written in html doc
		model.addAttribute("editMainMessage", "OR");
		model.addAttribute("deleteMessage", "Delete your list if you no longer need it/want it on the website. ACTION CANNOT BE UNDONE!");
		
		return mav;
	}
	
	//maps to the edit existing list button on title page
	@PostMapping("/retrieveList")
	public ModelAndView retrieveList(Model model, HttpSession session, @ModelAttribute("userPin") String userPin) 
	{
		String mainMessage;
		
		ModelAndView mav = new ModelAndView();
		
		//if pin isn't valid, keep them on the same page
		if(userPin == null || guestListService.checkForValidPin(userPin) == false) 
		{
			mav.setViewName("GuestListTitlePage");
			mainMessage = "Invalid PIN. Please try again.";
			model.addAttribute("mainMessage", mainMessage);
			return mav;
		}
		
		//only makes it here once pin is valid
		mav.setViewName("GuestListMainPage");
		
		mainMessage = "List found. Showing all guests.";
		model.addAttribute("mainMessage", mainMessage);
		//sets the guest list in the guest service class because that list is added to the session on main page call
		guestService.setGuestListFromDB(guestListService.editList(userPin));
		
		return mav;
	}
	
	//get mapped so we can use "th href"
	@GetMapping("/saveNewList")
	public ModelAndView saveNewList(Model model, HttpSession session) 
	{
		//save list method returns the pin number while saving
		String userPin = guestListService.saveList(guestService.guestArrayList);
		ModelAndView mav = new ModelAndView("GuestListMainPage");
		
		String mainMessage = "List saved! The PIN number for your list  is: " + userPin 
		+ ". Please do not lose this number or you will not be able to access your guest list!";
		model.addAttribute("mainMessage", mainMessage);
		
		return mav;
	}
	
	//allows user to overwrite a list in the database
	@PostMapping("/overwriteList")
	public ModelAndView overwriteList(@ModelAttribute("overwritePin") String overwritePin, Model model) 
	{
		ModelAndView mav = new ModelAndView();

		if(guestListService.checkForValidPin(overwritePin) == false) 
		{
			mav.setViewName("GuestListSavePage");
			String editMainMessage = "Invalid Pin. Please try again.";
			model.addAttribute("editMainMessage", editMainMessage);
			return mav;
		}
		
		//updates list after checks
		guestListService.updateList(overwritePin, guestService.guestArrayList);
		mav.setViewName("GuestListMainPage");
		String mainMessage = "List saved. You can now exit site safely.";
		model.addAttribute("mainMessage", mainMessage);

		return mav;
	}
	
	@PostMapping("/deleteList")
	public ModelAndView deleteList(@ModelAttribute("deletePin") String deletePin, Model model, HttpSession session) 
	{
		ModelAndView mav = new ModelAndView();
		
		//string that instructs user what to do
		String instructions;
		
		//adds list to session in case of accidental delete. 
		//at first I assumed their list would already be in the session, but the user could freshly come to the site and want to delete the list
		//session.setAttribute("allGuests", guestListService.editList(deletePin));

		if(guestListService.deleteList(deletePin) == false) 
		{
			mav.setViewName("GuestListSavePage");
			instructions = "Invalid PIN. Please try again.";
			model.addAttribute("deleteMessage", instructions);
			return mav;
		}

		mav.setViewName("GuestListMainPage");
		instructions = "List deleted from system. If this was an accident, you can go ahead and save your list as new. "
				+ "If this was intentional, once you leave the site, your list will be gone forever.";
		model.addAttribute("mainMessage", instructions);
		
		return mav;
	}
	
	
}
