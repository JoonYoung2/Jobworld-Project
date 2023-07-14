package com.jobworld.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Company;
import com.jobworld.project.dto.CompDTO;
import com.jobworld.project.repository.CompRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class CompService {
	private final CompRepository repo;
	
	@Transactional
	public String join(CompDTO comp) {
		Company check = new Company();
		try {
			check.setId(comp.getComp_id());
	    	check.setPw(comp.getComp_pw());
	    	check.setBusinessType(comp.getComp_business_type());
	    	check.setEmplNum(comp.getComp_empl_num());
	    	check.setName(comp.getComp_nm());
	    	check.setSite(comp.getComp_site());
	    	check.setSize(comp.getComp_size());
	    	check.setBrandImg(comp.getComp_brand_img());
			repo.save(check);
			return "등록 완료";
		}catch(Exception e){
			log.error("CompService compWrite(Company) error --> {}", e);
		}
		return "등록 실패";
	}
	
	@Transactional
	public String compUpdate(Company comp) {
		try {
			Company update = repo.findOne(comp.getId());
			update.setPw(comp.getPw());
			update.setName(comp.getName());
			update.setBusinessType(comp.getBusinessType());
			update.setEmplNum(comp.getEmplNum());
			update.setSize(comp.getSize());
			update.setSite(comp.getSite());
			return "수정 완료";
		}catch(Exception e) {
			log.error("CompService compUpdate(Company) error --> {}", e);
		}
		return "수정 실패";
	}
	
	public CompDTO findId(String comp_id) {
		Company comp = repo.findOne(comp_id);
		if(comp == null) {
			return null;
		}
		CompDTO check = setCompDto(comp);
		return check;
	}

	private CompDTO setCompDto(Company comp) {
		CompDTO dto = new CompDTO();
		dto.setComp_brand_img(comp.getBrandImg());
		dto.setComp_business_type(comp.getBusinessType());
		dto.setComp_empl_num(comp.getEmplNum());
		dto.setComp_id(comp.getId());
		dto.setComp_nm(comp.getName());
		dto.setComp_pw(comp.getPw());
		dto.setComp_site(comp.getSite());
		dto.setComp_size(comp.getSize());
		return dto;
	}
}
