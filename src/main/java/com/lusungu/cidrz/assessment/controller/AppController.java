package com.lusungu.cidrz.assessment.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lusungu.cidrz.assessment.model.DicFacilities;
import com.lusungu.cidrz.assessment.service.DicFacilitiesService;
import com.lusungu.cidrz.assessment.util.FileUploadUtil;

@Controller
public class AppController {

	@Autowired
	private DicFacilitiesService dicFacService;
	
	@Value(value = "${file.upload-dir}")
	private String uploadDir;
	
	@GetMapping("/webapp/facilities")
	public String getIndexPage(final Model model, @Param("message") String message) {
		List<DicFacilities> facs = dicFacService.getDicFacilities();
		model.addAttribute("message" , message);
		model.addAttribute("facilities" , facs);
		return "facilities";
	}
	
	@RequestMapping(path = "/webapp/facility")
	public String getFacility(final Model model) {
		DicFacilities fac = new DicFacilities();
		model.addAttribute("facility", fac);
		return "add-facility";
	}
	
	@PostMapping("/webapp/dic-facilities")
    public ModelAndView saveUser(DicFacilities dicFacility,
            @RequestParam("fImage") MultipartFile multipartFile) throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        dicFacility.setFacImage(fileName);
        DicFacilities fac = dicFacService.saveFacility(dicFacility);
 
        String dir = uploadDir + fac.getFacId();
 
        FileUploadUtil.saveFile(dir, fileName, multipartFile);
         
        return new ModelAndView("redirect:/webapp/facility-detail?facId="+fac.getFacId());
    }
	
	@GetMapping(path = "/webapp/facility-detail")
	public String getFacility(@Param("facId") int facId, final Model model) {
		DicFacilities fac = dicFacService.getDicFacility(facId);
		model.addAttribute("facility", fac);
		return "dic-facilities-details";
	}
}
