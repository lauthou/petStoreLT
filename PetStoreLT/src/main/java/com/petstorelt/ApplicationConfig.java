package com.petstorelt;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
@ImportResource("classpath:context.xml")
public class ApplicationConfig  extends WebMvcConfigurerAdapter {

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//	    registry.addViewController("/").setViewName("redirect:/templates/index.html");
//	    registry.addViewController("/PetStoreLT").setViewName("redirect:/index.html");
//	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/home").setViewName("home");
////        registry.addViewController("/").setViewName("home");
////        registry.addViewController("/index").setViewName("index");
//		 registry.addViewController("/").setViewName("redirect:/templates/index.html");
        registry.addViewController("/login").setViewName("forward:/templates/login.html");
        registry.addViewController("/admin").setViewName("forward:/templates/index.html");
    }
}


