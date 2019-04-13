package com.JavaJonathanSite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController 
{
	
	@GetMapping("/Home")
	public ModelAndView homePageView() 
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Home");
		return mv;
	}
	
	@GetMapping("/AboutMePage")
	public ModelAndView aboutMePageView() 
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AboutMePage");
		return mv;
	}
	
	@GetMapping("/ProjectPage")
	public ModelAndView projectPageView() 
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ProjectPage");
		return mv;
	}
	
	
	
	
	
	
}
