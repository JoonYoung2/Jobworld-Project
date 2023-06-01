package com.jobworld.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Recruit;
import com.jobworld.project.repository.RecruitRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class RecruitService {
	private final RecruitRepository repo;
	
	@Transactional
	public String recruitWrite(Recruit recruit) {
		try {
			recruit.setOpenType(0);
			repo.save(recruit);
			return "쓰기 완료";
		}catch(Exception e){
			log.error("RecruitService recruitWrite(Recruit) error --> {}", e);
		}
		return "쓰기 실패";
	}
	
	@Transactional
	public String recruitUpdate(Recruit recruit) {
		try {
			Recruit update = repo.findOne(recruit.getId());
			update.setCarrer(recruit.getCarrer());
			update.setEducation(recruit.getEducation());
			update.setEmployment(recruit.getEmployment());
			update.setSalary(recruit.getSalary());
			update.setArea(recruit.getArea());
			update.setTime(recruit.getTime());
			update.setStartDate(recruit.getStartDate());
			update.setEndDate(recruit.getEndDate());
			update.setOpenType(recruit.getOpenType());
			return "수정 완료";
		}catch(Exception e) {
			log.error("RecruitService recruitUpdate(Recruit) error --> {}", e);
		}
		return "수정 완료";
	}
}
