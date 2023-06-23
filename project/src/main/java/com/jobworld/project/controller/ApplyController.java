package com.jobworld.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobworld.project.dto.ApplyDTO;
import com.jobworld.project.dto.RecruitDTO;
import com.jobworld.project.dto.ResumeDTO;
import com.jobworld.project.service.ApplyService;
import com.jobworld.project.service.RecruitService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ApplyController {
	
	private final ApplyService service;
	private final HttpSession session;
	private final RecruitService recruitService;
	
	@GetMapping("apply")
	public String applyInfo(@RequestParam("recruit_id") Long recruit_id, Model model){
		if(session.getAttribute("user_id") == null) {
			RecruitDTO dto = recruitService.recruitInfo(recruit_id);
			model.addAttribute("msg", "로그인 후 지원이 가능합니다.");
			model.addAttribute("recruit", dto);
			return "user/recruit/recruitInfo";
		}
		ResumeDTO resume = service.getUserResume();
		model.addAttribute("recruit_id", recruit_id);
		model.addAttribute("resume", resume);
		return "user/apply/applyInfo";
	}
	
	@PostMapping("apply.do")
	public String applySave(ApplyDTO dto, Model model) {
		String msg = service.applySave(dto);
		if(msg.equals("성공")) {
			RecruitDTO recruit = service.getRecruitInfo(dto.getRecruit_id());
			model.addAttribute("recruit_id", dto.getRecruit_id());
			model.addAttribute("recruit", recruit);
			return "user/recruit/recruitInfo";
		}else {
			ResumeDTO resume = service.getUserResume();
			model.addAttribute("msg", msg);
			model.addAttribute("recruit_id", dto.getRecruit_id());
			model.addAttribute("resume", resume);
			return "user/apply/applyInfo"; 
		}
	}
	
	@PostMapping("userApplyList.do")
	public String userApplyList(ApplyDTO dto, Model model){
		List<ApplyDTO> check = service.getApplyInfo(dto.getRecruit_id());
		if(check.size() > 0) {
			List<ResumeDTO> resumeList = service.getResumeInfo(check);
			model.addAttribute("list", resumeList);
			return "company/apply/applyList";
		}
		return "company/apply/applyList";
	}
	
}
