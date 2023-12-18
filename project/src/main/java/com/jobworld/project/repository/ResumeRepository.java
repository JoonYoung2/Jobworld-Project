package com.jobworld.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobworld.project.domain.Resume;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

	@Query("SELECT r FROM Resume r WHERE r.member.id = :memberId")
	List<Resume> findByMemberId(String memberId);
}
