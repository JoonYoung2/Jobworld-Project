package com.jobworld.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobworld.project.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
}