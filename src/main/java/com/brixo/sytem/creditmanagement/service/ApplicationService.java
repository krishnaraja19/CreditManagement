package com.brixo.sytem.creditmanagement.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.brixo.sytem.creditmanagement.model.Application;
import com.brixo.sytem.creditmanagement.repository.ApplicationRepository;


@Service
public class ApplicationService {
	
	
	@Autowired
	private ApplicationRepository appRepo;
	
	public List<Application> getAllApplication() {
		 return appRepo.findAll();
		
	}
	
	public void saveAllApplication(List<Application> applications) {
		 appRepo.saveAll(applications);
	}
}
