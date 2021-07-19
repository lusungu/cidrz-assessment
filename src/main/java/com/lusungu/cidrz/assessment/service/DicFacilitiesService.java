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
import com.lusungu.cidrz.assessment.model.DicFacilities;
import com.lusungu.cidrz.assessment.repository.DicFacilitiesRepository;

@Service
@Transactional
public class DicFacilitiesService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DicFacilitiesRepository repo;
	
	// get all facilities
	public List<DicFacilities> getDicFacilities(){
		return repo.findAll();
	}
	
	// get one facility
	public DicFacilities getDicFacility(int facId) {
		Optional<DicFacilities> facility = repo.findById(facId);
		if (!facility.isPresent()) {
			throw new NotFoundException("Facility with id="+facId+" does not exist!");
		}		
		return facility.get();
	}
	
	// save one facility
	public DicFacilities saveFacility(DicFacilities facility) {
		String facCode = facility.getFacCode();
		if (existsByFacCode(facCode)) {
			throw new AlreadyExistException("Facility with Facility code "+facCode+" already exists");
		}
		DicFacilities fac = repo.save(facility);
		log.info("Facility saved! {}", fac);
		return fac;
	}
	
	// update
	public void updateFacility(int facId, DicFacilities facility) {
		DicFacilities fac = getDicFacility(facId);
		fac.setFacCode(facility.getFacCode());
		fac.setFacName(facility.getFacName());
		
		DicFacilities update = repo.save(fac);
		log.info("Facility updated! {}", update);
	}
	
	// delete
	public void deleteFacility(int facId) {
		DicFacilities fac = getDicFacility(facId);
		repo.delete(fac);
		log.info("Facility deleted! {}", fac);
	}
	
	private boolean existsByFacCode(String facCode) {
		boolean exists = repo.existsByFacCode(facCode);
		return exists;
	}
	
}
