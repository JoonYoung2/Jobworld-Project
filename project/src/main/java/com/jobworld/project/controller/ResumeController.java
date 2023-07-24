package com.jobworld.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jobworld.project.dto.ResumeDTO;
import com.jobworld.project.dto.resumeInfoDto.UserResumeDTO;
import com.jobworld.project.service.ResumeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ResumeController {
	private final ResumeService service;
	
	@GetMapping("resumeInfo")
	public String resumeInfo(@RequestParam("user_id") String user_id, Model model) {
		String msg = service.resumeFind(user_id);
		if(msg.equals("없음")) {
			return "user/resume/writeInfo";			
		}else{
			UserResumeDTO resume = service.getUserResumeDto(user_id);

			model.addAttribute("resume", resume);
			model.addAttribute("msg", msg);
			return "user/resume/writeInfo";
		}
	}
	
	@GetMapping("resumeWrite")
	public String resumeWrite() {
		return "user/resume/resumeWrite";
	}
	@PostMapping("resumeWrite.do")
	public String resumeWrite(MultipartHttpServletRequest multi, Model model){
		String msg = service.resumeWrite(multi);
		String user_id = multi.getParameter("user_id");
		if(msg.equals("쓰기 성공")) {
			ResumeDTO resume = service.getResume(user_id);
			System.out.println(resume.getResume_id());
			model.addAttribute("resume", resume);
			model.addAttribute("msg", msg);
			return "user/resume/writeInfo";
		}else {
			model.addAttribute("msg", msg);
			return "user/resume/resumeWrite";
		}
	}
	@GetMapping("resumeUpdate")
	public String resumeUpdate(int resume_id, Model model) {
		UserResumeDTO resume = service.getUserResumeDto(resume_id);
		model.addAttribute("resume", resume);
		return "user/resume/resumeUpdate";
	}
	
	@PostMapping("personalInfoUpdate.do")
	public String resumeImgUpdate(MultipartHttpServletRequest multi, Model model) {
		UserResumeDTO resume = service.personalInfoUpdate(multi);
		model.addAttribute("resume", resume);
		return "user/resume/resumeUpdate";
	}
	
	@PostMapping("resumeUpdate.do")
	public String resumeUpdate(ResumeDTO dto, Model model){
		ResumeDTO resume = service.resumeUpdate(dto);
		String msg = "";
		if(resume!=null) {
			msg = "있음";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("resume", dto);
		
		return "user/resume/writeInfo";
	}
}
