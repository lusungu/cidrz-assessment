package com.lusungu.cidrz.assessment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Entity
@Data
public class DicFacilities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer facId;
	
	private String facCode;
	private String facName;
	
	@Column(nullable = true, length = 64)
    private String facImage;
	
	// for form submission via json or rest or javascript
	@Transient
	private MultipartFile fImage;
	
	@Transient
    public String getPhotosImagePath() {
        if (facImage == null || facId == null) return null;
         
        return "/cidrz/facility/" + facId + "/" + facImage;
    }
}
