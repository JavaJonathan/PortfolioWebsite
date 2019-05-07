package com.JavaJonathanSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.sun.glass.ui.Application;

@SpringBootApplication
public class JavaJonathanSiteApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JavaJonathanSiteApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JavaJonathanSiteApplication.class, args);
	}

	//public static void main(String[] args) {
		//SpringApplication.run(JavaJonathanSiteApplication.class, args);
	//}

}
