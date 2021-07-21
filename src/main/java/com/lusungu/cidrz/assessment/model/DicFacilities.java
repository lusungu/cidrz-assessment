package com.lusungu.cidrz.assessment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Entity
@Data
public class DicFacilities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer facId;
	
	@Column(unique = true)
	private String facCode;
	private String facName;
	
	@Column(nullable = true, length = 64)
	@XmlTransient
    private String facImage;
	
	// for form submission via json or rest or javascript
	@Transient
	@XmlTransient
	private MultipartFile fImage;
	
	@Transient
	@XmlTransient
    public String getPhotosImagePath() {
        if (facImage == null || facId == null) return null;
         
        return "/cidrz/facility/" + facId + "/" + facImage;
    }
}
