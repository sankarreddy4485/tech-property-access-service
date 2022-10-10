package com.tech.microservices.techpropertyaccessservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tech.microservices.techpropertyaccessservice.bean.PropertyAccessBean;
import com.tech.microservices.techpropertyaccessservice.bean.PropertyAccessValue;

@RestController
@RequestMapping("/access")
public class PropertyFileaccessController {

	@Autowired
	PropertyAccessBean propertyAccessBean;
	@GetMapping("accesspropertyfile")
	public PropertyAccessValue getPropertiesFile() {
		refreshAcctuator();
		return new PropertyAccessValue(propertyAccessBean.getName(), propertyAccessBean.getDescription());
		
	}
	
	public void refreshAcctuator() {
		RestTemplate restTemplate =new RestTemplate();
		final String baseUrl ="http://localhost:8100/actuator/refresh";
		HttpHeaders httpHeaders =new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");
		HttpEntity httpEntity = new HttpEntity(httpHeaders);
		ResponseEntity<String> responseEntity  = restTemplate.postForEntity(baseUrl, httpEntity, String.class);
	}
}
