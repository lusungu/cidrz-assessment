package com.lusungu.cidrz.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lusungu.cidrz.assessment.model.DicDoctors;
import com.lusungu.cidrz.assessment.model.DicFacilities;
import com.lusungu.cidrz.assessment.model.DicTests;
import com.lusungu.cidrz.assessment.model.Patients;
import com.lusungu.cidrz.assessment.model.Requests;
import com.lusungu.cidrz.assessment.model.Tests;
import com.lusungu.cidrz.assessment.response.GenericResponse;
import com.lusungu.cidrz.assessment.service.DicDoctorsService;
import com.lusungu.cidrz.assessment.service.DicFacilitiesService;
import com.lusungu.cidrz.assessment.service.DicTestsService;
import com.lusungu.cidrz.assessment.service.PatientsService;
import com.lusungu.cidrz.assessment.service.RequestsService;
import com.lusungu.cidrz.assessment.service.TestsService;

@RestController
public class AppRestController {
	
	@Autowired
	private DicFacilitiesService dicFacService;
	
	@Autowired
	private DicDoctorsService dicDoctorsService;
	
	@Autowired
	private PatientsService patientService;
	
	@Autowired
	private RequestsService requestService;
	
	@Autowired
	private DicTestsService dicTestService;
	
	@Autowired
	private TestsService testService;

	@PostMapping("/dic-facilities")
	public GenericResponse addDicFacility(@RequestBody DicFacilities dicFac) {
		dicFacService.saveFacility(dicFac);
		return new GenericResponse("success");
	}
	
	@PostMapping("/dic-doctors")
	public GenericResponse addDicDoctors(@RequestBody DicDoctors dicDoctors) {
		dicDoctorsService.saveDoctor(dicDoctors);
		return new GenericResponse("success");
	}
	
	@PostMapping("/patients")
	public GenericResponse addPatient(@RequestBody Patients patient) {
		patientService.savePatient(patient);
		return new GenericResponse("success");
	}
	
	@PostMapping("/requests")
	public GenericResponse addRequests(@RequestBody Requests request) {
		requestService.save(request);
		return new GenericResponse("success");
	}
	
	@PostMapping("/dic-tests")
	public GenericResponse addDicTests(@RequestBody DicTests test) {
		dicTestService.save(test);
		return new GenericResponse("success");
	}
	
	@PostMapping("/tests")
	public GenericResponse addTests(@RequestBody Tests test) {
		testService.save(test);
		return new GenericResponse("success");
	}
	
}
