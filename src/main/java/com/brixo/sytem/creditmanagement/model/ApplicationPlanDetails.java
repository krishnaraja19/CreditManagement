package com.brixo.sytem.creditmanagement.model;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ForeignKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "applicationdetails")
public class ApplicationPlanDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int plan;
	private float amortization;
	private float interest;
	private float invoiceFee;
	private float monthlyPayableAmount;
	private float debtBalance;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private boolean isMailSent = false;
	
	@ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "application_id"), name = "application_id")
	@JsonIgnore
	private Application application;
}
