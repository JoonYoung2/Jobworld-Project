package com.jobworld.project.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String resumeInfo(@RequestParam("user_id") String user_id, Model model) {
		String msg = service.resumeFind(user_id);
		if(msg.equals("없음")) {
			return "user/resume/writeInfo";			
		}else{
			ResumeDTO resume = service.getUserResume(user_id);
			System.out.println(resume.getResume_id());
			model.addAttribute("resume", resume);
			model.addAttribute("msg", msg);
			return "user/resume/writeInfo";
		}
	}
	
	@GetMapping("resumeWrite")
	public String resumeWrite() {
		return "user/resume/resumeWrite";
	}
	@PostMapping("resumeWrite.do")
	public String resumeWrite(MultipartHttpServletRequest multi, Model model){
		String msg = service.resumeWrite(multi);
		String user_id = multi.getParameter("user_id");
		if(msg.equals("쓰기 성공")) {
			ResumeDTO resume = service.getUserResume(user_id);
			System.out.println(resume.getResume_id());
			model.addAttribute("resume", resume);
			model.addAttribute("msg", msg);
			return "user/resume/writeInfo";
		}else {
			model.addAttribute("msg", msg);
			return "user/resume/resumeWrite";
		}
	}
	@GetMapping("resumeUpdate")
	public String resumeUpdate(int resume_id, Model model) {
		ResumeDTO resume = service.getResumeInfo(resume_id);
		model.addAttribute("resume", resume);
		return "user/resume/resumeUpdate";
	}
	
	@PostMapping("imgUpdate.do")
	public String resumeImgUpdate(MultipartHttpServletRequest multi, Model model) {
		ResumeDTO resume = service.imgUpdate(multi);
		String msg = "";
		if(resume!=null) {
			msg = "있음";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("resume", resume);
		return "user/resume/writeInfo";
	}
	
	@PostMapping("resumeUpdate.do")
	public String resumeUpdate(ResumeDTO dto, Model model){
		ResumeDTO resume = service.resumeUpdate(dto);
		String msg = "";
		if(resume!=null) {
			msg = "있음";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("resume", dto);
		
		return "user/resume/writeInfo";
	}
}
