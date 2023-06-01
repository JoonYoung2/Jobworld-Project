package com.jobworld.project.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Resume;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ResumeRepository {
	
	private final EntityManager em;
	
	public void save(Resume resume) throws Exception {
		try {
			em.persist(resume);			
		}catch(Exception e) {
			log.error("ResumeRepository save(ResumeDomain) error --> {}", e);
		}
	}
	
	public void update(Resume resume) throws Exception {
		Resume find;
		try {
			find = findOne(resume.getId());
			
			find.setImg(resume.getImg());
			find.setTitle(resume.getTitle());
		}catch(Exception e) {
			log.error("ResumeRepository update(ResumeDomain) error --> {}", e);
		}
	}
	
	public Resume findOne(int id) throws Exception {
		Resume find = null;
		
		try {
			find = em.find(Resume.class, id);
		}catch(Exception e) {
			log.error("ResumeRepository findOne(int) error --> {}", e);
		}
        return find;
    }
	
}
