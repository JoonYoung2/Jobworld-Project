package com.jobworld.project.controller;

import java.io.PrintWriter;
import java.util.List;

import com.jobworld.project.dto.response.member.MemberResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jobworld.project.dto.request.member.MemberRequestDto;
import com.jobworld.project.dto.request.apply.UserRecruitViewRequestDto;
import com.jobworld.project.service.HomeService;
import com.jobworld.project.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
	private final MemberService service;
	private final HttpSession session;
	private final HomeService homeService;
	
	@GetMapping("/login")
	public String login(HttpServletResponse response) {
		if(session.getAttribute("user_id") == null) {
			return "user/member/login";			
		}
		
		alertAndBack(response, "이미 로그인상태입니다. 새로고침 한번 해 주세요.");
		
		return "redirect:/";
	}
	@PostMapping("/login")
	public String login(MemberRequestDto memberRequestDto, Model model, HttpServletResponse response) {
		
		String msg = "";
		
		if(memberRequestDto.getUserId() == null || memberRequestDto.getUserId().equals("")) {
			msg = "아이디를 입력해주세요";
		}else if(memberRequestDto.getUserPw() == null || memberRequestDto.getUserPw().equals("")) {
			msg = "비밀번호를 입력해주세요";
		}

		if(!msg.equals("")) {
			model.addAttribute("member", memberRequestDto);
    		model.addAttribute("msg", msg);
    		alertAndLoginLocation(response, msg);
    		return "user/member/login";
    	}
		
		MemberResponseDto check = service.findUser(memberRequestDto.getUserId());
		
		if(check != null) {
			if(service.validPwCheck(memberRequestDto.getUserPw(), check.getUserPw())){
				List<UserRecruitViewRequestDto> list = homeService.getUserRecruitViewInfo();
				model.addAttribute("list", list);
				session.setAttribute("user_id", memberRequestDto.getUserId());
				session.setAttribute("login_type", memberRequestDto.getLoginType());
				alertAndBack(response, "로그인 성공!");
				return "user/index";
			} else {
				msg = "비밀번호가 일치하지 않습니다.";
			}
		}else {
			msg = "아이디가 존재하지 않습니다.";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("member", memberRequestDto);
		alertAndLoginLocation(response, msg);
		return "user/member/login";
	}
	
	@GetMapping("/register")
	public String register(HttpServletResponse response) {
		if(session.getAttribute("user_id") == null) {
			return "user/member/register";
		}
		alertAndBack(response, "이미 로그인상태입니다. 새로고침 한번 해 주세요.");
		return "user/member/register";
	}
	
	@PostMapping("/register")
	public String register(@Valid MemberRequestDto memberRequestDto, BindingResult result, Model model) {
		String msg="";
    	if(result.hasErrors()) {		//정규식 검사
			msg = "입력이 올바르지 않습니다.";
			model.addAttribute("member", memberRequestDto);
			model.addAttribute("msg", msg);
			return "user/member/register";
		}

		log.info("사용자 이름 => {}", memberRequestDto.getUserNm());
		log.info("사용자 아이디 => {}", memberRequestDto.getUserId());
		log.info("사용자 비번 => {}", memberRequestDto.getUserPw());
		log.info("사용자 생일 => {}", memberRequestDto.getUserBirthday());
    	if(memberRequestDto.getUserNm()==null || memberRequestDto.getUserNm().equals(""))
    		msg = "이름을 입력해주세요.";
    	else if(memberRequestDto.getZipCd()==null || memberRequestDto.getZipCd().equals(""))
    		msg = "우편번호를 입력해주세요.";
    	else if(memberRequestDto.getAddressInfo()==null || memberRequestDto.getAddressInfo().equals(""))
    		msg = "주소를 입력해주세요.";
    	else if(memberRequestDto.getAddressDetail()==null || memberRequestDto.getAddressDetail().equals(""))
    		msg = "상세주소를 입력해주세요.";
    	else if(!memberRequestDto.getUserPw().equals(memberRequestDto.getUserPwCheck()))
    		msg = "비밀번호가 일치하지 않습니다.";
    	
    	if(!msg.equals("")) {
    		model.addAttribute("member", memberRequestDto);
    		model.addAttribute("msg", msg);
    		return "user/member/register";
    	}
    	
    	boolean memberCheck = service.findUserCheck(memberRequestDto.getUserId());
    	if(memberCheck) {
    		service.save(memberRequestDto);
    		msg = "회원가입이 완료되었습니다.";
    		model.addAttribute("msg", msg);	
    		return "user/member/login";
    	}
    	
    	msg = "동일한 아이디가 존재합니다. 다른 아이디를 입력해주세요.";
    	model.addAttribute("member", memberRequestDto);
		model.addAttribute("msg", msg);	
    	return "user/member/register";
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
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
	
	public static void alertAndLoginLocation(HttpServletResponse response, String msg) {
	    try {
	        response.setContentType("text/html; charset=utf-8");
	        PrintWriter w = response.getWriter();
	        w.write("<script>alert('"+msg+"'); location.href='/member/login'; </script>");
	        w.flush();
	        w.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
}	