package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;

import com.jobworld.project.dto.response.resume.ResumeResponseDto;
import com.jobworld.project.exception.NotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Apply;
import com.jobworld.project.domain.Recruit;
import com.jobworld.project.domain.Resume;
import com.jobworld.project.dto.ApplyDTO;
import com.jobworld.project.dto.RecruitDTO;
import com.jobworld.project.dto.request.resume.ResumeRequestDto;
import com.jobworld.project.dto.applyViewDto.UserResumeRecruitDTO;
import com.jobworld.project.repository.ApplyRepository;
import com.jobworld.project.repository.RecruitRepository;
import com.jobworld.project.repository.ResumeRepository;
import com.jobworld.project.repository.apply.CompApplyStatusDTO;
import com.jobworld.project.repository.apply.UserApplyStatusDTO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplyService {
	private final ApplyRepository repo;
	private final ResumeRepository resumeRepository;
	private final RecruitRepository recruitRepository;
	private final HttpSession session;

	public ResumeResponseDto getUserResume() {
		List<Resume> list = resumeRepository.findByMemberId(session.getAttribute("user_id").toString());
		if(list.size() > 0) {
			Resume resume = list.get(0);
			ResumeResponseDto dto = setResumeResponseDto(resume);
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
		Resume resume = resumeRepository.findById(dto.getResume_id()).orElseThrow(
				() -> new NotFoundException("찾는 이력서가 존재하지 않습니다.")
		);
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
		List<Apply> apply = repo.applyByRecruitId(recruit_id);
		List<ApplyDTO> list = Apply.setApplyDto(apply);
		return list;
	}
	

	private ResumeResponseDto setResumeResponseDto(Resume resume) {
		ResumeResponseDto dto = ResumeResponseDto.fromEntity(resume);
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
			Resume resume = resumeRepository.findById(check.get(i).getResume_id()).orElseThrow(
					() -> new NotFoundException("찾는 이력서가 없습니다."));
			dto.setApply_id(check.get(i).getApply_id());
			dto.setState(check.get(i).getApply_state());
			dto.setResume_title(resume.getTitle());
			dto.setUser_birthday(resume.getMember().getBirthday());
			dto.setUser_email(resume.getMember().getEmail());
			dto.setUser_id(resume.getMember().getId());
			dto.setUser_img(resume.getImg());
			dto.setUser_nm(resume.getMember().getName());
			dto.setResume_id(resume.getId());
			dto.setUser_phone_num(resume.getMember().getPhoneNum());
			dto.setZip_cd(resume.getMember().getZipCd());
			dto.setAddress_info(resume.getMember().getAddressInfo());
			dto.setAddress_detail(resume.getMember().getAddressDetail());
			list.add(dto);
		}
		return list;
	}

	public Long getResumeId(String user_id) {
		List<Resume> resume = resumeRepository.findByMemberId(user_id);
		if(resume.size()>0) {
			return resume.get(0).getId();
		}else
			return 0L;
	}

	public List<ApplyDTO> getApplyInfo(int resume_id) {
		List<Apply> apply = repo.applyByResumeId(resume_id);
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
			dto.setRecruit_title(recruit.getTitle());
			dto.setComp_brand_img(recruit.getCompany().getBrandImg());
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
		Apply apply = repo.findById(apply_id);
		apply.setState(state);
		Long recruit_id = apply.getRecruit().getId();
		return recruit_id;
	}

	public UserResumeRecruitDTO getApplyInfo(Long recruit_id, Long resumeId) {
		Recruit recruit = recruitRepository.findOne(recruit_id);
		Resume resume = resumeRepository.findById(resumeId).orElseThrow(
				() -> new NotFoundException("찾는 이력서가 없습니다."));
		UserResumeRecruitDTO dto = setUserResumeRecruitDto(recruit, resume);
		return dto;
	}

	private UserResumeRecruitDTO setUserResumeRecruitDto(Recruit recruit, Resume resume) {
		UserResumeRecruitDTO dto = new UserResumeRecruitDTO();
		dto.setRecruit_id(recruit.getId());
		dto.setResume_id(resume.getId());
		dto.setComp_nm(recruit.getCompany().getName());
		dto.setRecruit_title(recruit.getTitle());
		dto.setResume_title(resume.getTitle());
		dto.setUser_email(resume.getMember().getEmail());
		dto.setUser_nm(resume.getMember().getName());
		dto.setUser_phone_num(resume.getMember().getPhoneNum());
		
		return dto;
	}

}