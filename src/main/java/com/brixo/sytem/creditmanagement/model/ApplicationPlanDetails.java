package com.brixo.sytem.creditmanagement.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	int id;
	private int plan;
	private float amortization;
	private float interest;
	private float invoiceFee;
	private float monthlyPayableAmount;
	private float debtBalance;
	
	@Column(name = "start_date")
	private LocalDateTime startTime;
	
	@Column(name = "end_date")
	private LocalDateTime endTime;
	
	@ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "application_id"), name = "application_id")
	private Application application;
}
