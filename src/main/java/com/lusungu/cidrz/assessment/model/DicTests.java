package com.lusungu.cidrz.assessment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class DicTests {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int testId;
	
	@Column(unique = true)
	private String testCode;
	private String testName;
	private int testPrice;
	
	@OneToMany(mappedBy = "dicTests")
	@JsonIgnore
	private List<Tests> tests;

}
