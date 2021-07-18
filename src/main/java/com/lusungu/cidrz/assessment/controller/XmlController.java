package com.lusungu.cidrz.assessment.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lusungu.cidrz.assessment.model.Tests;
import com.lusungu.cidrz.assessment.response.XmlTestResponse;
import com.lusungu.cidrz.assessment.service.TestsService;

@RestController
public class XmlController {
	
	@Autowired
	private TestsService testService;

	@RequestMapping(value = "/request/{requestId}", produces = {MediaType.APPLICATION_XML_VALUE})
	public List<XmlTestResponse> getTest(@PathVariable(value = "requestId") int requestId) {
		System.out.println("Received request to get request in xml");
		List<Tests> tests = testService.getTestByRequestId(requestId);
		
		
		List<XmlTestResponse> responses = new ArrayList<XmlTestResponse>();
		for (Tests test : tests) {
			XmlTestResponse response = new XmlTestResponse();
			response.setDoctorName(test.getRequests().getDicDoctors().getDocName());
			response.setFacilityName(test.getRequests().getDicFacilities().getFacName());
			String patientName = test.getRequests().getPatients().getFirstName()+ " "+test.getRequests().getPatients().getLastName();
			response.setPatientName(patientName);
			response.setTestName(test.getDicTests().getTestName());
			response.setTestValue(test.getResultValue());
			
			responses.add(response);
		}		
		return responses;		
	}
}
