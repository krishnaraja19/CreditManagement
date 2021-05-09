package com.brixo.sytem.creditmanagement.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application")
public class Application implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="application_id", nullable = false)
	private int id;
	private String firstName;
	private String lastName;
	private String ssn;
	private String phone;
	private String email;
	private String loanType;
	private String approvedAmount;
	private int paybackPeriod;
	private String interestRate;
	private String invoiceFee;
	private String status;
	private String created_at;
	private String updated_at;
  
	@OneToMany(mappedBy = "application")
	@JsonIgnore
	private List<ApplicationPlanDetails> planDetails;
}
