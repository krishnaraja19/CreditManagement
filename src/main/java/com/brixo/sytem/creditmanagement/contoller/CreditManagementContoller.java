package com.brixo.sytem.creditmanagement.contoller;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brixo.sytem.creditmanagement.model.Application;
import com.brixo.sytem.creditmanagement.service.ApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/credit/api")
public class CreditManagementContoller {
	
	@Autowired
	private ApplicationService appService;
	
	@RequestMapping("/allapplcation")
	public List <Application> getApplications(){
		return appService.getAllApplication();
	}
	
	
}
