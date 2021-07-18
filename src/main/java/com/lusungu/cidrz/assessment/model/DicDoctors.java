package com.lusungu.cidrz.assessment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DicDoctors {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int docId;
	
	private String docCode;
	private String docName;

}
