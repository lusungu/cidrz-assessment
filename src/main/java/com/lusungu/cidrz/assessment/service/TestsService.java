package com.lusungu.cidrz.assessment.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lusungu.cidrz.assessment.error.NotFoundException;
import com.lusungu.cidrz.assessment.model.Requests;
import com.lusungu.cidrz.assessment.model.Tests;
import com.lusungu.cidrz.assessment.repository.TestsRepository;

@Service
@Transactional
public class TestsService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TestsRepository repo;
	
	@Autowired
	private RequestsService requestService;
	
	// get one
	public Tests getTest(int reqTestId) {
		Optional<Tests> test = repo.findById(reqTestId);
		if (!test.isPresent()) {
			throw new NotFoundException("Test not found");
		}
		return test.get();
	}
	
	// get all
	public List<Tests> getTests(){
		return repo.findAll();
	}
	
	// save
	public void save(Tests test) {
		Tests te = repo.save(test);
		log.info("Test saved! {}", te);
	}
	
	// update
	public void update(int reqTestId, Tests test) {
		Tests t = getTest(reqTestId);
		t.setDicTests(test.getDicTests());
		t.setRequests(test.getRequests());
		t.setResultValue(test.getResultValue());
		
		Tests te = repo.save(t);
		log.info("Test updated! {}", te);
	}
	
	// delete
	public void delete(int reqTestId) {
		Tests test = getTest(reqTestId);
		repo.delete(test);
		log.info("Test deleted! {}", test);
	}

	public List<Tests> getTestByRequestId(int requestId) {
		Requests request = requestService.getRequest(requestId);
		List<Tests> tests = repo.findByRequests(request);
		return tests;
	}
}
