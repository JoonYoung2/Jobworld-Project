package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;

import com.jobworld.project.dto.response.company.recruit.RecruitResponseDto;
import com.jobworld.project.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Company;
import com.jobworld.project.domain.Recruit;
import com.jobworld.project.dto.request.company.recruit.RecruitRequestDto;
import com.jobworld.project.dto.request.apply.UserCompanyRecruitInfoRequestDto;
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
	
	private List<RecruitResponseDto> setRecruitResponseList(List<Recruit> recruitList) {
		List<RecruitResponseDto> list = new ArrayList<>();
		for(Recruit recruit : recruitList) {
			RecruitResponseDto dto = RecruitResponseDto.fromEntity(recruit);
			list.add(dto);
		}
		return list;
	}
	
	private UserCompanyRecruitInfoRequestDto setRecruitInfo(Recruit recruit) {
		UserCompanyRecruitInfoRequestDto dto = new UserCompanyRecruitInfoRequestDto();
		dto.setRecruitId(recruit.getId());
		dto.setCompId(recruit.getCompany().getId());
		dto.setRecruitTitle(recruit.getTitle());
		dto.setRecruitCareer(recruit.getCareer());
		dto.setRecruitEducation(recruit.getEducation());
		dto.setRecruitEmployment(recruit.getEmployment());
		dto.setRecruitSalary(recruit.getSalary());
		dto.setRecruitArea(recruit.getArea());
		dto.setRecruitTime(recruit.getTime());
		dto.setRecruitStartDate(recruit.getStartDate());
		dto.setRecruitEndDate(recruit.getEndDate());
		dto.setRecruitOpenType(recruit.getOpenType());
		dto.setCompBrandImg(recruit.getCompany().getBrandImg());
		dto.setCompBusinessType(recruit.getCompany().getBusinessType());
		dto.setCompEmplNum(recruit.getCompany().getEmplNum());
		dto.setCompNm(recruit.getCompany().getName());
		dto.setCompSite(recruit.getCompany().getSite());
		dto.setCompSize(recruit.getCompany().getSize());
		return dto;
	}

	
	@Transactional
	public String recruitWrite(Recruit recruit) {
		try {
			recruit.updateOpenType(0);
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
			Recruit update = repo.findById(recruit.getId()).orElseThrow(
					() -> new NotFoundException("찾는 공고문이 없습니다.")
			);
			update.updateTitle(recruit.getTitle());
			update.updateCareer(recruit.getCareer());
			update.updateEducation(recruit.getEducation());
			update.updateEmployment(recruit.getEmployment());
			update.updateSalary(recruit.getSalary());
			update.updateArea(recruit.getArea());
			update.updateTime(recruit.getTime());
			update.updateStartDate(recruit.getStartDate());
			update.updateEndDate(recruit.getEndDate());
			update.updateOpenType(recruit.getOpenType());
			return "완료";
		}catch(Exception e) {
			log.error("RecruitService recruitUpdate(Recruit) error --> {}", e);
		}
		return "완료안됨";
	}

	public List<RecruitResponseDto> recruitResponseList(String compId) {
		
		List<Recruit> recruitList = repo.findByCompId(compId);
		List<RecruitResponseDto> recruitResponseDtoList = new ArrayList<>();
		if(recruitList.size() > 0) {
			for(Recruit recruit : recruitList) {
				RecruitResponseDto recruitResponseDto = RecruitResponseDto.fromEntity(recruit);
				recruitResponseDtoList.add(recruitResponseDto);
			}
			return recruitResponseDtoList;
		}else
		return null;
	}
	
	@Transactional
	public String save(RecruitRequestDto dto) {
		Company company = compRepository.findById(dto.getCompId()).orElseThrow(
				() -> new NotFoundException("찾는 회사가 없습니다.")
		);
		Recruit recruit = Recruit
				.builder()
				.recruitRequestDto(dto)
				.company(company)
				.build();
		repo.save(recruit);
		return "등록";
	}


	public UserCompanyRecruitInfoRequestDto recruitInfo(Long recruitId) {
		Recruit recruit = repo.findById(recruitId).orElseThrow(
				() -> new NotFoundException("찾는 공고문이 없습니다.")
		);
		UserCompanyRecruitInfoRequestDto dto = setRecruitInfo(recruit);
		return dto;
	}


	

	
	
}
