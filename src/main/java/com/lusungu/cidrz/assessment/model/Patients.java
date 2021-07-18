package com.lusungu.cidrz.assessment.model;

import java.util.Date;
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
public class Patients {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patId;
	
	private String firstName;
	private String lastName;
	private String nrc;
	@Column(nullable = false)
	private Date dob;
	@Column(nullable = false)
	private int sex;
	
	@OneToMany(mappedBy = "patients")
	@JsonIgnore
	private List<Requests> requests;

}
