package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Apply;
import com.jobworld.project.domain.Company;
import com.jobworld.project.domain.Member;
import com.jobworld.project.domain.Recruit;
import com.jobworld.project.domain.Resume;
import com.jobworld.project.dto.ApplyDTO;
import com.jobworld.project.dto.RecruitDTO;
import com.jobworld.project.dto.ResumeDTO;
import com.jobworld.project.repository.ApplyRepository;
import com.jobworld.project.repository.CompRepository;
import com.jobworld.project.repository.MemberRepository;
import com.jobworld.project.repository.RecruitRepository;
import com.jobworld.project.repository.ResumeRepository;
import com.jobworld.project.repository.apply.CompApplyStatusDTO;
import com.jobworld.project.repository.apply.UserApplyStatusDTO;

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

	public ResumeDTO getUserResume() {
		List<Resume> list = resumeRepository.findByName(session.getAttribute("user_id").toString());
		if(list.size() > 0) {
			Resume resume = list.get(0);
			ResumeDTO dto = setResume(resume);
			return dto;
		}else
			return null;
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

	public List<UserApplyStatusDTO> getResumeMemberInfo(List<ApplyDTO> check) {
		List<UserApplyStatusDTO> list = new ArrayList<>();
		for(int i = 0; i < check.size(); ++i) {
			UserApplyStatusDTO dto = new UserApplyStatusDTO();
			Resume resume = resumeRepository.findOne(check.get(i).getResume_id());
			dto.setApply_id(check.get(i).getApply_id());
			dto.setState(check.get(i).getApply_state());
			dto.setResume_title(resume.getTitle());
			dto.setUser_birthday(resume.getMember().getBirthday());
			dto.setUser_email(resume.getMember().getEmail());
			dto.setUser_id(resume.getMember().getId());
			dto.setUser_img(resume.getImg());
			dto.setUser_nm(resume.getMember().getName());
			list.add(dto);
		}
		return list;
	}

	public int getResumeId(String user_id) {
		List<Resume> resume = resumeRepository.findByName(user_id);
		if(resume.size()>0) {
			return resume.get(0).getId();			
		}else
			return 0;
	}

	public List<ApplyDTO> getApplyInfo(int resume_id) {
		List<Apply> apply = repo.getApplyInfo(resume_id);
		List<ApplyDTO> list = Apply.setApplyDto(apply);
		return list;
	}

	public List<CompApplyStatusDTO> getRecruitCompanyInfo(List<ApplyDTO> check) {
		List<CompApplyStatusDTO> list = new ArrayList<>();
		for(int i = 0; i < check.size(); ++i) {
			CompApplyStatusDTO dto = new CompApplyStatusDTO();
			Recruit recruit = recruitRepository.findOne(check.get(i).getRecruit_id());
			dto.setApply_id(check.get(i).getApply_id());
			dto.setComp_business_type(recruit.getCompany().getBusinessType());
			dto.setComp_empl_num(recruit.getCompany().getEmplNum());
			dto.setComp_id(recruit.getCompany().getId());
			dto.setComp_nm(recruit.getCompany().getName());
			dto.setComp_site(recruit.getCompany().getSite());
			dto.setComp_size(recruit.getCompany().getSize());
			dto.setRecruit_area(recruit.getArea());
			dto.setRecruit_career(recruit.getCareer());
			dto.setRecruit_education(recruit.getEducation());
			dto.setRecruit_employment(recruit.getEmployment());
			dto.setRecruit_end_date(recruit.getEndDate());
			dto.setRecruit_id(recruit.getId());
			dto.setRecruit_open_type(recruit.getOpenType());
			dto.setRecruit_salary(recruit.getSalary());
			dto.setRecruit_start_date(recruit.getStartDate());
			dto.setRecruit_time(recruit.getTime());
			dto.setState(check.get(i).getApply_state());
			list.add(dto);
			
		}
		return list;
	}
	
	@Transactional
	public void applyCancel(Long apply_id) {
		repo.applyCancel(apply_id);
		
	}
	
	@Transactional
	public Long stateUpdate(Long apply_id, int state) {
		Apply apply = repo.find(apply_id);
		apply.setState(state);
		Long recruit_id = apply.getRecruit().getId();
		return recruit_id;
	}

}