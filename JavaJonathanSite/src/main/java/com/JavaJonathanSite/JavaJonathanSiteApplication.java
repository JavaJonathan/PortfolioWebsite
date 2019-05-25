package com.JavaJonathanSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.sun.glass.ui.Application;

@SpringBootApplication
public class JavaJonathanSiteApplication extends SpringBootServletInitializer {
	
	//@Override
	//protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	//	return application.sources(JavaJonathanSiteApplication.class);
	//}
	
	public static void main(String[] args) {
		SpringApplication.run(JavaJonathanSiteApplication.class, args);
	}


}
