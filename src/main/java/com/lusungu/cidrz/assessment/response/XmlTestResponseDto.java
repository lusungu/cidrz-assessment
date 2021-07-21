package com.lusungu.cidrz.assessment.response;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO to hold data used as response to test result request. 
 * Transformed to XML document.
 * @author lchihana
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "RequestDetail")
@JsonPropertyOrder(value = {"patientName", "testName", "doctorName", "facilityName", "testValue" })
public class XmlTestResponseDto {
	
	@XmlElement
	private String patientName;
	@XmlElement
	private String testName;
	@XmlElement
	private int resultValue;
	@XmlElement
	private String facilityName;
	@XmlElement
	private String doctorName;

}
