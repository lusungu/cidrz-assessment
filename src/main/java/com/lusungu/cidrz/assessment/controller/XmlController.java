package com.lusungu.cidrz.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lusungu.cidrz.assessment.model.Tests;
import com.lusungu.cidrz.assessment.response.GenericResponse;
import com.lusungu.cidrz.assessment.response.TestResponses;
import com.lusungu.cidrz.assessment.response.XmlTestResponse;
import com.lusungu.cidrz.assessment.service.TestsService;

@RestController
public class XmlController {
	
	@Autowired
	private TestsService testService;

	@RequestMapping(path = "/request/{requestId}", produces = {MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseEntity<?> getTestRequest(@PathVariable(value = "requestId") int requestId) {
		List<Tests> tests = testService.getTestByRequestId(requestId);
		
		TestResponses responses = new TestResponses();
		
		for (Tests test : tests) {
			XmlTestResponse testDetail = new XmlTestResponse();
			testDetail.setDoctorName(test.getRequests().getDicDoctors().getDocName());
			testDetail.setFacilityName(test.getRequests().getDicFacilities().getFacName());
			String patientName = test.getRequests().getPatients().getFirstName()+ " "+test.getRequests().getPatients().getLastName();
			testDetail.setPatientName(patientName);
			testDetail.setTestName(test.getDicTests().getTestName());
			testDetail.setTestValue(test.getResultValue());
			
			responses.getTestDetails().add(testDetail);
		}
		if (responses.getTestDetails().isEmpty()) {
			return ResponseEntity.ok().body(new GenericResponse("Data not found for the given request id"));	
		}
		return ResponseEntity.ok().body(responses);		
	}
	
	@RequestMapping(path = "/test/{testId}",  produces = {MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public Tests getTest(@PathVariable(value = "testId") int testId) {
		Tests test = testService.getTest(testId);
		return test;
	}
}
