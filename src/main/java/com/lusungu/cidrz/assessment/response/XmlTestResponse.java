package com.lusungu.cidrz.assessment.response;

import lombok.Data;

@Data
public class XmlTestResponse {
	
	private String patientName;
	private String testName;
	private int testValue;
	private String facilityName;
	private String doctorName;

}
