package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;

import com.jobworld.project.dto.response.company.recruit.RecruitResponseDto;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Recruit;
import com.jobworld.project.dto.response.recruitViewResponseDto;
import com.jobworld.project.dto.request.company.CompanyIndexViewDto;
import com.jobworld.project.repository.HomeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class HomeService {
	private final HomeRepository repo;
	private final HttpSession session;

	public List<RecruitResponseDto> getRecruitInfo() {
		List<Recruit> recruit = repo.getRecruitInfo();
		List<RecruitResponseDto> list = setRecruit(recruit);
		return list;
	}

	private List<RecruitResponseDto> setRecruit(List<Recruit> recruitList) {
		List<RecruitResponseDto> list = new ArrayList<>();
		for(Recruit recruit : recruitList) {
			RecruitResponseDto dto = RecruitResponseDto.fromEntity(recruit);
			list.add(dto);
		}
		return list;
	}

	public List<recruitViewResponseDto> getUserRecruitViewInfo() {
		List<Recruit> recruit = repo.getRecruitInfo();
		List<recruitViewResponseDto> list = setUserRecruitView(recruit);
		return list;
	}

	private List<recruitViewResponseDto> setUserRecruitView(List<Recruit> recruitList) {
		List<recruitViewResponseDto> list = new ArrayList<>();
		for(Recruit recruit : recruitList) {
			list.add(recruitViewResponseDto.fromEntity(recruit));
		}
		return list;
	}

	public List<CompanyIndexViewDto> getCompanyIndexView() {
		List<Recruit> recruit = repo.findByCompId((String)session.getAttribute("comp_id"));
		if(recruit.size() == 0) {
			return null;
		}
		List<CompanyIndexViewDto> list = setCompanyIndexViewDto(recruit);
		return list;
	}

	private List<CompanyIndexViewDto> setCompanyIndexViewDto(List<Recruit> recruit) {
		List<CompanyIndexViewDto> list = new ArrayList<>();
		for(Recruit re : recruit) {
			CompanyIndexViewDto dto = new CompanyIndexViewDto();
			dto.setCompBrandImg(re.getCompany().getBrandImg());
			dto.setCompId(re.getCompany().getId());
			dto.setCompNm(re.getCompany().getName());
			dto.setRecruitId(re.getId());
			dto.setRecruitTitle(re.getTitle());
			list.add(dto);
		}
		return list;
	}
}
