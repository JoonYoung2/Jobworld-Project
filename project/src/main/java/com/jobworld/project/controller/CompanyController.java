package com.jobworld.project.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobworld.project.domain.Company;
import com.jobworld.project.dto.CompDTO;
import com.jobworld.project.service.CompService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
	private final CompService service;
	private final HttpSession session;
	
	@GetMapping("companyLogin")
	public String login() {
		return "company/member/login";
	}
	
	@PostMapping("companyLogin.do")
	public String login(CompDTO comp, RedirectAttributes attr) {
		String msg="";
		boolean pwCheck = true;
		
		Company check = new Company();
		check = service.findId(comp.getComp_id());
		try {
			if(comp.getComp_id() == "" || comp.getComp_id() == null) {
				msg = "아이디를 입력해주세요.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("comp", comp);
				return "redirect:companyLogin";
			}
			if(comp.getComp_pw() == "" || comp.getComp_pw() == null) {
				msg = "비밀번호를 입력해주세요.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("comp", comp);
				return "redirect:companyLogin";
			}
			
			if(check == null) {
				msg = "아이디가 존재하지 않습니다.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("comp", comp);
				return "redirect:companyLogin";
			}
			if(check.getPw().equals(comp.getComp_pw())) {
				pwCheck = false;
			}
			if(pwCheck) {
				msg="비밀번호가 일치하지 않습니다.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("comp", comp);
				return "redirect:companyLogin";
			}
		}catch(Exception e) {
			log.error("CompController login(CompDTO, RedirectAttributes) error --> {}", e);
		}
		if(check != null) {
			session.setAttribute("comp_id", comp.getComp_id());
			return "company/index";
			
		}
		return "company/index";
	}
	
	@GetMapping("companyRegister")
	public String register() {
		return "company/member/register";
	}
	@PostMapping("companyRegister.do")
	public String register(@Valid CompDTO comp, BindingResult result, RedirectAttributes attr) {
		String msg="";
    	boolean pwCheck = true;
    	Company check = null;
    	if(comp.getComp_pw().equals(comp.getComp_pwCheck())) {
			pwCheck = false;
		}
    	if(result.hasErrors()) {		//정규식 검사
			msg = "입력이 올바르지 않습니다.";
			attr.addFlashAttribute("comp", comp);
			attr.addFlashAttribute("msg", msg);
			return "redirect:companyRegister";
		}
    	try {
    		if(pwCheck) {
    			msg="비밀번호가 일치하지 않습니다.";
    			attr.addFlashAttribute("comp", comp);
    			attr.addFlashAttribute("msg", msg);
    			return "redirect:companyRegister";
    		}
    		check = service.findId(comp.getComp_id());
    		if(check != null) {
    			msg="동일한 아이디가 존재합니다.";
    			attr.addFlashAttribute("comp", comp);
    			attr.addFlashAttribute("msg", msg);
    			return "redirect:companyRegister";
    		}
    	}catch(Exception e) {
    		log.error("CompanyController register(CompDTO, BindingResult, RedirectAttributes)", e);
    	}
    	
    	msg = service.join(comp);
    	System.out.println("msg =====> " + msg);
    	if(msg.equals("등록 실패")) {
    		msg="동일한 아이디가 존재합니다.";
			attr.addFlashAttribute("comp", comp);
			attr.addFlashAttribute("msg", msg);
			return "redirect:companyRegister";
    	}
		return "company/member/login";
	}
	@GetMapping("companyLogout")
	public String logout() {
		session.invalidate();
		return "redirect:company";
	}
}

