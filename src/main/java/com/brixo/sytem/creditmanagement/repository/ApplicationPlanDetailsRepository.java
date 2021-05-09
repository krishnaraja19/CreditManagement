package com.brixo.sytem.creditmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brixo.sytem.creditmanagement.model.Application;
import com.brixo.sytem.creditmanagement.model.ApplicationPlanDetails;

@Repository
public interface ApplicationPlanDetailsRepository extends JpaRepository<ApplicationPlanDetails, Long>{
	ApplicationPlanDetails findByApplicationAndPlan(Application application, int plan);
}