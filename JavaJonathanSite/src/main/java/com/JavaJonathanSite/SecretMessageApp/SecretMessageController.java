package com.JavaJonathanSite.SecretMessageApp;

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
public class SecretMessageController {
	
	//string to be displayed to tell user what do on view, each method needs its own to set a default value
	String unscrambleDirectUser = "Please type in the message you want to be unscrambled." + 
			"You will need to provide the password given to the person whom scrambled the message.";	
	
	String scrambleDirectUser = "Please type in the message you want to be scrambled." + 
			"We will provide a password to give to the person you want to read the message. DO NOT SHARE WITH ANYONE ELSE.";
	
	String password = "Your password will be printed here.";
	
	
	
	
	@RequestMapping("/UnscramblePage")
	public ModelAndView populateUnscramble(Model model)
	{
		ModelAndView mav = new ModelAndView("UnscramblePage");
		model.addAttribute("mainMessage", unscrambleDirectUser);
		return mav;
	}
	
	@RequestMapping("/ScramblePage")
	public ModelAndView populateScramble(Model model)
	{
		ModelAndView mav = new ModelAndView("ScramblePage");
		model.addAttribute("mainMessage", scrambleDirectUser);
		model.addAttribute("password", password);
		return mav;
	}
	
	@RequestMapping("/SecretMessageHome")
	public ModelAndView scrambleMainPage()
	{
		ModelAndView mav = new ModelAndView("SecretMessageHome");
		return mav;
	}
	
	
	
	@PostMapping("/unscrambleMessage")
	public ModelAndView unscrambleMessage(Model model, @ModelAttribute("unscrambleUserMessage") String unscrambleUserMessage, 
			@ModelAttribute("unscramblePassword") String unscramblePassword)
	{
		ModelAndView mav = new ModelAndView("UnscramblePage");
		
		//returns true if password is invalid
		if(MessageUnscrambler.unscrambleMessage(unscrambleUserMessage, unscramblePassword)) 
		{
			unscrambleDirectUser = "Invalid password. Please try again.";
			model.addAttribute("mainMessage", unscrambleDirectUser);
			return mav;
		}
		
		unscrambleDirectUser = "Your decode message: " + MessageUnscrambler.decodedMessage;
		
		//adds decoded message to the view after checked
		model.addAttribute("mainMessage", unscrambleDirectUser);
		
		return mav;
	}
	
	
	
	
	
	@GetMapping("/scrambleMessage")
	public ModelAndView scrambleMessage(Model model, @ModelAttribute("scrambleUserMessage") String scrambleUserMessage)
	{
		ModelAndView mav = new ModelAndView("ScramblePage");
		
		//checks to make sure user actually put in a value
		if(scrambleUserMessage.trim().length() == 0) 
		{
			scrambleDirectUser = "Error. Please input text to be scrambled.";
			model.addAttribute("mainMessage", scrambleDirectUser);
			return mav;
		}
		MessageScrambler.generateScrambledMessage(scrambleUserMessage);
		
		scrambleDirectUser = MessageScrambler.codedMessage;
		model.addAttribute("mainMessage", scrambleDirectUser);
		
		password = "Your password: " + MessageScrambler.uniqueCode + ". ONLY SHARE WITH WHOM YOU WANT TO BE ABLE TO READ THE MESSAGE!";
		
		model.addAttribute("password", password);
		
		return mav;
	}
}
