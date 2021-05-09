package com.brixo.sytem.creditmanagement.configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.brixo.sytem.creditmanagement.model.Application;
import com.brixo.sytem.creditmanagement.model.ApplicationPlanDetails;
import com.brixo.sytem.creditmanagement.service.ApplicationService;
import com.brixo.sytem.creditmanagement.service.PlanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ApplicationService appService;
	
	@Autowired
	private PlanService planService;
	
	
	
	String url="https://interview.brixo.se/api/application";
    @SuppressWarnings("rawtypes")
	public static HttpEntity getHttpEntity() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Accept", "application/json");
		headers.set("Content-Type", "application/json");
		headers.set("Username", "interview");
		headers.set("Password", "Hkn7epU0h2g1wS");
    	
    	@SuppressWarnings("unchecked")
		HttpEntity entity = new HttpEntity(headers);
    	return entity;
    }
    
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// TODO Auto-generated method stub
		
		saveAllApplicationData();	  
         
		appDetailsDataInsertion();
	}
	
	public void saveAllApplicationData() {
		List<Application> applicationList = null;
		try {
			applicationList = ObjectMapping();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		appService.saveAllApplication(applicationList);
	}
	
	public List<Application> ObjectMapping() throws ParseException, JsonMappingException, JsonProcessingException{
		JSONArray jsonArray  = getAllApplicationJsonArray();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
		System.out.println(jsonArray.toString());
		Application[] applications = null;
		applications = mapper.readValue(jsonArray.toString(), Application[].class);
		return Arrays.asList(applications);
	}
	
	public JSONArray getAllApplicationJsonArray() throws ParseException {
		JSONArray jsonArray = null;
		ResponseEntity<String> responseEntity  =   restTemplate.exchange(
			    url, HttpMethod.GET, getHttpEntity(),  String.class);
		
		if(responseEntity.getStatusCode() == HttpStatus.OK){
			
			String response = responseEntity.getBody();
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = null;
			jsonObject = (JSONObject) parser.parse(response);
			jsonArray  = (JSONArray) jsonObject.get("Response");
			  
          } else {
            System.out.println("error occurred");
            System.out.println(responseEntity.getStatusCode());
        }
		return jsonArray;
	}
	public void appDetailsDataInsertion() {
		List<Application> listofApplication = appService.getAllApplication();
		float interestAmount;
		float interest;
		float amorizationAmount;
		int plan =0;
		float mPA = 0; 
		for(Application application:listofApplication) {
			
			plan = 0;
			for(int i=0;i<=application.getPaybackPeriod();i++) {
				ApplicationPlanDetails planDetail = new ApplicationPlanDetails();
				if(plan == 0) {
					planDetail.setPlan(plan);
					planDetail.setAmortization((float) 0);
					planDetail.setInterest((float) 0);
					planDetail.setInvoiceFee((float) 0);
					planDetail.setMonthlyPayableAmount((float) 0);
					planDetail.setDebtBalance(Float.valueOf(application.getApprovedAmount()));
					planDetail.setApplication(application);
					planDetail.setStartTime(LocalDateTime.now());
					planDetail.setEndTime(LocalDateTime.now().plusMinutes(5));
					
					planService.saveApplicationPlanDetail(planDetail);
					
				}else {
					ApplicationPlanDetails lastPlan = new ApplicationPlanDetails();
					lastPlan = planService.findByApplicationAndPlanDetails(application, plan-1);
					planDetail.setPlan(plan);
					amorizationAmount = Math.round(Float.valueOf(application.getApprovedAmount())/application.getPaybackPeriod());
					planDetail.setAmortization(amorizationAmount);
					interest= Float.valueOf(application.getInterestRate())/100;
					interestAmount = (lastPlan.getDebtBalance() * interest)/12;
					planDetail.setInterest(Math.round(interestAmount));
					planDetail.setInvoiceFee(Math.round(Float.valueOf(application.getInvoiceFee())));
					mPA = amorizationAmount
							+interestAmount+Float.valueOf(application.getInvoiceFee());
					planDetail.setMonthlyPayableAmount(Math.round(mPA));
					if(plan==application.getPaybackPeriod()) 
						planDetail.setDebtBalance((float) 0);
					else
						planDetail.setDebtBalance(Math.round(Float.valueOf(lastPlan.getDebtBalance())-amorizationAmount));
					planDetail.setApplication(application);
					planDetail.setStartTime(lastPlan.getEndTime());
					planDetail.setEndTime(lastPlan.getEndTime().plusMinutes(5));
					planService.saveApplicationPlanDetail(planDetail);
				}
				plan++;
			}
			
			
		}
		
	}

}
