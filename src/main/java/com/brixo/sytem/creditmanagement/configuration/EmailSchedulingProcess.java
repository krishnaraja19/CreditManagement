package com.brixo.sytem.creditmanagement.configuration;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.brixo.sytem.creditmanagement.model.ApplicationPlanDetails;
import com.brixo.sytem.creditmanagement.service.EmailSenderService;
import com.brixo.sytem.creditmanagement.service.InvoicePdfWriterService;
import com.brixo.sytem.creditmanagement.service.PlanService;
import com.itextpdf.text.DocumentException;

@Component
public class EmailSchedulingProcess {
	private static final Logger logger = LoggerFactory.getLogger(EmailSchedulingProcess.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
    @Autowired
    private InvoicePdfWriterService pdfService;
    
	@Autowired
	private PlanService planService;
	
	@Autowired
	private EmailSenderService emailService;
	 
    
	@Scheduled(cron = "0 * * * * ?")
	public void scheduleTaskWithCronExpression() throws FileNotFoundException, DocumentException, MessagingException {
		
	    logger.info("Cron Task :: Execution  Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	    String name;
	    String response;
	    LocalDateTime currenttime = LocalDateTime.now();
	    List<ApplicationPlanDetails> getAllValidInvoicingData =
	    		planService.findAllValidPlan(currenttime,currenttime.now().plusMinutes(5));

	
		for(ApplicationPlanDetails action:getAllValidInvoicingData) {
				 name = pdfService.pdfWriter(action);
				 response = emailService.sendEmail(action,name);
				 if( response.equalsIgnoreCase("Mail Sent Successfully") )
					 planService.updateByPlanId(action);
		}
	    
	   
	    
	}
	

}
