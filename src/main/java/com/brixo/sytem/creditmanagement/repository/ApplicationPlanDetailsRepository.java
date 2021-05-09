package com.brixo.sytem.creditmanagement.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brixo.sytem.creditmanagement.model.Application;
import com.brixo.sytem.creditmanagement.model.ApplicationPlanDetails;

@Repository
public interface ApplicationPlanDetailsRepository extends JpaRepository<ApplicationPlanDetails, Long>{
	ApplicationPlanDetails findByApplicationAndPlan(Application application, int plan);
	List<ApplicationPlanDetails> findAllByStartTimeGreaterThanEqualAndStartTimeLessThanEqualAndPlanNotAndIsMailSent(LocalDateTime startDate,LocalDateTime startDatee,int plan,boolean flag);
}
