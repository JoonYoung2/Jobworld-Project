package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Apply;
import com.jobworld.project.domain.Company;
import com.jobworld.project.domain.Recruit;
import com.jobworld.project.domain.Resume;
import com.jobworld.project.dto.ApplyDTO;
import com.jobworld.project.dto.RecruitDTO;
import com.jobworld.project.dto.ResumeDTO;
import com.jobworld.project.repository.ApplyRepository;
import com.jobworld.project.repository.CompRepository;
import com.jobworld.project.repository.RecruitRepository;
import com.jobworld.project.repository.ResumeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplyService {
	private final ApplyRepository repo;
	private final ResumeRepository resumeRepository;
	private final RecruitRepository recruitRepository;
	private final HttpSession session;

	private List<ResumeDTO> setResumeListDto(List<Resume> resumeList) {

		List<ResumeDTO> list = new ArrayList<>();

		for (int i = 0; i < resumeList.size(); ++i) {
			ResumeDTO dto = new ResumeDTO();
			dto.setResume_id(resumeList.get(i).getId());
			dto.setUser_id(resumeList.get(i).getMember().getId());
			dto.setResume_title(resumeList.get(i).getTitle());
			dto.setUser_img(resumeList.get(i).getImg());
			list.add(dto);
		}
		return list;
	}

	private List<Resume> setResumeList(List<ApplyDTO> check) {
		List<Resume> list = new ArrayList<>();
		for (int i = 0; i < check.size(); ++i) {
			Resume resume = resumeRepository.findOne(check.get(i).getResume_id());
			list.add(resume);
		}
		return list;
	}

	private ResumeDTO setResume(Resume resume) {
		ResumeDTO dto = new ResumeDTO();
		dto.setResume_id(resume.getId());
		dto.setResume_title(resume.getTitle());
		dto.setUser_id(resume.getMember().getId());
		dto.setUser_img(resume.getImg());
		return dto;
	}

	private RecruitDTO setRecruit(Recruit recruit) {
		RecruitDTO dto = new RecruitDTO();
		dto.setRecruit_id(recruit.getId());
		dto.setComp_id(recruit.getCompany().getId());
		dto.setRecruit_career(recruit.getCareer());
		dto.setRecruit_education(recruit.getEducation());
		dto.setRecruit_employment(recruit.getEmployment());
		dto.setRecruit_salary(recruit.getSalary());
		dto.setRecruit_area(recruit.getArea());
		dto.setRecruit_time(recruit.getTime());
		dto.setRecruit_start_date(recruit.getStartDate());
		dto.setRecruit_end_date(recruit.getEndDate());
		dto.setRecruit_open_type(recruit.getOpenType());
		return dto;
	}

	public ResumeDTO getUserResume() {
		List<Resume> list = resumeRepository.findByName(session.getAttribute("user_id").toString());
		Resume resume = list.get(0);
		ResumeDTO dto = setResume(resume);
		return dto;
	}

	public RecruitDTO getRecruitInfo(Long recruit_id) {
		Recruit recruit = recruitRepository.findOne(recruit_id);
		RecruitDTO dto = setRecruit(recruit);
		return dto;
	}

	@Transactional
	public String applySave(ApplyDTO dto) {
		Resume resume = resumeRepository.findOne(dto.getResume_id());
		Recruit recruit = recruitRepository.findOne(dto.getRecruit_id());
		List<Apply> applyCheck = repo.checkApplyUser(dto.getRecruit_id(), dto.getResume_id());
		System.out.println("size == > " + applyCheck.size());
		if (applyCheck.size() > 0) {
			return "이미 지원하셨습니다.";
		} else {
			Apply apply = setApply(resume, recruit);
			repo.save(apply);
			return "성공";
		}
	}

	private Apply setApply(Resume resume, Recruit recruit) {
		Apply apply = Apply.setApply(resume, recruit);
		return apply;
	}

	public List<ApplyDTO> getApplyInfo(Long recruit_id) {
		List<Apply> apply = repo.getApplyInfo(recruit_id);
		List<ApplyDTO> list = Apply.setApplyDto(apply);
		return list;
	}

	public List<ResumeDTO> getResumeInfo(List<ApplyDTO> check) {
		List<Resume> resumeList = setResumeList(check);
		List<ResumeDTO> list = setResumeListDto(resumeList);
		return list;
	}

}