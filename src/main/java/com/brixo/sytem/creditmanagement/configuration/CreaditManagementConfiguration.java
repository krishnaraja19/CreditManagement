package com.brixo.sytem.creditmanagement.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.brixo.sytem.creditmanagement.service.ApplicationService;
import com.brixo.sytem.creditmanagement.service.EmailSenderService;
import com.brixo.sytem.creditmanagement.service.InvoicePdfWriterService;




@Configuration
public class CreaditManagementConfiguration {
	
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@Bean 
	public EmailSenderService getEmailSenderService() {
		return new EmailSenderService();
	}
	@Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}
