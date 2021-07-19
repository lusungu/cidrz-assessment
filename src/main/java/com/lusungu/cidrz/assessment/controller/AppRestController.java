package com.lusungu.cidrz.assessment.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
import com.lusungu.cidrz.assessment.util.FileUploadUtil;

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
	
	@Value(value = "${file.upload-dir}")
	private String uploadDir;

	@PostMapping("/dic-facilities")
	public GenericResponse addDicFacility(@RequestBody DicFacilities dicFac) {
		dicFacService.saveFacility(dicFac);
		return new GenericResponse("success");
	}
	
	@PostMapping(path = "/json/dic-facilities", consumes = {"multipart/form-data"})
    public ResponseEntity<GenericResponse> saveDicFacility(@ModelAttribute DicFacilities dicFacility) throws IOException {
		
		MultipartFile multipartFile = dicFacility.getFImage();
         
        String fileName = dicFacility.getFacCode()+"-image";
        //StringUtils.cleanPath(dicFacility.getFImage().getOriginalFilename()); 
        //StringUtils.cleanPath(multipartFile.getOriginalFilename());
        dicFacility.setFacImage(fileName);
        DicFacilities fac = dicFacService.saveFacility(dicFacility);
 
        String dir = uploadDir + fac.getFacId();
 
        FileUploadUtil.saveFile(dir, fileName, multipartFile);
         
        return ResponseEntity.ok().body(new GenericResponse("success"));	 // new GenericResponse("success");
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
	
	// add dic test
	@PostMapping("/dic-tests")
	public GenericResponse addDicTests(@RequestBody DicTests test) {
		dicTestService.save(test);
		return new GenericResponse("success");
	}
	
	// get dic test
	@GetMapping("/dic-tests")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getDicTests() {
		List<DicTests> tests = dicTestService.getTests();
		return ResponseEntity.ok().body(tests);
	}
	
	@PostMapping("/tests")
	public GenericResponse addTests(@RequestBody Tests test) {
		testService.save(test);
		return new GenericResponse("success");
	}
	
}
