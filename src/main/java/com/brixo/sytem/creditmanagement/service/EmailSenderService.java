package com.brixo.sytem.creditmanagement.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.brixo.sytem.creditmanagement.model.ApplicationPlanDetails;
import com.brixo.sytem.creditmanagement.model.IEmailSender;


public class EmailSenderService implements IEmailSender {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);
	 
	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	public SimpleEmailValidator simpleEmailValidator;
	
	@Value("${mail.response.message}")
	String responseMessage;
	public String sendEmail(ApplicationPlanDetails details,String name) throws MessagingException {
		// TODO Auto-generated method stub
		 LOGGER.info("EmailServiceImpl will start the validation process for bulk email");
		 
		 
		 String subject =  "Brixo Monthly Invoice";
		 String content = "Please find the attached Brixo invoice";
		 
		 
		 String errorMessage = simpleEmailValidator.validator(details.getApplication().getEmail(),
				  subject, content);
		 
		 
		  LOGGER.info("The validation process is completed");
		  
		  if(errorMessage.isEmpty()) {
			  
	      MimeMessage message = emailSender.createMimeMessage();
		  MimeMessageHelper helper = new MimeMessageHelper(message, true);
		  
		  helper.setFrom("krishnaaltran@gmail.com");
		  //helper.setTo(details.getApplication().getEmail());
		  helper.setTo("krishnaraja19@gmail.com");
		    helper.setSubject(subject);
		    helper.setText(content);
		    FileSystemResource file 
		      = new FileSystemResource(new File(name));
		    helper.addAttachment(name, file);
		    
		    emailSender.send(message);
	   
			
		  LOGGER.info("Mails sent successfully");
		  return responseMessage;
		  }else {
			  LOGGER.info("The validation process is throw the error");
			  return errorMessage;
		  }
	}
	

}
