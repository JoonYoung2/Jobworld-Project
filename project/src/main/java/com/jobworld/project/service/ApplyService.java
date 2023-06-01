package com.jobworld.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Apply;
import com.jobworld.project.domain.Company;
import com.jobworld.project.repository.ApplyRepository;
import com.jobworld.project.repository.CompRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class ApplyService {
	private final ApplyRepository repo;
	
	@Transactional
	public String applyWrite(Apply apply) {
		try {
			repo.save(apply);
			return "���� �Ϸ�";
		}catch(Exception e){
			log.error("ApplyService applyWrite(Apply) error --> {}", e);
		}
		return "���� ����";
	}
	
	@Transactional
	public String applyUpdate(Apply apply) {
		try {
			Apply update = repo.findOne(apply.getId());
			update.setState(apply.getState());
			return "���� �Ϸ�";
		}catch(Exception e) {
			log.error("ApplyService applyUpdate(Apply) error --> {}", e);
		}
		return "���� �Ϸ�";
	}
}
