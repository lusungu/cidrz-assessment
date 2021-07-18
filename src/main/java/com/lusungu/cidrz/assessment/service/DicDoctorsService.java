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
import com.lusungu.cidrz.assessment.model.DicDoctors;
import com.lusungu.cidrz.assessment.repository.DicDoctorsRepository;

@Service
@Transactional
public class DicDoctorsService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DicDoctorsRepository repo;
	
	// get all dic doctors
	public List<DicDoctors> getDicDoctors(){
		return repo.findAll();
	}
	
	// get one dic doctor
	public DicDoctors getDicDoctor(int docId) {
		Optional<DicDoctors> doctor = repo.findById(docId);
		if (!doctor.isPresent()) {
			throw new NotFoundException("Dic Doctor with id="+docId+" does not exist!");
		}		
		return doctor.get();
	}
	
	// save one doctor
	public void saveDoctor(DicDoctors doctor) {
		String docCode = doctor.getDocCode();
		if (existsByDocCode(docCode)) {
			throw new AlreadyExistException("Doc code "+docCode+" already exists");
		}
		DicDoctors doc = repo.save(doctor);
		log.info("Dic Doctor saved! {}", doc);
	}
	
	// update
	public void updateDoctor(int docId, DicDoctors doctor) {
		DicDoctors doc = getDicDoctor(docId);
		doc.setDocCode(doctor.getDocCode());
		doc.setDocName(doctor.getDocName());
		
		DicDoctors update = repo.save(doc);
		log.info("Doctor updated! {}", update);
	}
	
	// delete
	public void deleteDoctor(int docId) {
		DicDoctors doc = getDicDoctor(docId);
		repo.delete(doc);
		log.info("Dic doctor deleted! {}", doc);
	}
	
	private boolean existsByDocCode(String facCode) {
		boolean exists = repo.existsByDocCode(facCode);
		return exists;
	}
	
}
