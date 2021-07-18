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
import com.lusungu.cidrz.assessment.model.Patients;
import com.lusungu.cidrz.assessment.repository.PatientsRepository;

@Service
@Transactional
public class PatientsService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PatientsRepository repo;
	
	// get one
	public Patients getPatient(int patId) {
		Optional<Patients> patient = repo.findById(patId);
		if (!patient.isPresent()) {
			throw new NotFoundException("Patient not found");
		}
		return patient.get();
	}
	
	// get all
	public List<Patients> getPatients(){
		return repo.findAll();
	}
	
	// save one'
	public void savePatient(Patients patient) {
		log.info("{}", patient);
		String nrc = patient.getNrc();
		if (existsByNRC(nrc)) {
			throw new AlreadyExistException("Patient already exists!");
		}
		Patients pat = repo.save(patient);
		log.info("Patient enrolled! {}", pat);
	}
	
	// update
	public void update(int patId, Patients patient) {
		Patients pat = getPatient(patId);
		pat.setDob(patient.getDob());
		pat.setFirstName(patient.getFirstName());
		pat.setLastName(patient.getLastName());
		pat.setNrc(patient.getNrc());
		pat.setSex(patient.getSex());
		
		Patients patUpdte = repo.save(pat);
		log.info("Patient updated! {}", patUpdte);
	}
	
	// delete
	public void delete(int patId) {
		Patients patient = getPatient(patId);
		repo.delete(patient);
		log.info("Patient deleted! {}", patient);
	}
	
	// if exists by nrc
	private boolean existsByNRC(String nrc) {
		boolean exists = repo.existsByNrcAndNrcIsNotNull(nrc); // children do not have nrc.
		return exists;
	}

}
