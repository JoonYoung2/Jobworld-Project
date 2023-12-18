package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jobworld.project.domain.Company;
import com.jobworld.project.domain.Recruit;
import com.jobworld.project.dto.request.apply.UserRecruitViewRequestDto;
import com.jobworld.project.repository.SearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchService {
	private final SearchRepository repo;
	public List<UserRecruitViewRequestDto> getUserRecruitSearchViewInfo(String userSearch) {
		List<Recruit> recruit = repo.getRecruitSearchInfo(userSearch);
		if(recruit.size() > 0) {
			List<UserRecruitViewRequestDto> list = getUserRecruitViewDto(recruit);
			return list;
		}
		
		return null;
	}
	
	public List<UserRecruitViewRequestDto> getCompanyRecruitSearchViewInfo(String companySearch, String comp_id) {
		List<Recruit> recruit = repo.getRecruitSearchInfo(companySearch, comp_id);
		if(recruit.size() > 0) {
			List<UserRecruitViewRequestDto> list = getUserRecruitViewDto(recruit);
			return list;
		}
		
		return null;
	}
	
	private List<UserRecruitViewRequestDto> getUserRecruitViewDto(List<Recruit> recruit) {
		List<UserRecruitViewRequestDto> list = new ArrayList<>();
		for(int i = 0; i < recruit.size(); ++i) {
			UserRecruitViewRequestDto dto = new UserRecruitViewRequestDto();
			Company comp = repo.getCompanyById(recruit.get(i).getCompany().getId());
			dto.setRecruitId(recruit.get(i).getId());
			dto.setCompId(comp.getId());
			dto.setRecruitTitle(recruit.get(i).getTitle());
			dto.setCompBrandImg(comp.getBrandImg());
			dto.setCompNm(comp.getName());
			list.add(dto);
		}
		return list;
	}
}
