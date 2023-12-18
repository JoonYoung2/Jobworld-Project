package com.jobworld.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.jobworld.project.dto.request.apply.UserRecruitViewRequestDto;
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
		List<UserRecruitViewRequestDto> list = service.getUserRecruitSearchViewInfo(userSearch);
		model.addAttribute("list", list);
		return "user/search/index";
	}
	
	@PostMapping("/company")
	public String companySearch(String companySearch, String comp_id, Model model){
		List<UserRecruitViewRequestDto> list = service.getCompanyRecruitSearchViewInfo(companySearch, comp_id);
		model.addAttribute("list", list);
		return "company/search/index";
	}
}