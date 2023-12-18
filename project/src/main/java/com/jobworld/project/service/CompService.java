package com.jobworld.project.service;

import com.jobworld.project.dto.response.company.member.CompanyMemberResponseDto;
import com.jobworld.project.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Company;
import com.jobworld.project.dto.request.company.member.CompanyMemberRequestDto;
import com.jobworld.project.repository.CompRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class CompService {
	private final CompRepository repo;
	
	@Transactional
	public String join(CompanyMemberRequestDto comp) {
		Company check = Company
				.builder()
				.companyMemberRequestDto(comp)
				.build();
		repo.save(check);
		return "등록 완료";
	}
	
	@Transactional
	public String compUpdate(CompanyMemberRequestDto companyMemberRequestDto) {
		Company company = repo.findById(companyMemberRequestDto.getCompId()).orElseThrow(
				() -> new NotFoundException("회원을 찾을 수 없습니다.")
		);
		company.updateName(companyMemberRequestDto.getCompNm());
		company.updateBusinessType(companyMemberRequestDto.getCompBusinessType());
		company.updateEmplNum(companyMemberRequestDto.getCompEmplNum());
		company.updateSize(companyMemberRequestDto.getCompSize());
		company.updateSite(companyMemberRequestDto.getCompSite());
		return "수정 완료";

	}
	
	public CompanyMemberResponseDto findId(String compId) {
		Optional<Company> company = repo.findById(compId);
		if(company.isEmpty()){
			return null;
		}
		return CompanyMemberResponseDto.fromEntity(company.get());
	}

	private CompanyMemberResponseDto setCompanyMemberResponseDto(Company company) {
		return CompanyMemberResponseDto.fromEntity(company);
	}
}
