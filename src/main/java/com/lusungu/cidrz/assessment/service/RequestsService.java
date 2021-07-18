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
import com.lusungu.cidrz.assessment.repository.RequestsRepository;

@Service
@Transactional
public class RequestsService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RequestsRepository requestRepo;
	
	// get one
	public Requests getRequest(int requestId) {
		Optional<Requests> request = requestRepo.findById(requestId);
		if (!request.isPresent()) {
			throw new NotFoundException("Request not found!");
		}
		return request.get();
	}
	
	// get all
	public List<Requests> getRequests(){
		return requestRepo.findAll();
	}
	
	// save
	public void save(Requests request) {
		Requests req = requestRepo.save(request);
		log.info("Request saved! {}", req);
	}
	
	// update
	public void update(int requestId, Requests request) {
		Requests req = getRequest(requestId);
		req.setAccessNo(request.getAccessNo());
		req.setDicDoctors(request.getDicDoctors());
		req.setDicFacilities(request.getDicFacilities());
		req.setPatients(request.getPatients());
		req.setReceivedDate(request.getReceivedDate());
		
		Requests update = requestRepo.save(req);
		log.info("Request updated! {}", update);
	}
	
	// delete
	public void delete(int requestId) {
		Requests req = getRequest(requestId);
		requestRepo.delete(req);
		log.info("Request deleted! {}", req);
	}
}
