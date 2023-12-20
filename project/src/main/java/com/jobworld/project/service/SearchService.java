package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;

import com.jobworld.project.repository.custom.SearchRepositoryImpl;
import org.springframework.stereotype.Service;

import com.jobworld.project.domain.Recruit;
import com.jobworld.project.dto.response.recruitViewResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchService {
	private final SearchRepositoryImpl repo;
	public List<recruitViewResponseDto> getUserRecruitSearchViewInfo(String userSearch) {
		List<Recruit> recruit = repo.getRecruitSearchInfo(userSearch);
		if(recruit.size() > 0) {
			List<recruitViewResponseDto> list = getUserRecruitViewDto(recruit);
			return list;
		}
		
		return null;
	}
	
	public List<recruitViewResponseDto> getCompanyRecruitSearchViewInfo(String companySearch, String compId) {
		List<Recruit> recruit = repo.getRecruitSearchInfo(companySearch, compId);
		if(recruit.size() > 0) {
			return getUserRecruitViewDto(recruit);
		}
		
		return null;
	}
	
	private List<recruitViewResponseDto> getUserRecruitViewDto(List<Recruit> recruitList) {
		List<recruitViewResponseDto> list = new ArrayList<>();
		for(Recruit recruit : recruitList) {
			list.add(recruitViewResponseDto.fromEntity(recruit));
		}
		return list;
	}
}
