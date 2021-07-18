package com.lusungu.cidrz.assessment.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "TestDetail")
@XmlAccessorType(XmlAccessType.NONE)
public class XmlTestResponse {
	
	@XmlElement
	private String patientName;
	@XmlElement
	private String testName;
	@XmlElement
	private int testValue;
	@XmlElement
	private String facilityName;
	@XmlElement
	private String doctorName;

}
