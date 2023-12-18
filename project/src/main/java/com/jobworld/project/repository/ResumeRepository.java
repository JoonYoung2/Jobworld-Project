package com.jobworld.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobworld.project.domain.Resume;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

	@Query("SELECT r FROM Resume r WHERE r.member.id = :memberId")
	List<Resume> findByMemberId(String memberId);
}
