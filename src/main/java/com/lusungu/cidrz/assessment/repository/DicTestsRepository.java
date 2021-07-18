package com.lusungu.cidrz.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lusungu.cidrz.assessment.model.DicTests;

public interface DicTestsRepository extends JpaRepository<DicTests, Integer> {

	boolean existsByTestCode(String testCode);
	
}
