package com.brixo.sytem.creditmanagement.model;

import javax.mail.MessagingException;

@FunctionalInterface
public interface IEmailSender {
	public String sendEmail(ApplicationPlanDetails details,String fileName) throws MessagingException;
}
