package com.jobworld.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobworld.project.dto.RecruitDTO;
import com.jobworld.project.dto.applyViewDto.UserCompanyRecruitInfoDTO;
import com.jobworld.project.service.RecruitService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecruitController {
	private final RecruitService service;
	
	@GetMapping("/recruitInfo")
	public String recruitInfo(@RequestParam("comp_id") String comp_id, Model model) {
		List<RecruitDTO> list = service.recruitList(comp_id);
	
		if(list != null) {
			model.addAttribute("list", list);
			return "company/recruit/recruitList";
		}
		return "company/recruit/recruitList";
	}
	
	@GetMapping("recruitWrite")
	public String recruitWrite(){
		return "company/recruit/recruitWrite";
	}
	
	@PostMapping("recruitWrite.do")
	public String recruitWrite(RecruitDTO dto, Model model) {
		String msg = service.save(dto);
		if(msg.equals("등록")) {
			List<RecruitDTO> list = service.recruitList(dto.getComp_id());
			model.addAttribute("list", list);
			return "company/recruit/recruitList";
			
		}else {
			model.addAttribute("dto", dto);
			return "company/recruit/recruitWrite";
		}
	}
	
	@GetMapping("recruitInfo.go")
	public String userRecruitInfo(@RequestParam("recruit_id") Long recruit_id, Model model) {
		UserCompanyRecruitInfoDTO dto = service.recruitInfo(recruit_id);
		model.addAttribute("recruit", dto);
		return "user/recruit/recruitInfo";
	}	
}
