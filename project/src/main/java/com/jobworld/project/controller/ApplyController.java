package com.jobworld.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jobworld.project.dto.ResumeDTO;
import com.jobworld.project.service.ApplyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ApplyController {
	
	private final ApplyService service;
	
	@GetMapping("apply")
	public String applyInfo(){
		ResumeDTO dto = service.getUserResume();
		return "user/apply/applyInfo";
	}

}
