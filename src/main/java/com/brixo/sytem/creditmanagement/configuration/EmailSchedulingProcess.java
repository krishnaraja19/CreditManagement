package com.brixo.sytem.creditmanagement.configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailSchedulingProcess {
	private static final Logger logger = LoggerFactory.getLogger(EmailSchedulingProcess.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Scheduled(cron = "0 * * * * ?")
	public void scheduleTaskWithCronExpression() {
		System.out.println("sdfsd sdfdsfd fghgfhgfh");
	    logger.info("Cron Task :: Execution  Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}
	
}
