package com.brixo.sytem.creditmanagement.configuration;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.brixo.sytem.creditmanagement.model.ApplicationPlanDetails;
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
    
	@Scheduled(cron = "0 * * * * ?")
	public void scheduleTaskWithCronExpression() throws FileNotFoundException, DocumentException {
		
	    logger.info("Cron Task :: Execution  Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	    
	    LocalDateTime currenttime = LocalDateTime.now();
	    List<ApplicationPlanDetails> getAllValidInvoicingData =
	    		planService.findAllValidPlan(currenttime,currenttime.now().plusMinutes(5));
		System.out.println(getAllValidInvoicingData.size());
		System.out.println(" time "+currenttime);
		
	    getAllValidInvoicingData.forEach(action->{
	    	
	    	 try {
				pdfService.pdfWriter(action);
			} catch (FileNotFoundException | DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    });
	   
	    
	}
	
}
