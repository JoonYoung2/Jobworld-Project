package com.jobworld.project.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobworld.project.domain.Member;
import com.jobworld.project.domain.Resume;
import com.jobworld.project.dto.ResumeDTO;
import com.jobworld.project.service.MemberService;
import com.jobworld.project.service.ResumeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ResumeController {
	private final ResumeService service;
	private final MemberService memberService;
	
	private static String dir = "D:\\jobworld_controller\\jobworld_project\\project\\src\\main\\webapp\\resources\\upload\\";
	private String userImgSaveFile(MultipartFile file, String user_id) {
		String originalName = file.getOriginalFilename();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
		Calendar cal = Calendar.getInstance();
		String fileName = sdf.format(cal.getTime()) + originalName;
		String path = dir + user_id + "\\" + fileName;
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

	private void folderDelete(String user_id) {
		String path = dir + user_id;
		File folder = new File(path);
		try {
			while (folder.exists()) {
				File[] folder_list = folder.listFiles(); // 파일리스트 얻어오기
				for (int j = 0; j < folder_list.length; j++) {
					folder_list[j].delete(); // 파일 삭제
				}
				if (folder_list.length == 0 && folder.isDirectory()) {
					folder.delete();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@GetMapping("resumeInfo")
	public String resumeInfo(@RequestParam("user_id") String user_id, RedirectAttributes attr) {
		Resume resume = service.resumeFind(user_id);
		if(resume == null) {
			return "user/resume/resumeInfo";			
		}else{
			attr.addFlashAttribute("resume", resume);
			return "user/resume/resumeInfo";
		}
	}
	
	@GetMapping("resumeWrite")
	public String resumeWrite() {
		return "user/resume/resumeWrite";
	}
	@PostMapping("resumeWrite.do")
	public String resumeWrite(MultipartHttpServletRequest multi, RedirectAttributes attr){
		String msg = "";
		String user_id = multi.getParameter("user_id");
		String resume_title = multi.getParameter("resume_title");
		MultipartFile file = multi.getFile("file");
		
		if (file == null || file.isEmpty()) {
	        return "업로드 파일을 등록해주세요.";
	    }
		
		String user_img = userImgSaveFile(file, user_id);
		
		Resume resume = new Resume();
		Member member = memberService.findUser(user_id);
		resume.setMember(member);
		resume.setImg(user_img);
		resume.setTitle(resume_title);
		msg = service.resumeWrite(resume);
		if(msg.equals("쓰기 성공")) {
			return "redirect:/";
		}else {
			attr.addFlashAttribute("resume", resume);
			return "redirect:resumeWrite";
		}
	}
}
