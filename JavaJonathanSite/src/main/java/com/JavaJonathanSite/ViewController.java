package com.JavaJonathanSite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController 
{
	
	@GetMapping(value= {"/Home", "/", "/home"})
	public ModelAndView homePageView() 
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Home");
		return mv;
	}
	
	@GetMapping(value= {"/AboutMePage", "/AboutMe"})
	public ModelAndView aboutMePageView() 
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AboutMePage");
		return mv;
	}
	
	@GetMapping(value= {"/ProjectPage", "/Projects"})
	public ModelAndView projectPageView() 
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ProjectPage");
		return mv;
	}
	
	
	
	
	
	
}
