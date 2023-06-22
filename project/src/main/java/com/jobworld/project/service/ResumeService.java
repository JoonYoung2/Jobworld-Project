package com.jobworld.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jobworld.project.domain.Member;
import com.jobworld.project.domain.Resume;
import com.jobworld.project.dto.ResumeDTO;
import com.jobworld.project.repository.MemberRepository;
import com.jobworld.project.repository.ResumeRepository;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ResumeService {
	
	private final ResumeRepository repo;
	private final MemberRepository memberRepository;
	
	private static String dir = "D:\\jobworld_controller\\jobworld_project\\project\\src\\main\\webapp\\resources\\upload\\";
	
//	private ResumeDTO setResume(List<Resume> list) {
//		ResumeDTO dto = new ResumeDTO();
//		dto.setResume_id(list.get(0).getId());
//		dto.setResume_title(list.get(0).getTitle());
//		dto.setUser_id(list.get(0).getMember().getId());
//		dto.setUser_img(list.get(0).getImg());
//		return dto;
//	}
	
	private ResumeDTO setResume(Resume resume) {
		ResumeDTO dto = new ResumeDTO();
		dto.setResume_id(resume.getId());
		dto.setResume_title(resume.getTitle());
		dto.setUser_id(resume.getMember().getId());
		dto.setUser_img(resume.getImg());
		return dto;
	}
	
	public String userImgSave(MultipartFile file, String user_id) {
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
	
	public void folderDelete(String user_id) {
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
	
	@Transactional
	public String resumeWrite(MultipartHttpServletRequest multi) {
		String user_id = multi.getParameter("user_id");
		String resume_title = multi.getParameter("resume_title");
		MultipartFile file = multi.getFile("file");
		if(resume_title == null || resume_title.equals("")) {
			return "제목을 입력해주세요.";
		}
		if (file == null || file.isEmpty()) {
	        return "업로드 파일을 등록해주세요.";
	    }
		String user_img = userImgSave(file, user_id);
		Member member = memberRepository.findOne(user_id);
		Resume resume = Resume.createResume(member, user_id, user_img, resume_title);			
		repo.save(resume);
		return "쓰기 성공";
	}
	
	@Transactional
	public String resumeUpdate(Resume resume) {
		try {
			Resume update = repo.findOne(resume.getId());
			update.setImg(resume.getImg());				
			update.setTitle(resume.getTitle());
			return "수정 완료";
		}catch(Exception e) {
			log.error("ResumeService resumeUpdate(Resume) error --> {}", e);
		}
		return "수정 실패";
	}
	
	public String resumeFind(String user_id) {
		String msg = validateDuplicateResume(user_id);
		return msg;
	}
	
	private String validateDuplicateResume(String user_id) {
        Resume findResumes = repo.findByName(user_id);
        String msg = "";
        if(findResumes != null)
            msg = "있음";
        else 
        	msg = "없음";
        
        return msg;
    }

	public ResumeDTO getUserResume(String user_id) {
		Resume getResume = repo.findByName(user_id);
		ResumeDTO resume = setResume(getResume);
		return resume;
	}

	public ResumeDTO getResumeInfo(int resume_id) {
		Resume getResume = repo.findOne(resume_id);
		ResumeDTO resume = setResume(getResume);
		return resume;
	}
	
	@Transactional
	public ResumeDTO imgUpdate(MultipartHttpServletRequest multi) {
		int resume_id = Integer.parseInt(multi.getParameter("resume_id"));
		String user_id = multi.getParameter("user_id");
		MultipartFile file = multi.getFile("file");
		if (file == null || file.isEmpty()) {
			Resume resume = repo.findOne(resume_id);
			ResumeDTO dto = new ResumeDTO();
			dto.setResume_id(resume.getId());
			dto.setUser_id(resume.getMember().getId());
			dto.setResume_title(resume.getTitle());
			dto.setUser_img(resume.getImg());
			return dto;
	    }
		//폴더 및 파일 삭제
		folderDelete(user_id);
		
		String user_img = userImgSave(file, user_id);
		
		Resume resume = repo.findOne(resume_id);
		resume.setImg(user_img);
		
		ResumeDTO dto = new ResumeDTO();
		dto.setResume_id(resume.getId());
		dto.setUser_id(resume.getMember().getId());
		dto.setResume_title(resume.getTitle());
		dto.setUser_img(resume.getImg());
		return dto;
	}
		
	@Transactional
	public ResumeDTO resumeUpdate(ResumeDTO dto) {
		Resume resume = repo.findOne(dto.getResume_id());
		resume.setTitle(dto.getResume_title());
		
		dto.setResume_id(resume.getId());
		dto.setUser_img(resume.getImg());
		dto.setResume_title(resume.getTitle());
		dto.setUser_id(resume.getMember().getId());
		return dto;
	}
}
