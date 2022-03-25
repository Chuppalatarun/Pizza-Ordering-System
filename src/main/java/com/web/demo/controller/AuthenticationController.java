package com.web.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.demo.model.User;
import com.web.demo.service.MyUserDetails;
//import com.web.demo.service.UserService;
import com.web.demo.service.UserService;

import global.GlobalData;

@RestController
public class AuthenticationController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@GetMapping("/login")
	public ModelAndView showlogin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Login");
		GlobalData.cart.clear();
		return modelAndView;
	}
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user",user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@PostMapping("/register")
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult,ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		//check for the validations
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage","Please correct the errors in the registration form!");
			modelMap.addAttribute("bindingResult",bindingResult);
		}
		// we will save the user if, no binding errors
		else if(userService.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage","user already exists!");
		}
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage","user is registered succesfully!");
		}
		modelAndView.addObject("user",new User());
		modelAndView.setViewName("registration");
		return modelAndView;
	}

}
