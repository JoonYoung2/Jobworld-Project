package com.jobworld.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@Autowired
	private HttpSession session;
	
	@GetMapping("/")
	public String home() {
		return "user/index"; 
	}
	
	@GetMapping("company")
	public String company() {
		return "company/index"; 
	}
	
	@GetMapping("admin")
	public String admin() {
		int loginType = (int)session.getAttribute("login_type");
		if(loginType != 1) {
			return "redirect:/";  
		}
		return "admin/index"; 
	}
	
}
