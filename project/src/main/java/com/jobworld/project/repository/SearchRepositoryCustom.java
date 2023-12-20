package com.jobworld.project.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Company;
import com.jobworld.project.domain.Recruit;

import lombok.RequiredArgsConstructor;

@Repository
public interface SearchRepositoryCustom {
	List<Recruit> getRecruitSearchInfo(String userSearch);

	List<Recruit> getRecruitSearchInfo(String companySearch, String compId);

	Optional<Company> getCompanyById(String id);
}
