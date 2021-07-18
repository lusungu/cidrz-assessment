package com.lusungu.cidrz.assessment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DicFacilities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int facId;
	
	private String facCode;
	private String facName;
}
