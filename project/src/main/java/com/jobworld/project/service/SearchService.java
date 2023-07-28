package com.jobworld.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
		return null;
	}

}
