package com.jobworld.project.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobworld.project.dto.CompDTO;
import com.jobworld.project.dto.request.CompanyMultiDTO;
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
		
		CompDTO check = new CompDTO();
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
			if(check.getComp_pw().equals(comp.getComp_pw())) {
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
			return "redirect:company";
			
		}
		return "redirect:company";
	}
	
	@GetMapping("companyRegister")
	public String register() {
		return "company/member/register";
	}
	@PostMapping("companyRegister.do")
	public String register(CompanyMultiDTO dto, BindingResult br, RedirectAttributes attr) {
//		String msg = "";
//		CompDTO comp = getCompDto(multi);
//		msg = compVaild(comp);
//		if(!msg.equals("성공")) {
//			attr.addFlashAttribute("comp", comp);
//			attr.addFlashAttribute("msg", msg);
//			return "redirect:companyRegister";
//		}
//		msg = service.join(comp);
//    	if(msg.equals("등록 실패")) {
//    		msg="동일한 아이디가 존재합니다.";
//			attr.addFlashAttribute("comp", comp);
//			attr.addFlashAttribute("msg", msg);
//			return "redirect:companyRegister";
//    	}
//		return "company/member/login";
		if(br.hasErrors()) {
			attr.addFlashAttribute("comp", dto);
			attr.addFlashAttribute("msg", "형식이 올바르지 않습니다.");
			return "redirect:companyRegister";
		}
		
		String msg = "";
		
		CompDTO check = service.findId(dto.getComp_id());
		
		if(check != null) {
			msg = "동일한 아이디가 존재합니다";
			attr.addFlashAttribute("comp", dto);
			attr.addFlashAttribute("msg", msg);
			return "redirect:companyRegister";
		}else {
			msg = "성공";
		}
		
		CompDTO comp = getCompDto(dto);
		service.join(comp);
		return "company/member/login";
	}

	

	private String compVaild(CompDTO comp) {
		String msg = "";
		
		if(comp.getComp_id() == null || comp.getComp_id().equals("")) {
			msg = "아이디를 입력해주세요";
		}else if(comp.getComp_pw() == null || comp.getComp_pw().equals("")) {
			msg = "비밀번호를 입력해주세요";
		}else if(comp.getComp_pwCheck() == null || comp.getComp_pwCheck().equals("")) {
			msg = "비밀번호 확인을 입력해주세요";
		}else if(comp.getComp_nm() == null || comp.getComp_nm().equals("")) {
			msg = "기업명을 입력해주세요";
		}else if(comp.getComp_business_type() == null || comp.getComp_business_type().equals("")) {
			msg = "기업형태를 입력해주세요";
		}else if(comp.getComp_empl_num() < 0) {
			msg = "사원 수를 입력해주세요";
		}else if(comp.getComp_size() == null || comp.getComp_size().equals("")) {
			msg = "회사규모를 입력해주세요";
		}else if(comp.getComp_site() == null || comp.getComp_site().equals("")) {
			msg = "회사 사이트를 입력해주세요";
		}else if(comp.getComp_brand_img() == null || comp.getComp_brand_img().equals("")) {
			msg = "브랜드이미지를 등록해주세요";
		}
		
		CompDTO check = service.findId(comp.getComp_id());
		
		if(check != null) {
			msg = "동일한 아이디가 존재합니다";
		}else {
			msg = "성공";
		}
		return msg;
	}
	
	private CompDTO getCompDto(CompanyMultiDTO dto) {
		CompDTO comp = new CompDTO();
		
		String comp_brand_img = uploadFile(dto.getFile(), dto.getComp_id());
		comp.setComp_id(dto.getComp_id());
		comp.setComp_pw(dto.getComp_pw());
		comp.setComp_pwCheck(dto.getComp_pwCheck());
		comp.setComp_nm(dto.getComp_nm());
		comp.setComp_business_type(dto.getComp_business_type());
		comp.setComp_empl_num(dto.getComp_empl_num());
		comp.setComp_size(dto.getComp_size());
		comp.setComp_site(dto.getComp_site());
		comp.setComp_brand_img(comp_brand_img);
		return comp;
	}

	private CompDTO getCompDto(MultipartHttpServletRequest multi) {
		CompDTO dto = new CompDTO();
		String comp_id = multi.getParameter("comp_id");
		String comp_pw = multi.getParameter("comp_pw");
		String comp_pwCheck = multi.getParameter("comp_pwCheck");
		String comp_nm = multi.getParameter("comp_nm");
		String comp_business_type = multi.getParameter("comp_business_type");
		int comp_empl_num = Integer.parseInt(multi.getParameter("comp_empl_num"));
		String comp_size = multi.getParameter("comp_size");
		String comp_site = multi.getParameter("comp_site");
		String comp_brand_img;
		MultipartFile file = multi.getFile("file");
		if(file == null || file.isEmpty()) {
			dto.setComp_id(comp_id);
			dto.setComp_pw(comp_pw);
			dto.setComp_pwCheck(comp_pwCheck);
			dto.setComp_nm(comp_nm);
			dto.setComp_business_type(comp_business_type);
			dto.setComp_empl_num(comp_empl_num);
			dto.setComp_size(comp_size);
			dto.setComp_site(comp_site);
			return dto;
		}
		
		comp_brand_img = uploadFile(file, comp_id);
		dto.setComp_id(comp_id);
		dto.setComp_pw(comp_pw);
		dto.setComp_pwCheck(comp_pwCheck);
		dto.setComp_nm(comp_nm);
		dto.setComp_business_type(comp_business_type);
		dto.setComp_empl_num(comp_empl_num);
		dto.setComp_size(comp_size);
		dto.setComp_site(comp_site);
		dto.setComp_brand_img(comp_brand_img);
		
		return dto;
	}

	private String uploadFile(MultipartFile file, String comp_id) {
		String directory = "D:\\jobworld_controller\\jobworld_project\\project\\src\\main\\webapp\\resources\\company_upload\\";
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

	@GetMapping("companyLogout")
	public String logout() {
		session.invalidate();
		return "redirect:company";
	}
}

