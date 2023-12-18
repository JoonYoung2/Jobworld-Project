package com.jobworld.project.repository;

import com.jobworld.project.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompRepository extends JpaRepository<Company, String> {

}
