package com.lusungu.cidrz.assessment.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lusungu.cidrz.assessment.error.AlreadyExistException;
import com.lusungu.cidrz.assessment.error.NotFoundException;
import com.lusungu.cidrz.assessment.model.DicTests;
import com.lusungu.cidrz.assessment.repository.DicTestsRepository;

@Service
@Transactional
public class DicTestsService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DicTestsRepository repo;
	
	// get one
	public DicTests getTest(int testId) {
		Optional<DicTests> test = repo.findById(testId);
		if (!test.isPresent()) {
			throw new NotFoundException("Test not found!");
		}
		return test.get();
	}
	
	// get all
	public List<DicTests> getTests(){
		return repo.findAll();
	}
	
	// save
	public void save(DicTests test) {
		String testCode = test.getTestCode();
		if (exists(testCode)) {
			throw new AlreadyExistException("Test code already exists!");
		}
		DicTests dicTest = repo.save(test);
		log.info("Test saved! {}", dicTest);
	}
	
	// update
	public void update(int testId, DicTests test) {
		DicTests dt = getTest(testId);
		dt.setTestCode(test.getTestCode());
		dt.setTestName(test.getTestName());
		dt.setTestPrice(test.getTestPrice());
		
		DicTests update = repo.save(dt);
		log.info("Test updated! {}", update);
	}
	
	// delete
	public void delete(int testId) {
		DicTests test = getTest(testId);
		repo.delete(test);
		log.info("Test deleted! {}", test);
	}
	
	// check if test code already exists!
	private boolean exists(String testCode) {
		boolean exist = repo.existsByTestCode(testCode);
		return exist;
	}

}
