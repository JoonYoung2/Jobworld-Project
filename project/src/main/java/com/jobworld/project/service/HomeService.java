package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Recruit;
import com.jobworld.project.dto.RecruitDTO;
import com.jobworld.project.dto.UserRecruitViewDTO;
import com.jobworld.project.repository.HomeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class HomeService {
	private final HomeRepository repo;

	public List<RecruitDTO> getRecruitInfo() {
		List<Recruit> recruit = repo.getRecruitInfo();
		List<RecruitDTO> list = setRecruit(recruit);
		return list;
	}

	private List<RecruitDTO> setRecruit(List<Recruit> recruit) {
		List<RecruitDTO> list = new ArrayList<>();
		for(int i=0; i<recruit.size(); ++i) {
			RecruitDTO dto = new RecruitDTO();
			dto.setRecruit_id(recruit.get(i).getId());
			dto.setComp_id(recruit.get(i).getCompany().getId());
			dto.setRecruit_title(recruit.get(i).getTitle());
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

	public List<UserRecruitViewDTO> getUserRecruitViewInfo() {
		List<Recruit> recruit = repo.getRecruitInfo();
		List<UserRecruitViewDTO> list = setUserRecruitView(recruit);
		return list;
	}

	private List<UserRecruitViewDTO> setUserRecruitView(List<Recruit> recruit) {
		List<UserRecruitViewDTO> list = new ArrayList<>();
		for(int i=0; i<recruit.size(); ++i) {
			UserRecruitViewDTO dto = new UserRecruitViewDTO();
			dto.setRecruit_id(recruit.get(i).getId());
			dto.setComp_id(recruit.get(i).getCompany().getId());
			dto.setRecruit_title(recruit.get(i).getTitle());
			dto.setComp_brand_img(recruit.get(i).getCompany().getBrandImg());
			System.out.println("brand_img = " + dto.getComp_brand_img());
			dto.setComp_nm(recruit.get(i).getCompany().getName());
			list.add(dto);
		}
		return list;
	}
}
