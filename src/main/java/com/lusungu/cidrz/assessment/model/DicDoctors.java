package com.lusungu.cidrz.assessment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Entity
@Data
@JacksonXmlRootElement(localName = "DicDoctor")
public class DicDoctors {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int docId;
	
	@Column(unique = true)
	private String docCode;
	private String docName;

}
