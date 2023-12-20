package com.jobworld.project.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.jobworld.project.dto.response.company.member.CompanyMemberResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobworld.project.dto.request.company.member.CompanyMemberRequestDto;
import com.jobworld.project.dto.request.company.member.CompanyMultiRequestDto;
import com.jobworld.project.service.CompService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/company")
public class CompanyController {
	private final CompService service;
	private final HttpSession session;
	private static final String directory = "D:\\jobword_test\\project\\src\\main\\webapp\\resources\\company_upload\\";
	
	@GetMapping("/login")
	public String login() {
		return "company/member/login";
	}
	
	@PostMapping("/login")
	public String login(CompanyMemberRequestDto comp, RedirectAttributes attr) {
		String msg="";
		boolean pwCheck = true;
		
		CompanyMemberResponseDto check = service.findId(comp.getCompId());
		try {
			if(comp.getCompId() == "" || comp.getCompId() == null) {
				msg = "아이디를 입력해주세요.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("comp", comp);
				return "redirect:/company/login";
			}
			if(comp.getCompPw() == "" || comp.getCompPw() == null) {
				msg = "비밀번호를 입력해주세요.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("comp", comp);
				return "redirect:/company/login";
			}
			
			if(check == null) {
				msg = "아이디가 존재하지 않습니다.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("comp", comp);
				return "redirect:/company/login";
			}
			if(check.getCompPw().equals(comp.getCompPw())) {
				pwCheck = false;
			}
			if(pwCheck) {
				msg="비밀번호가 일치하지 않습니다.";
				attr.addFlashAttribute("msg", msg);
				attr.addFlashAttribute("comp", comp);
				return "redirect:/company/login";
			}
		}catch(Exception e) {
			log.error("CompController login(CompDTO, RedirectAttributes) error --> {}", e);
		}
		if(check != null) {
			session.setAttribute("comp_id", comp.getCompId());
			return "redirect:/company";
			
		}
		return "redirect:/company";
	}
	
	@GetMapping("/register")
	public String register() {
		return "company/member/register";
	}

	@PostMapping("/register")
	public String register(CompanyMultiRequestDto dto, BindingResult br, RedirectAttributes attr) {
		if(br.hasErrors()) {
			attr.addFlashAttribute("comp", dto);
			attr.addFlashAttribute("msg", "형식이 올바르지 않습니다.");
			return "redirect:/company/register";
		}
		
		String msg = "";
		
		CompanyMemberResponseDto check = service.findId(dto.getCompId());
		
		if(check != null) {
			msg = "동일한 아이디가 존재합니다";
			attr.addFlashAttribute("comp", dto);
			attr.addFlashAttribute("msg", msg);
			return "redirect:/company/register";
		}else {
			msg = "성공";
		}
		
		CompanyMemberRequestDto comp = getCompDto(dto);
		service.join(comp);
		return "company/member/login";
	}

	

	private String compVaild(CompanyMemberRequestDto comp) {
		String msg = "";
		
		if(comp.getCompId() == null || comp.getCompId().equals("")) {
			msg = "아이디를 입력해주세요";
		}else if(comp.getCompPw() == null || comp.getCompPw().equals("")) {
			msg = "비밀번호를 입력해주세요";
		}else if(comp.getCompPwCheck() == null || comp.getCompPwCheck().equals("")) {
			msg = "비밀번호 확인을 입력해주세요";
		}else if(comp.getCompNm() == null || comp.getCompNm().equals("")) {
			msg = "기업명을 입력해주세요";
		}else if(comp.getCompBusinessType() == null || comp.getCompBusinessType().equals("")) {
			msg = "기업형태를 입력해주세요";
		}else if(comp.getCompEmplNum() < 0) {
			msg = "사원 수를 입력해주세요";
		}else if(comp.getCompSize() == null || comp.getCompSize().equals("")) {
			msg = "회사규모를 입력해주세요";
		}else if(comp.getCompSite() == null || comp.getCompSite().equals("")) {
			msg = "회사 사이트를 입력해주세요";
		}else if(comp.getCompBrandImg() == null || comp.getCompBrandImg().equals("")) {
			msg = "브랜드이미지를 등록해주세요";
		}
		
		CompanyMemberResponseDto check = service.findId(comp.getCompId());
		
		if(check != null) {
			msg = "동일한 아이디가 존재합니다";
		}else {
			msg = "성공";
		}
		return msg;
	}
	
	private CompanyMemberRequestDto getCompDto(CompanyMultiRequestDto dto) {
		CompanyMemberRequestDto comp = new CompanyMemberRequestDto();
		
		String compBrandImg = uploadFile(dto.getFile(), dto.getCompId());
		comp.setCompId(dto.getCompId());
		comp.setCompPw(dto.getCompPw());
		comp.setCompPwCheck(dto.getCompPwCheck());
		comp.setCompNm(dto.getCompNm());
		comp.setCompBusinessType(dto.getCompBusinessType());
		comp.setCompEmplNum(dto.getCompEmplNum());
		comp.setCompSize(dto.getCompSize());
		comp.setCompSite(dto.getCompSite());
		comp.setCompBrandImg(compBrandImg);
		return comp;
	}

	private String uploadFile(MultipartFile file, String comp_id) {
		String originalName = file.getOriginalFilename();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
		Calendar cal = Calendar.getInstance();
		String fileName = sdf.format(cal.getTime()) + originalName;
		String path = directory + comp_id + "\\" + fileName;
		File targetFile = new File(path);
		if (targetFile.exists() == false) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/company";
	}
}

