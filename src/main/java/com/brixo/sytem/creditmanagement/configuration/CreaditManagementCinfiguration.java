package com.brixo.sytem.creditmanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.brixo.sytem.creditmanagement.service.EmailSenderService;
import com.brixo.sytem.creditmanagement.service.InvoicePdfWriterService;




@Configuration
public class CreaditManagementCinfiguration {
	
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@Bean 
	public EmailSenderService getEmailSenderService() {
		return new EmailSenderService();
	}

}
