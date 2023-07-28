package com.jobworld.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.jobworld.project.dto.applyViewDto.UserRecruitViewDTO;
import com.jobworld.project.service.SearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SearchController {
	private final SearchService service;
	@PostMapping("/userSearch.do")
	public String userSearch(String userSearch){
		List<UserRecruitViewDTO> list = service.getUserRecruitSearchViewInfo(userSearch);
		return "search/index";
	}
}
