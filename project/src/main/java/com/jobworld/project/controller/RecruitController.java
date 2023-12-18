package com.jobworld.project.controller;

import java.util.List;

import com.jobworld.project.dto.response.company.recruit.RecruitResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobworld.project.dto.request.company.recruit.RecruitRequestDto;
import com.jobworld.project.dto.request.apply.UserCompanyRecruitInfoRequestDto;
import com.jobworld.project.service.RecruitService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recruit")
public class RecruitController {
	private final RecruitService service;
	
	@GetMapping("/info")
	public String recruitInfo(@RequestParam("compId") String compId, Model model) {
		List<RecruitResponseDto> list = service.recruitResponseList(compId);
	
		if(list != null) {
			model.addAttribute("list", list);
			return "company/recruit/recruitList";
		}
		return "company/recruit/recruitList";
	}
	
	@GetMapping("/write")
	public String recruitWrite(){
		return "company/recruit/recruitWrite";
	}
	
	@PostMapping("/write")
	public String recruitWrite(RecruitRequestDto dto, Model model) {
		String msg = service.save(dto);
		if(msg.equals("등록")) {
			List<RecruitResponseDto> list = service.recruitResponseList(dto.getCompId());
			model.addAttribute("list", list);
			return "company/recruit/recruitList";
			
		}else {
			model.addAttribute("dto", dto);
			return "company/recruit/recruitWrite";
		}
	}
	
	@GetMapping("/user/info")
	public String userRecruitInfo(@RequestParam("recruitId") Long recruitId, Model model) {
		UserCompanyRecruitInfoRequestDto dto = service.recruitInfo(recruitId);
		model.addAttribute("recruit", dto);
		return "user/recruit/recruitInfo";
	}	
}
