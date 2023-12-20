package com.jobworld.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.jobworld.project.dto.response.recruitViewResponseDto;
import com.jobworld.project.service.SearchService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
	private final SearchService service;
	@PostMapping("/user")
	public String userSearch(String userSearch, Model model){
		List<recruitViewResponseDto> list = service.getUserRecruitSearchViewInfo(userSearch);
		model.addAttribute("list", list);
		return "user/search/index";
	}
	
	@PostMapping("/company")
	public String companySearch(String companySearch, String compId, Model model){
		List<recruitViewResponseDto> list = service.getCompanyRecruitSearchViewInfo(companySearch, compId);
		model.addAttribute("list", list);
		return "company/search/index";
	}
}