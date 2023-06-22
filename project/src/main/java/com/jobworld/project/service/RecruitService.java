package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Company;
import com.jobworld.project.domain.Recruit;
import com.jobworld.project.dto.RecruitDTO;
import com.jobworld.project.repository.CompRepository;
import com.jobworld.project.repository.RecruitRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class RecruitService {
	private final RecruitRepository repo;
	private final CompRepository compRepository;
	
	private List<RecruitDTO> setRecruitList(List<Recruit> recruit) {
		List<RecruitDTO> list = new ArrayList<>();
		for(int i = 0; i < recruit.size(); ++i) {
			RecruitDTO dto = new RecruitDTO();
			dto.setRecruit_id(recruit.get(i).getId());
			dto.setComp_id(recruit.get(i).getCompany().getId());
			dto.setRecruit_career(recruit.get(i).getCareer());
			dto.setRecruit_education(recruit.get(i).getEducation());
			dto.setRecruit_employment(recruit.get(i).getEmployment());
			dto.setRecruit_salary(recruit.get(i).getSalary());
			dto.setRecruit_area(recruit.get(i).getArea());
			dto.setRecruit_time(recruit.get(i).getTime());
			dto.setRecruit_start_date(recruit.get(i).getStartDate());
			dto.setRecruit_end_date(recruit.get(i).getEndDate());
			dto.setRecruit_open_type(recruit.get(i).getOpenType());
			list.add(dto);
		}
		return list;
	}
	
	private RecruitDTO setRecruitInfo(Recruit recruit) {
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

	
	@Transactional
	public String recruitWrite(Recruit recruit) {
		try {
			recruit.setOpenType(0);
			repo.save(recruit);
			return "등록";
		}catch(Exception e){
			log.error("RecruitService recruitWrite(Recruit) error --> {}", e);
		}
		return "등록안됨";
	}
	
	@Transactional
	public String recruitUpdate(Recruit recruit) {
		try {
			Recruit update = repo.findOne(recruit.getId());
			update.setCareer(recruit.getCareer());
			update.setEducation(recruit.getEducation());
			update.setEmployment(recruit.getEmployment());
			update.setSalary(recruit.getSalary());
			update.setArea(recruit.getArea());
			update.setTime(recruit.getTime());
			update.setStartDate(recruit.getStartDate());
			update.setEndDate(recruit.getEndDate());
			update.setOpenType(recruit.getOpenType());
			return "완료";
		}catch(Exception e) {
			log.error("RecruitService recruitUpdate(Recruit) error --> {}", e);
		}
		return "완료안됨";
	}

	public List<RecruitDTO> recruitList(String comp_id) {
		
		List<Recruit> recruit = repo.findByCompId(comp_id);

		if(recruit.size() > 0) { 
			List<RecruitDTO> list = setRecruitList(recruit);
			return list;
		}else
		return null;
	}
	
	@Transactional
	public String save(RecruitDTO dto) {
		Company company = compRepository.findOne(dto.getComp_id());
		Recruit recruit = Recruit.setRecruit(dto, company);
		repo.save(recruit);
		return "등록";
	}


	public RecruitDTO recruitInfo(Long recruit_id) {
		Recruit recruit = repo.findOne(recruit_id);
		RecruitDTO dto = setRecruitInfo(recruit);
		return dto;
	}


	

	
	
}
