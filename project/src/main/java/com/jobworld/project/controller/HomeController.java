package com.jobworld.project.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jobworld.project.dto.response.recruitViewResponseDto;
import com.jobworld.project.dto.request.company.CompanyIndexViewDto;
import com.jobworld.project.service.HomeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final HttpSession session;
	private final HomeService service;
	
	/*
	 	User Home UI Start
	 */
	@GetMapping("/")
	public String home(Model model) {
		List<recruitViewResponseDto> list = service.getUserRecruitViewInfo();
		if(list != null) {
			model.addAttribute("list", list);
			return "user/index"; 			
		}
		return "user/index";
	}
	
	/*
	  User Home UI End
	 */
	
	@GetMapping("/company")
	public String company(Model model) {
		if(session.getAttribute("comp_id") == null) {
			return "company/member/login";
		}
		
		List<CompanyIndexViewDto> list = service.getCompanyIndexView();
		if(list == null) {
			return "company/index";
		}
		
		model.addAttribute("comp_nm", list.get(0).getCompNm());
		model.addAttribute("list", list);
		
		return "company/index"; 
	}
	
	@GetMapping("/admin")
	public String admin() {
		int loginType = (int)session.getAttribute("login_type");
		if(loginType != 1) {
			return "redirect:/";  
		}
		return "admin/index"; 
	}
	
}
