package com.lusungu.cidrz.assessment.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@XmlRootElement(name="TestResponses")
public class TestResponses {

	private List<XmlTestResponse> testDetails = new ArrayList<XmlTestResponse>();
}
