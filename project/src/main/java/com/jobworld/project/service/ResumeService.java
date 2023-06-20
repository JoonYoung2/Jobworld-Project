package com.jobworld.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Resume;
import com.jobworld.project.repository.ResumeRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ResumeService {
	
	private final ResumeRepository repo;
	
	@Transactional
	public String resumeWrite(Resume resume) {
		try {
			repo.save(resume);
			return "쓰기 성공";
		}catch(Exception e){
			log.error("ResumeService resumeWrite(Resume) error --> {}", e);
		}
		return "쓰기 실패";
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
		System.out.println("gdgdgdgdgdgd");
        List<Resume> findResumes = repo.findByName(user_id);
        System.out.println("gdgdgdgdgdgd2");
        String msg = "";
        if(!findResumes.isEmpty())
            msg = "있음";
        else 
        	msg = "없음";
        
        return msg;
    }
}
