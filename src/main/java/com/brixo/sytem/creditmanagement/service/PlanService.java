package com.brixo.sytem.creditmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brixo.sytem.creditmanagement.model.Application;
import com.brixo.sytem.creditmanagement.model.ApplicationPlanDetails;
import com.brixo.sytem.creditmanagement.repository.ApplicationPlanDetailsRepository;


@Service
public class PlanService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PlanService.class);
	
	@Autowired
	private ApplicationPlanDetailsRepository appPlanDtlRepo;
	
	public List<ApplicationPlanDetails> getAllApplicationPlanDetails() {
		 LOGGER.info("Fetching all the records from Plan Detail repository");
		 return appPlanDtlRepo.findAll();
		
	}
	public void saveApplicationPlanDetail(ApplicationPlanDetails planDetail) {
		LOGGER.info("Saving a record to Plan Detail repository");
		appPlanDtlRepo.save(planDetail);
	}
	public void saveAllApplicationPlanDetails(List<ApplicationPlanDetails> planDetails) {
		LOGGER.info("Saving all the records to Plan Detail repository");
		appPlanDtlRepo.saveAll(planDetails);
	}
	public ApplicationPlanDetails findByApplicationAndPlanDetails(Application application, int plan) {
		LOGGER.info("Fetching a record from Plan Detail repository");
		return appPlanDtlRepo.findByApplicationAndPlan(application, plan);
	}
	public List<ApplicationPlanDetails> findAllValidPlan(LocalDateTime StartTime,LocalDateTime EndTime) {
		LOGGER.info("Fetching list of records from Plan Detail repository as per the below quert");
		return appPlanDtlRepo.findAllByStartTimeGreaterThanEqualAndStartTimeLessThanEqualAndPlanNotAndIsMailSent(StartTime, EndTime,0,false);
		
	}
	public List<ApplicationPlanDetails> findById(Application application){
		LOGGER.info("Fetching a record from Plan Detail repository by application details");
		return appPlanDtlRepo.findByApplication(application);
	}
	
	public void updateByPlanId(ApplicationPlanDetails planDtl) {
		LOGGER.info("Updating a record to Plan Detail repository by Plan detail id");
		Optional<ApplicationPlanDetails> plantoUpdate = appPlanDtlRepo.findById(planDtl.getId());
		plantoUpdate.ifPresent(consumer->{
			consumer.setMailSent(true);
		});
		appPlanDtlRepo.save(plantoUpdate.get());
	}
}
