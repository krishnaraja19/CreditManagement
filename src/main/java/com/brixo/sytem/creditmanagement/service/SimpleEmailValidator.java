package com.brixo.sytem.creditmanagement.service;
import java.util.Arrays;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SimpleEmailValidator {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailValidator.class);
	
	@Value("${mail.toaddress.invalid.message}")
	String emailErrorMsg;
	
	@Value("${mail.subject.invalid.message}")
	String subjectErrorMsg;
	
	@Value("${mail.content.invalid.message}")
	String contentErrorMsg;
	
	@Value("${mail.content.invalid.messagelength}")
	String contentLengthErrMsg;
	
	public String validator(String mailAddress,String subject,String content) {
		String errorMessage="";
		
		boolean emailAddressValid = EmailValidator.getInstance().isValid(mailAddress);
		
		
		if(mailAddress == null || mailAddress.equalsIgnoreCase("") || !emailAddressValid) {
		
			LOGGER.error("Email address is invalid: " + content.length());
			errorMessage = emailErrorMsg;
		}
		if(subject == null || subject.equalsIgnoreCase("") ) {
			LOGGER.error("Subject is invalid: " + content.length());
			errorMessage = subjectErrorMsg;
		
		}
		if(content == null || content.equalsIgnoreCase("")) {
			LOGGER.error("Content is invalid: " + content.length());
			errorMessage = contentErrorMsg;
			
		}
		 if(content.length() > 65000) {
				
			 LOGGER.error("Content is too big: " + content.length());
        	 errorMessage = contentLengthErrMsg;
        }
		return errorMessage;
	}
	
	public String validator(String[] mailAddress,String subject,String content) {
		String errorMessage="";
		
		for(String listEmailAddress:mailAddress) {
			boolean emailAddressValid = EmailValidator.getInstance().isValid(listEmailAddress);
			
			
			if(mailAddress == null || listEmailAddress.equalsIgnoreCase("") || !emailAddressValid) {
			
				LOGGER.error("Email address is invalid: " + content.length());
				errorMessage = emailErrorMsg;
			}
		}
		
		
		if(subject == null || subject.equalsIgnoreCase("") ) {
			LOGGER.error("Subject is invalid: " + content.length());
			errorMessage = subjectErrorMsg;
		
		}
		if(content == null || content.equalsIgnoreCase("")) {
			LOGGER.error("Content is invalid: " + content.length());
			errorMessage = contentErrorMsg;
			
		}
		 if(content.length() > 65000) {
				
			 LOGGER.error("Content is too big: " + content.length());
        	 errorMessage = contentLengthErrMsg;
        }
		return errorMessage;
	}

}
