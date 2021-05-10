package com.brixo.sytem.creditmanagement.contoller;

import java.util.List;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brixo.sytem.creditmanagement.model.Application;
import com.brixo.sytem.creditmanagement.model.ApplicationPlanDetails;
import com.brixo.sytem.creditmanagement.service.ApplicationService;
import com.brixo.sytem.creditmanagement.service.EmailSenderService;
import com.brixo.sytem.creditmanagement.service.PlanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/credit/api")
public class CreditManagementContoller {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditManagementContoller.class);
	
	
	@Autowired
	private ApplicationService appService;
	
	@Autowired
	private PlanService planService;
	
	@RequestMapping("/allapplcation")
	public List <Application> getApplications(){
		LOGGER.info("Getting request from client for fetching all application details");
		return appService.getAllApplication();
	}
	
	@RequestMapping("/plandetails/{id}")
	public List <ApplicationPlanDetails> getApplicationPlanDetail(@PathVariable(value = "id")
									int appId){
		LOGGER.info("Getting request from client for fetching all application plan details");
		Application application = appService.getOneApplication(appId);
		return planService.findById(application);
	}
	
	
}
