package com.jobworld.project.controller;

import java.io.PrintWriter;
import java.util.List;

import com.jobworld.project.dto.response.apply.ApplyResponseDto;
import com.jobworld.project.dto.response.resume.ResumeResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobworld.project.dto.request.apply.ApplyRequestDto;
import com.jobworld.project.dto.request.apply.UserResumeRecruitRequestDto;
import com.jobworld.project.repository.apply.CompApplyStatusDTO;
import com.jobworld.project.repository.apply.UserApplyStatusDTO;
import com.jobworld.project.service.ApplyService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {	
	
	private final ApplyService service;
	
	@GetMapping
	public String applyInfo(@RequestParam("recruitId") Long recruitId, Model model, HttpServletResponse response){
		ResumeResponseDto resumeResponseDto = service.getUserResume();
		if(resumeResponseDto == null) {
			alertAndBack(response, "지원서 작성 후 지원해주시기 바랍니다.");
			return "user/recruit/recruitInfo";
		}
		
		UserResumeRecruitRequestDto applyInfo = service.getApplyInfo(recruitId, resumeResponseDto.getResumeId());
		model.addAttribute("resume", applyInfo);
		return "user/apply/applyInfo";
	}
	
	@PostMapping
	public void applySave(ApplyRequestDto dto, Model model, HttpServletResponse response) {
		String msg = service.applySave(dto);
		if(msg.equals("성공")) {
			alertAndBack(response, "지원이 완료되었습니다.");
		}else {
			alertAndBack(response, msg);
		}
	}
	
	@GetMapping("/info")
	public String userApplyList(Long recruitId, Model model){
		List<ApplyResponseDto> check = service.getApplyInfo(recruitId);
		if(check.size() > 0) {
			List<UserApplyStatusDTO> applyUserList = service.getResumeMemberInfo(check);
			model.addAttribute("list", applyUserList);
			return "company/apply/applyList";
		}
		return "company/apply/applyList";
	}
	
	@PostMapping("/user/list")
	public String userApplyList(ApplyRequestDto dto, Model model){
		List<ApplyResponseDto> check = service.getApplyInfo(dto.getRecruitId());
		if(check.size() > 0) {
			List<UserApplyStatusDTO> applyUserList = service.getResumeMemberInfo(check);
			model.addAttribute("list", applyUserList);
			return "company/apply/applyList";
		}
		return "company/apply/applyList";
	}
	
	@GetMapping("/company/list")
	public String companyApplyList(@RequestParam("userId") String userId, Model model) {
		Long resumeId = service.getResumeId(userId);
		if(resumeId == 0) {
			return "user/apply/applyList";
		}
		
		List<ApplyResponseDto> check = service.getApplyInfo(resumeId);
		if(check.size() > 0) {
			List<CompApplyStatusDTO> applyCompanyList = service.getRecruitCompanyInfo(check);
			model.addAttribute("list", applyCompanyList);
			return "user/apply/applyList";
		}
		return "user/apply/applyList";
	}
	
	@PostMapping("/cancel")
	public String applyCancel(ApplyDto dto, Model model) {
		System.out.println("applyId = " + dto.getApplyId());
		service.applyCancel(dto.getApplyId());
		Long resumeId = service.getResumeId(dto.getUserId());
		if(resumeId == 0) {
			return "user/apply/applyList";
		}
		
		List<ApplyResponseDto> check = service.getApplyInfo(resumeId);
		if(check.size() > 0) {
			List<CompApplyStatusDTO> applyCompanyList = service.getRecruitCompanyInfo(check);
			model.addAttribute("list", applyCompanyList);
			return "user/apply/applyList";
		}
		return "user/apply/applyList";
	}
	
	@PostMapping("/state/update")
	public String applyStateUpdate(Long applyId, int state, Model model) {
		Long recruitId = service.stateUpdate(applyId, state);
		List<ApplyResponseDto> check = service.getApplyInfo(recruitId);

		if(check.size() > 0) {
			List<UserApplyStatusDTO> applyUserList = service.getResumeMemberInfo(check);
			model.addAttribute("list", applyUserList);
			return "company/apply/applyList";
		}
		return "company/apply/applyList";
	}
	
	@Data
	static class ApplyDto{
		private Long applyId;
		private String userId;
	}
	
	public static void alertAndBack(HttpServletResponse response, String msg) {
	    try {
	        response.setContentType("text/html; charset=utf-8");
	        PrintWriter w = response.getWriter();
	        w.write("<script>alert('"+msg+"'); window.close();</script>");
	        w.flush();
	        w.close();
	    } catch(Exception e) {
	        e.printStackTrace(); 
	    }
	}
}