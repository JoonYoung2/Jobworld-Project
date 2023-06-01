package com.jobworld.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Resume;
import com.jobworld.project.repository.ResumeRepository;

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
			return "쓰기 완료";
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
		return "수정 완료";
	}
}
