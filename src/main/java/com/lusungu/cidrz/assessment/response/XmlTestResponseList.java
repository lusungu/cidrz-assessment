package com.lusungu.cidrz.assessment.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name="TestResponses")
@Getter
@Setter
public class XmlTestResponseList {

	private List<XmlTestResponse> testDetails = new ArrayList<XmlTestResponse>();
}
