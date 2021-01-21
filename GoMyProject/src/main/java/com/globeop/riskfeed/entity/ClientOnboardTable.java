package com.globeop.riskfeed.entity;


import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globeop.riskfeed.enums.AutomationProcess;
import com.globeop.riskfeed.enums.IsActive;

@Entity  
@Table(name="ClientOnboardTable")  
public class ClientOnboardTable implements Serializable{

	@Id   
	@Column(name = "ClientOnboardId")
	@GeneratedValue	(strategy=GenerationType.IDENTITY)  
	private int clientOnboardId;

	@JsonBackReference
	@ManyToOne(cascade=CascadeType.PERSIST,fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ClientID", nullable = false)    
	private ClientTable client;

	@JsonBackReference
	@ManyToOne(cascade=CascadeType.PERSIST,fetch = FetchType.EAGER,optional = false)
	@JoinColumn(name="RiskAggregatorId", nullable = false)
	private RiskAggregator riskAggregator;
	
	@JsonBackReference
	@ManyToOne(cascade=CascadeType.PERSIST,fetch = FetchType.EAGER,optional = false)
	@JoinColumn(name="FundID", nullable = false)  
	private FundTable fund;
		
	@Column(name = "SetUpDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate setUpDate;
	
	@Column(name = "EndDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "AutomationProcess")
	private AutomationProcess automationProcess;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "IsActive")
	private IsActive isActive;
	
	@Column(name = "Comments")
	private String comments;
	
	//@Enumerated(EnumType.STRING)
	@Column(name = "Frequency")
	private String frequency;
	//private Frequency Frequency;
	
	@Column(name = "Modified_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modified_date;
	
	public ClientOnboardTable() {
		
	}
	
	public RiskAggregator getRiskAggregator() {
		return riskAggregator;
	}
	public void setRiskAggregator(RiskAggregator riskAggregator) {
		this.riskAggregator = riskAggregator;
	}
		
	public int getClientOnboardId() {
		return clientOnboardId;
	}
	public void setClientOnboardId(int clientOnboardId) {
		this.clientOnboardId = clientOnboardId;
	}
	public ClientTable getClient() {
		return client;
	}
	public void setClient(ClientTable client) {
		this.client = client;
	}
	public FundTable getFund() {
		return fund;
	}
	public void setFund(FundTable fund) {
		this.fund = fund;
	}
	public LocalDate getSetUpDate() {
		return setUpDate;
	}
	public void setSetUpDate(LocalDate setUpDate) {
		this.setUpDate = setUpDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
		
	public String getFrequency() {		
		return frequency;
	}
	
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public LocalDate getModified_date() {
		return modified_date;
	}
	public void setModified_date(LocalDate modified_date) {
		this.modified_date = modified_date;
	}
	public AutomationProcess getAutomationProcess() {
		return automationProcess;
	}
	public void setAutomationProcess(AutomationProcess theAutomationProcess) {
		this.automationProcess=theAutomationProcess;
	}
	public IsActive getIsActive() {
		return isActive;
	}
	public void setIsActive(IsActive theIsActive) {
		this.isActive=theIsActive;
	}
	/*
	 * @Override public String toString() { return
	 * "ClientOnboardTable [clientOnboardId=" + clientOnboardId + ", client=" +
	 * client.getClientID() + ", riskAggregator=" +
	 * riskAggregator.getRiskAggregatorId() + ", fund=" + fund.getFundID() +
	 * ", setUpDate=" + setUpDate + ", endDate=" + endDate + ", automationProcess="
	 * + automationProcess + ", isActive=" + isActive + ", comments=" + comments +
	 * ", frequency=" + frequency + ", modified_date=" + modified_date + "]"; }
	 */
	/*
	 * @Override public String toString() { return
	 * "ClientOnboardTable [clientOnboardId=" + clientOnboardId + ", client=" +
	 * client.getClientID() + ", riskAggregator=" +
	 * riskAggregator.getRiskAggregatorId() + ", fund=" + fund + "]"; }
	 */
	
	
	
	
	
	  @Override public String toString() { 
		  return
	  "ClientOnboardTable [clientOnboardId=" + clientOnboardId + ", clientId=" +client.getClientID() +  ", clientName=" +client.getClientShortName() +
	  ", riskAggregatorId=" +riskAggregator.getRiskAggregatorId() + ", riskAggregatorName=" +riskAggregator.getRiskAggregatorName() + ","
	  		+ " fund=" + fund.getFundID() +
	  ", setUpDate=" + setUpDate + ", endDate=" + endDate + ", automationProcess="
	  + automationProcess + ", isActive=" + isActive + ", comments=" + comments +
	  ", frequency=" + frequency + ", modified_date=" + modified_date + "]"; }
	 
	
	
	
		
}
