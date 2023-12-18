package com.jobworld.project.controller;

import java.io.PrintWriter;
import java.util.List;

import com.jobworld.project.dto.response.resume.ResumeResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobworld.project.dto.ApplyDTO;
import com.jobworld.project.dto.applyViewDto.UserResumeRecruitDTO;
import com.jobworld.project.repository.apply.CompApplyStatusDTO;
import com.jobworld.project.repository.apply.UserApplyStatusDTO;
import com.jobworld.project.service.ApplyService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ApplyController {	
	
	private final ApplyService service;
	
	@GetMapping("apply")
	public String applyInfo(@RequestParam("recruitId") Long recruitId, Model model, HttpServletResponse response){
		ResumeResponseDto resumeResponseDto = service.getUserResume();
		if(resumeResponseDto == null) {
//			UserCompanyRecruitInfoDTO dto = recruitService.recruitInfo(recruit_id);
//			model.addAttribute("msg", "지원서 작성 후 지원해주시기 바랍니다.");
//			model.addAttribute("recruit", dto);
			alertAndBack(response, "지원서 작성 후 지원해주시기 바랍니다.");
			return "user/recruit/recruitInfo";
		}
		
		UserResumeRecruitDTO applyInfo = service.getApplyInfo(recruitId, resumeResponseDto.getResumeId());
		model.addAttribute("resume", applyInfo);
		return "user/apply/applyInfo";
	}
	
																																														
	
	@PostMapping("apply.do")
	public void applySave(ApplyDTO dto, Model model, HttpServletResponse response) {
		String msg = service.applySave(dto);
		if(msg.equals("성공")) {
//			RecruitDTO recruit = service.getRecruitInfo(dto.getRecruit_id());
			alertAndBack(response, "지원이 완료되었습니다.");
//			model.addAttribute("msg", "지원이 완료되었습니다.");
//			model.addAttribute("recruit", recruit);
//			return "user/recruit/recruitInfo";
		}else {
//			UserResumeRecruitDTO resume = service.getApplyInfo(dto.getRecruit_id(), dto.getResume_id());
			alertAndBack(response, msg);
//			model.addAttribute("msg", msg);
//			model.addAttribute("resume", resume);
//			return "user/apply/applyInfo"; 
		}
	}
	
	@GetMapping("applyUserInfo")
	public String userApplyList(Long recruit_id, Model model){
		List<ApplyDTO> check = service.getApplyInfo(recruit_id);
		if(check.size() > 0) {
			List<UserApplyStatusDTO> applyUserList = service.getResumeMemberInfo(check);
			model.addAttribute("list", applyUserList);
			return "company/apply/applyList";
		}
		return "company/apply/applyList";
	}
	
	@PostMapping("userApplyList.do")
	public String userApplyList(ApplyDTO dto, Model model){
		List<ApplyDTO> check = service.getApplyInfo(dto.getRecruit_id());
		if(check.size() > 0) {
			List<UserApplyStatusDTO> applyUserList = service.getResumeMemberInfo(check);
			model.addAttribute("list", applyUserList);
			return "company/apply/applyList";
		}
		return "company/apply/applyList";
	}
	
	@GetMapping("companyApplyList")
	public String companyApplyList(@RequestParam("user_id") String user_id, Model model) {
		Long resume_id = service.getResumeId(user_id);
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
	public String applyCancel(ApplyDto dto, Model model) {
		System.out.println("applyId = " + dto.getApply_id());
		service.applyCancel(dto.getApply_id());
		Long resume_id = service.getResumeId(dto.getUser_id());
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
	
	@Data
	static class ApplyDto{
		private Long apply_id;
		private String user_id;
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