package com.carthurnau.learnSongs.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.carthurnau.learnSongs.models.User;
import com.carthurnau.learnSongs.services.UserService;
import com.carthurnau.learnSongs.validators.UserValidator;

@Controller
public class LogRegController {

	
	private final UserService userService;
	private final UserValidator userValidator;
	
	public LogRegController(
			UserService userService, 
			UserValidator userValidator
			) {
		this.userService = userService;
		this.userValidator = userValidator;
	}

	
	@RequestMapping("/")
	public String showWelcome() {
		return "welcome.jsp";
	}
		
	@RequestMapping("/login")
	public String showLoginForm(Model model) {
		
		// create a user instance for the form to use for binding and validation
		User user = new User();
		model.addAttribute("user",user);
		
		return "loginForm.jsp";
	}
	
	@RequestMapping("/register")
	public String showRegistrationForm(Model model) {
		
		// create a user instance for the form to user for binding and validation
		User user = new User();
		model.addAttribute("user",user);
		
		return "registrationForm.jsp";
	}

    @RequestMapping(value="/processRegistrationForm", method=RequestMethod.POST)
    public String registerUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {

    	userValidator.validate(user, result);
    	
    	if (result.hasErrors()) {
    		model.addAttribute("user",user);
            return "registrationForm.jsp";
    	} else {
    		
    		// Note: Login process assumes that register process ensures that
    		// no duplicate emails are allowed
    		
    		boolean isUnique = userService.uniqueUser(user.getEmail());
    		
    		if (isUnique) {
        		User thisUser = userService.registerUser(user);
	    		
        		session.setAttribute("userId", user.getId());
        		session.setAttribute("user", thisUser);
        		
        		model.addAttribute("error","");
        		
        		return "redirect:/home";    			
    		} else {
    			model.addAttribute("error","That email is already registered");
    			model.addAttribute("user",user);
    			return "registrationForm.jsp";
    		}
    		
    	}
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        // if the user is authenticated, save their user id in session
        // else, add error messages and return the login page
    	
      	boolean isAuthentic = userService.authenticateUser(email,password);
    	
    	if (isAuthentic) {
    		
    		ArrayList<User> theseUsers = (ArrayList) userService.findByEmail(email);
    		
    		User thisUser = theseUsers.get(0);
    		
    		session.setAttribute("userId", thisUser.getId()); 
    		session.setAttribute("user", thisUser);
    		
    		return "redirect:/home";
    		
    	} else {
    		
    		model.addAttribute("error","Invalid Credentials. Please try again");
    		return "loginForm.jsp";
    		
    	}
    	
    	
    }
    	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/";
	}
	
	
}
