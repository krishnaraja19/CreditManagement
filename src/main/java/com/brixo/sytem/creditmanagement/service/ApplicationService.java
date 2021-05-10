package com.brixo.sytem.creditmanagement.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.brixo.sytem.creditmanagement.model.Application;
import com.brixo.sytem.creditmanagement.repository.ApplicationRepository;


@Service
public class ApplicationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);
	
	@Autowired
	private ApplicationRepository appRepo;
	
	
	public List<Application> getAllApplication() {
		LOGGER.info("Fetching all the records from Application repository");
		 return appRepo.findAll();
		
	}
	public Application getOneApplication(int id) {
		LOGGER.info("Fetching a records from Application repository by Id");
		 return appRepo.findById(id);
	}
	
	public void saveAllApplication(List<Application> applications) {
		LOGGER.info("Saving all the records from Application repository");
		 appRepo.saveAll(applications);
	}
}
