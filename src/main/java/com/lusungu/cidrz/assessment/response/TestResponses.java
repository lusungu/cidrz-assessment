package com.lusungu.cidrz.assessment.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JacksonXmlRootElement(localName = "RequestDetails")
public class TestResponses {
	
	@JacksonXmlProperty(localName = "TestDetails")
	private List<XmlTestResponseDto> testDetails = new ArrayList<XmlTestResponseDto>();
}
