package com.jobworld.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.jobworld.project.dto.applyViewDto.UserRecruitViewDTO;
import com.jobworld.project.service.SearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SearchController {
	private final SearchService service;
	@PostMapping("/userSearch.do")
	public String userSearch(String userSearch, Model model){
		List<UserRecruitViewDTO> list = service.getUserRecruitSearchViewInfo(userSearch);
		model.addAttribute("list", list);
		return "user/search/index";
	}
	
	@PostMapping("/companySearch.do")
	public String companySearch(String companySearch, String comp_id, Model model){
		List<UserRecruitViewDTO> list = service.getCompanyRecruitSearchViewInfo(companySearch, comp_id);
		model.addAttribute("list", list);
		return "company/search/index";
	}
}