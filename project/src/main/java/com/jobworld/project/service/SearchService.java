package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jobworld.project.domain.Company;
import com.jobworld.project.domain.Recruit;
import com.jobworld.project.dto.applyViewDto.UserRecruitViewDTO;
import com.jobworld.project.repository.RecruitRepository;
import com.jobworld.project.repository.SearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchService {
	private final SearchRepository repo;
	public List<UserRecruitViewDTO> getUserRecruitSearchViewInfo(String userSearch) {
		List<Recruit> recruit = repo.getRecruitSearchInfo(userSearch);
		if(recruit.size() > 0) {
			List<UserRecruitViewDTO> list = getUserRecruitViewDto(recruit);			
			return list;
		}
		
		return null;
	}
	
	private List<UserRecruitViewDTO> getUserRecruitViewDto(List<Recruit> recruit) {
		List<UserRecruitViewDTO> list = new ArrayList<>();
		for(int i = 0; i < recruit.size(); ++i) {
			UserRecruitViewDTO dto = new UserRecruitViewDTO();
			Company comp = repo.getCompanyById(recruit.get(i).getCompany().getId());
			dto.setRecruit_id(recruit.get(i).getId());
			dto.setComp_id(comp.getId());
			dto.setRecruit_title(recruit.get(i).getTitle());
			dto.setComp_brand_img(comp.getBrandImg());
			dto.setComp_nm(comp.getName());
			list.add(dto);
		}
		return list;
	}
}
