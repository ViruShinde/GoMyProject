package com.globeop.riskfeed.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globeop.riskfeed.enums.AutomationProcess;
import com.globeop.riskfeed.enums.Frequency;
import com.globeop.riskfeed.enums.IsActive;

@Entity  
@Table(name="ClientOnboardTable")  
public class ClientOnboardTable implements Serializable{

	@Id   
	@Column(name = "ClientOnboardId")
	@GeneratedValue	(strategy=GenerationType.IDENTITY)  
	private int ClientOnboardId;

	@JsonBackReference
	@ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ClientID", nullable = false)    
	private ClientTable client;

	@JsonBackReference
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="RiskAggregatorId", nullable = false)
	private RiskAggregator riskAggregator;
	
	@JsonBackReference
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="FundID", nullable = false)  
	private FundTable fund;
		
	@Column(name = "SetUpDate")
	private Date SetUpDate;
	
	@Column(name = "EndDate")
	private Date EndDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "AutomationProcess")
	private AutomationProcess automationProcess;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "IsActive")
	private IsActive isActive;
	
	@Column(name = "Comments")
	private String Comments;
	
	//@Enumerated(EnumType.STRING)
	@Column(name = "Frequency")
	private String Frequency;
	//private Frequency Frequency;
	
	@Column(name = "Modified_date")
	private Date Modified_date;
	
	
	public RiskAggregator getRiskAggregator() {
		return riskAggregator;
	}
	public void setRiskAggregator(RiskAggregator riskAggregator) {
		this.riskAggregator = riskAggregator;
	}
		
	public int getClientOnboardId() {
		return ClientOnboardId;
	}
	public void setClientOnboardId(int clientOnboardId) {
		ClientOnboardId = clientOnboardId;
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
	public Date getSetUpDate() {
		return SetUpDate;
	}
	public void setSetUpDate(Date setUpDate) {
		SetUpDate = setUpDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}	
	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}
		
	public String getFrequency() {		
		return Frequency;
	}
	
	public void setFrequency(String frequency) {
		this.Frequency = frequency;
	}
	public Date getModified_date() {
		return Modified_date;
	}
	public void setModified_date(Date modified_date) {
		Modified_date = modified_date;
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

	@Override
	public String toString() {
		return "ClientOnboardTable [ClientOnboardId=" + ClientOnboardId + ", client=" + client.getClientID() + ", riskAggregator="
				+ riskAggregator.getId() + ", fund=" + fund.getFundID() + ", SetUpDate=" + SetUpDate + ", EndDate=" + EndDate
				+ ", automationProcess=" + automationProcess + ", isActive=" + isActive + ", Comments=" + Comments
				+ ", Frequency=" + Frequency + ", Modified_date=" + Modified_date + "]";
	}	
		
}
