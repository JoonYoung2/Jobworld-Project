package com.jobworld.project.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Apply;
import com.jobworld.project.domain.Company;
import com.jobworld.project.domain.Resume;
import com.jobworld.project.dto.ResumeDTO;
import com.jobworld.project.repository.ApplyRepository;
import com.jobworld.project.repository.CompRepository;
import com.jobworld.project.repository.ResumeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class ApplyService {
	private final ApplyRepository repo;
	private final ResumeRepository resumeRepository;
	private final HttpSession session;

	public ResumeDTO getUserResume() {
		Resume resume = resumeRepository.findByName(session.getAttribute("user_id").toString());
		ResumeDTO dto = setResume(resume);
		return dto;
	}

	private ResumeDTO setResume(Resume resume) {
		ResumeDTO dto = new ResumeDTO();
		dto.setResume_id(resume.getId());
		dto.setResume_title(resume.getTitle());
		dto.setUser_id(resume.getMember().getId());
		dto.setUser_img(resume.getImg());
		return dto;
	}
	
}
