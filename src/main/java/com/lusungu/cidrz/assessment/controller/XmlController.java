package com.lusungu.cidrz.assessment.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lusungu.cidrz.assessment.model.Tests;
import com.lusungu.cidrz.assessment.response.XmlTestResponse;
import com.lusungu.cidrz.assessment.response.XmlTestResponseList;
import com.lusungu.cidrz.assessment.service.TestsService;

@RestController
public class XmlController {
	
	@Autowired
	private TestsService testService;

	@RequestMapping(path = "/request/{requestId}", produces = {MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public XmlTestResponseList getTestRequest(@PathVariable(value = "requestId") int requestId) {
		System.out.println("Received request to get request in xml");
		List<Tests> tests = testService.getTestByRequestId(requestId);
		
		XmlTestResponseList resps = new XmlTestResponseList();
		
		List<XmlTestResponse> responses = new ArrayList<XmlTestResponse>();
		for (Tests test : tests) {
			XmlTestResponse testDetail = new XmlTestResponse();
			testDetail.setDoctorName(test.getRequests().getDicDoctors().getDocName());
			testDetail.setFacilityName(test.getRequests().getDicFacilities().getFacName());
			String patientName = test.getRequests().getPatients().getFirstName()+ " "+test.getRequests().getPatients().getLastName();
			testDetail.setPatientName(patientName);
			testDetail.setTestName(test.getDicTests().getTestName());
			testDetail.setTestValue(test.getResultValue());
			
			resps.getTestDetails().add(testDetail);
			
			responses.add(testDetail);
		}		
		return resps;		
	}
	
	@RequestMapping(path = "/test/{testId}",  produces = {MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public Tests getTest(@PathVariable(value = "testId") int testId) {
		Tests test = testService.getTest(testId);
		return test;
	}
}
