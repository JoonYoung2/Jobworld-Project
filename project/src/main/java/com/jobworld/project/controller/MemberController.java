package com.jobworld.project.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobworld.project.domain.Member;
import com.jobworld.project.dto.MemberDTO;
import com.jobworld.project.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	private final MemberService service;
	private final HttpSession session;
	
	@GetMapping("memberLogin")
	public String login() {
		return "user/member/login";
	}
	@PostMapping("memberLogin.do")
	public String login(MemberDTO member, RedirectAttributes attr) {
		String msg="";
		boolean pwCheck = true;
    	System.out.println("id = " + member.getUser_id() + "pw = " + member.getUser_pw());
		Member check = new Member();
		check = service.findUser(member.getUser_id());
		System.out.println("check ==> " + check);
		try {
			if(member.getUser_id() == "" || member.getUser_id() == null) {
				msg = "아이디를 입력해주세요.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("member", member);
				return "redirect:memberLogin";
			}
			if(member.getUser_pw() == "" || member.getUser_pw() == null) {
				msg = "비밀번호를 입력해주세요.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("member", member);
				return "redirect:memberLogin";
			}
			
			if(check == null) {
				msg = "아이디가 존재하지 않습니다.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("member", member);
				return "redirect:memberLogin";
			}
			if(check.getPw().equals(member.getUser_pw())) {
				pwCheck = false;
			}
			if(pwCheck) {
				msg="비밀번호가 일치하지 않습니다.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("member", member);
				return "redirect:memberLogin";
			}
		}catch(Exception e) {
			log.error("MemberController login(MemberDTO, RedirectAttributes) error --> {}", e);
		}
		session.setAttribute("user_id", check.getId());
		session.setAttribute("login_type", check.getLogin_type());
		return "redirect:/";
	}
	
	@GetMapping("memberRegister")
	public String register() {
		return "user/member/register";
	}
	
	@PostMapping("memberRegister.do")
	public String register(@Valid MemberDTO member, BindingResult result, RedirectAttributes attr) {
		String msg="";
    	boolean pwCheck = true;
    	if(member.getUser_pw().equals(member.getUser_pwCheck())) {
			pwCheck = false;
		}
    	if(result.hasErrors()) {		//정규식 검사
			msg = "입력이 올바르지 않습니다.";
			attr.addFlashAttribute("member", member);
			attr.addFlashAttribute("msg", msg);
			return "redirect:memberRegister";
		}
    	try {
    		if(pwCheck) {
    			msg="비밀번호가 일치하지 않습니다.";
    			attr.addFlashAttribute("member", member);
    			attr.addFlashAttribute("msg", msg);
    			return "redirect:memberRegister";
    		}
    		msg = service.join(member);
    		if(msg.equals("동일한 아이디가 존재합니다.")) {
				attr.addFlashAttribute("member", member);
				attr.addFlashAttribute("msg", msg);
				return "redirect:memberRegister";
			}
    	}catch(Exception e) {
    		log.error("MemberService register(MemberDTO, BindingResult, RedirectAttributes)", e);
    	}
		return "user/member/login";
	}
	
	@GetMapping("memberLogout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}	