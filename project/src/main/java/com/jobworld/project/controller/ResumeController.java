package com.jobworld.project.controller;

import com.jobworld.project.dto.request.resume.UserResumeRequestDto;
import com.jobworld.project.dto.response.resume.ResumeResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobworld.project.dto.request.resume.ResumeRequestDto;
import com.jobworld.project.dto.response.resume.UserResumeResponseDto;
import com.jobworld.project.service.ResumeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resume")
public class ResumeController {
	private final ResumeService service;
	
	@GetMapping("/info")
	public String resumeInfo(@RequestParam("userId") String userId, Model model) {
		String msg = service.resumeFind(userId);
		if(msg.equals("없음")) {
			return "user/resume/resumeWrite";			
		}else{
			UserResumeResponseDto resume = service.getUserResumeDto(userId);

			model.addAttribute("resume", resume);
			model.addAttribute("msg", msg);
			return "user/resume/writeInfo";
		}
	}
	
	@GetMapping("/write")
	public String resumeWrite() {
		return "user/resume/resumeWrite";
	}
	
	@PostMapping("/write")
	public String resumeWrite(ResumeRequestDto resumeRequestDto, Model model){
		String msg = service.resumeWrite(resumeRequestDto);
		String userId = resumeRequestDto.getUserId();
		if(msg.equals("쓰기 성공")) {
			UserResumeResponseDto resume = service.getUserResumeDto(userId);

			model.addAttribute("resume", resume);
			model.addAttribute("msg", msg);
			return "user/resume/writeInfo";
		}else {
			model.addAttribute("msg", msg);
			return "user/resume/resumeWrite";
		}
	}

	@GetMapping("/update")
	public String resumeUpdate(Long resumeId, Model model) {
		UserResumeResponseDto resume = service.getUserResumeDto(resumeId);
		model.addAttribute("resume", resume);
		return "user/resume/resumeUpdate";
	}
	
	@PostMapping("/personal/update")
	public String resumeImgUpdate(UserResumeRequestDto dto, Model model) {
		UserResumeResponseDto resume = service.personalInfoUpdate(dto);
		model.addAttribute("resume", resume);
		return "user/resume/resumeUpdate";
	}
	
	@PostMapping("update")
	public String resumeUpdate(ResumeRequestDto dto, Model model){
		ResumeResponseDto resumeResponseDto = service.resumeUpdate(dto);
		String msg = "";
		if(resumeResponseDto!=null) {
			msg = "있음";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("resume", dto);
		
		return "user/resume/writeInfo";
	}
}
