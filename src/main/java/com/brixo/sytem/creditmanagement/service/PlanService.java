package com.brixo.sytem.creditmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brixo.sytem.creditmanagement.model.Application;
import com.brixo.sytem.creditmanagement.model.ApplicationPlanDetails;
import com.brixo.sytem.creditmanagement.repository.ApplicationPlanDetailsRepository;


@Service
public class PlanService {
	
	@Autowired
	private ApplicationPlanDetailsRepository appPlanDtlRepo;
	
	public List<ApplicationPlanDetails> getAllApplicationPlanDetails() {
		 return appPlanDtlRepo.findAll();
		
	}
	public void saveApplicationPlanDetail(ApplicationPlanDetails planDetail) {
		appPlanDtlRepo.save(planDetail);
	}
	public void saveAllApplicationPlanDetails(List<ApplicationPlanDetails> planDetails) {
		appPlanDtlRepo.saveAll(planDetails);
	}
	public ApplicationPlanDetails findByApplicationAndPlanDetails(Application application, int plan) {
		return appPlanDtlRepo.findByApplicationAndPlan(application, plan);
	}
	public List<ApplicationPlanDetails> findAllValidPlan(LocalDateTime StartTime,LocalDateTime EndTime) {
		return appPlanDtlRepo.findAllByStartTimeGreaterThanEqualAndStartTimeLessThanEqualAndPlanNotAndIsMailSent(StartTime, EndTime,0,false);
		
	}
	
	public void updateByPlanId(ApplicationPlanDetails planDtl) {
		Optional<ApplicationPlanDetails> plantoUpdate = appPlanDtlRepo.findById(planDtl.getId());
		plantoUpdate.ifPresent(consumer->{
			consumer.setMailSent(true);
		});
		appPlanDtlRepo.save(plantoUpdate.get());
	}
}
