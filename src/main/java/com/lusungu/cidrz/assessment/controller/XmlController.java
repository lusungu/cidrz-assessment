package com.lusungu.cidrz.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lusungu.cidrz.assessment.model.DicDoctors;
import com.lusungu.cidrz.assessment.model.Tests;
import com.lusungu.cidrz.assessment.response.GenericResponse;
import com.lusungu.cidrz.assessment.response.TestResponses;
import com.lusungu.cidrz.assessment.response.XmlTestResponseDto;
import com.lusungu.cidrz.assessment.service.DicDoctorsService;
import com.lusungu.cidrz.assessment.service.TestsService;

/**
 * XML controller, consumes JSON and produces XML response document.
 * @author lchihana
 * 
 */
@RestController
public class XmlController {
	
	@Autowired
	private TestsService testService;
	
	@Autowired
	private DicDoctorsService dicDoctorsService;

	/** 
	 * @param requestId
	 * @return list of test results {@code XmlTestResponse}
	 */
	@RequestMapping(path = "/request/{requestId}", produces = {MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseEntity<?> getTestRequest(@PathVariable(value = "requestId") int requestId) {
		try {
			List<Tests> tests = testService.getTestByRequestId(requestId);
			
			TestResponses responses = new TestResponses();
			
			for (Tests test : tests) {
				XmlTestResponseDto testDetail = new XmlTestResponseDto();
				testDetail.setDoctorName(test.getRequests().getDicDoctors().getDocName());
				testDetail.setFacilityName(test.getRequests().getDicFacilities().getFacName());
				String patientName = test.getRequests().getPatients().getFirstName()+ " "+test.getRequests().getPatients().getLastName();
				testDetail.setPatientName(patientName);
				testDetail.setTestName(test.getDicTests().getTestName());
				testDetail.setResultValue(test.getResultValue());
				
				responses.getTestDetails().add(testDetail);
			}
			
			if (responses.getTestDetails().isEmpty()) {
				return ResponseEntity.ok().body(new GenericResponse("Data not found for the given request id"));	
			}
			
			if (responses.getTestDetails().size() == 1) {
				return ResponseEntity.ok().body(responses.getTestDetails().get(0));
			}		
			return ResponseEntity.ok().body(responses);	
		} catch (Exception e) {
			return ResponseEntity.ok().body(new GenericResponse(e.getMessage(), e.getClass().getSimpleName()));
		}
			
	}
	
	/**
	 * @param testId
	 * @return {@code Tests} valid XML document
	 */
	@RequestMapping(path = "/test/{testId}",  produces = {MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseEntity<?> getTest(@PathVariable(value = "testId") int testId) {
		Tests response; 
		try {
			response = testService.getTest(testId);
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			return ResponseEntity.ok().body(new GenericResponse(e.getMessage(), e.getClass().getSimpleName()));
		}			
	}
	
	@GetMapping(path = "/xml/dic-doctors", produces = {MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseEntity<?> getDicDoctors(){
		List<DicDoctors> response = dicDoctorsService.getDicDoctors();		
		return ResponseEntity.ok().body(response);
	}
}
