package com.jobworld.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jobworld.project.dto.MemberDTO;
import com.jobworld.project.dto.RecruitDTO;
import com.jobworld.project.dto.applyViewDto.UserRecruitViewDTO;
import com.jobworld.project.service.HomeService;
import com.jobworld.project.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService service;
	private final HttpSession session;
	private final HomeService homeService;
	
	@GetMapping("memberLogin")
	public String login() {
		String msg = (String) session.getAttribute("user_id");
		return "user/member/login";
	}
	@PostMapping("memberLogin.do")
	public String login(MemberDTO member, Model model) {
		
		String msg = "";
		
		if(member.getUser_id() == null || member.getUser_id().equals("")) {
			msg = "아이디를 입력해주세요";
		}else if(member.getUser_pw() == null || member.getUser_pw().equals("")) {
			msg = "비밀번호를 입력해주세요";
		}
		
		if(!msg.equals("")) {
			model.addAttribute("member", member);
    		model.addAttribute("msg", msg);
    		return "user/member/login";
    	}
		
		MemberDTO check = service.findUser(member.getUser_id());
		
		if(check != null) {
			if(check.getUser_pw().equals(member.getUser_pw())) {
				List<UserRecruitViewDTO> list = homeService.getUserRecruitViewInfo();
				model.addAttribute("list", list);
				session.setAttribute("user_id", member.getUser_id());
				session.setAttribute("login_type", member.getLogin_type());
				return "user/index";
			}else {
				msg = "비밀번호가 일치하지 않습니다.";
			}
		}else {
			msg = "아이디가 존재하지 않습니다.";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("user_id", member.getUser_id());
		return "user/member/login";
	}
	
	@GetMapping("memberRegister")
	public String register() {
		return "user/member/register";
	}
	
	@PostMapping("memberRegister.do")
	public String register(@Valid MemberDTO member, BindingResult result, Model model) {
		String msg="";
    	if(result.hasErrors()) {		//정규식 검사
			msg = "입력이 올바르지 않습니다.";
			model.addAttribute("member", member);
			model.addAttribute("msg", msg);
			return "user/member/register";
		}
    	
    	if(member.getUser_nm()==null || member.getUser_nm().equals(""))
    		msg = "이름을 입력해주세요.";
    	else if(member.getZip_cd()==null || member.getZip_cd().equals(""))
    		msg = "우편번호를 입력해주세요.";
    	else if(member.getAddress_info()==null || member.getAddress_info().equals(""))
    		msg = "주소를 입력해주세요.";
    	else if(member.getAddress_detail()==null || member.getAddress_detail().equals("")) 
    		msg = "상세주소를 입력해주세요.";
    	else if(!member.getUser_pw().equals(member.getUser_pwCheck()))
    		msg = "비밀번호가 일치하지 않습니다.";
    	
    	if(!msg.equals("")) {
    		model.addAttribute("member", member);
    		model.addAttribute("msg", msg);
    		return "user/member/register";
    	}
    	
    	MemberDTO check = service.findUser(member.getUser_id());
    	System.out.println(check);
    	if(check == null) {
    		service.save(member);
    		msg = "회원가입이 완료되었습니다.";
    		model.addAttribute("msg", msg);	
    		return "user/member/login";
    	}
    	
    	msg = "동일한 아이디가 존재합니다. 다른 아이디를 입력해주세요.";
    	model.addAttribute("member", member);
		model.addAttribute("msg", msg);	
    	return "user/member/register";
	}
	
	@GetMapping("memberLogout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}	