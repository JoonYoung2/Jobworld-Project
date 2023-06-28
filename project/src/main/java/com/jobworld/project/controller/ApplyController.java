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
import com.jobworld.project.repository.apply.CompApplyStatusDTO;
import com.jobworld.project.repository.apply.UserApplyStatusDTO;
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
		if(resume == null) {
			RecruitDTO dto = recruitService.recruitInfo(recruit_id);
			model.addAttribute("msg", "지원서 작성 후 지원해주시기 바랍니다.");
			model.addAttribute("recruit", dto);
			return "user/recruit/recruitInfo";
		}
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
		System.out.println(check.size());
		if(check.size() > 0) {
			List<UserApplyStatusDTO> applyUserList = service.getResumeMemberInfo(check);
			model.addAttribute("list", applyUserList);
			return "company/apply/applyList";
		}
		return "company/apply/applyList";
	}
	
	@GetMapping("companyApplyList")
	public String companyApplyList(@RequestParam("user_id") String user_id, Model model) {
		int resume_id = service.getResumeId(user_id);
		if(resume_id == 0) {
			return "user/apply/applyList";
		}
		
		List<ApplyDTO> check = service.getApplyInfo(resume_id);
		if(check.size() > 0) {
			List<CompApplyStatusDTO> applyCompanyList = service.getRecruitCompanyInfo(check);
			model.addAttribute("list", applyCompanyList);
			return "user/apply/applyList";
		}
		return "user/apply/applyList";
	}
	
	@PostMapping("applyCancel.do")
	public String applyCancel(Long apply_id, String user_id, Model model) {
		service.applyCancel(apply_id);
		int resume_id = service.getResumeId(user_id);
		if(resume_id == 0) {
			return "user/apply/applyList";
		}
		
		List<ApplyDTO> check = service.getApplyInfo(resume_id);
		if(check.size() > 0) {
			List<CompApplyStatusDTO> applyCompanyList = service.getRecruitCompanyInfo(check);
			model.addAttribute("list", applyCompanyList);
			return "user/apply/applyList";
		}
		
		return "user/apply/applyList";
		
	}
	
	@PostMapping("applyStateUpdate.do")
	public String applyStateUpdate(Long apply_id, int state, Model model) {
		Long recruit_id = service.stateUpdate(apply_id, state);
		List<ApplyDTO> check = service.getApplyInfo(recruit_id);
		System.out.println(check.size());
		if(check.size() > 0) {
			List<UserApplyStatusDTO> applyUserList = service.getResumeMemberInfo(check);
			model.addAttribute("list", applyUserList);
			return "company/apply/applyList";
		}
		return "company/apply/applyList";
	}
}
