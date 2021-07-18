package com.lusungu.cidrz.assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lusungu.cidrz.assessment.model.Requests;
import com.lusungu.cidrz.assessment.model.Tests;

public interface TestsRepository extends JpaRepository<Tests, Integer> {

	List<Tests> findByRequests(Requests request);

}
