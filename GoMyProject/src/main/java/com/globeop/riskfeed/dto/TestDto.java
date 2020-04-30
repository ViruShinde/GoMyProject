package com.globeop.riskfeed.dto;

import java.util.Date;

import com.globeop.riskfeed.enums.AutomationProcess;
import com.globeop.riskfeed.enums.IsActive;
import com.globeop.riskfeed.enums.IsClientPayingOldCharges;
import com.globeop.riskfeed.enums.IsWaivedOff;

public class TestDto {

	private int riskAggregatorId, clientID, fundID, clientOnboardId;

	private String riskAggregatorName, riskAggregatorContact, clientName, fundName;

	private Date setUpDate, endDate;

	private AutomationProcess automationProcess;

	private IsActive isActive;

	private String clientOnBoardComments, frequency;

	private int billId, setupFee, monthlyFee, devlopementFee;

	private IsClientPayingOldCharges isClientPayingOldCharges;

	private IsWaivedOff isWaivedOff;

	private byte[] waiverMail;
	
	private Date billStartDate, billEndDate;
	
	private String crmName, crmailID, billingComments;
	
	private int goCheckNoteId, fundcount;

	private int developmentId, developmentHours, developmentCost;
	
	private IsWaivedOff isDevelopmentWaivedOff;
	
	private Date developmentStartDate, developmentEndDate;
	
	private String developmentComments;
	
	public TestDto(String riskAggregatorName, String clientName) {
		this.riskAggregatorName = riskAggregatorName;
		this.clientName = clientName;
	}
	
	public TestDto(int riskAggregatorId, String riskAggregatorName, String riskAggregatorContact, 
			int  clientID, String clientName, 
			int fundId, String fundName,
			int clientOnboardId, Date setUpDate, Date endDate, AutomationProcess automationProcess, IsActive isActive, String clientOnBoardComments, String frequency,
			int billId, int setupFee, int monthlyFee, int devlopementFee, IsClientPayingOldCharges isClientPayingOldCharges, IsWaivedOff isWaivedOff, Date billStartDate, Date billEndDate, String crmName, String crmailID, String billingComments, int goCheckNoteId, int fundcount,
			
			//d.developmentId,   d.developmentHours,     d.developmentCost, d.isWaivedOff, 						d.startDate,  				d.endDate, 				d.developmentComment
			Integer developmentId
			, Integer developmentHours, Integer developmentCost, IsWaivedOff isDevelopmentWaivedOff, Date developmentStartDate, Date developmentEndDate, String developmentComments 
			
			) {		
		
		this.riskAggregatorId=riskAggregatorId;
		this.riskAggregatorName = riskAggregatorName;
		this.riskAggregatorContact=riskAggregatorContact;
		
		this.clientID=clientID;
		this.clientName = clientName;
		
		this.fundID=fundId;
		this.fundName = fundName;
		
		this.clientOnboardId=clientOnboardId;
		this.setUpDate=setUpDate;
		this.endDate=endDate;
		this.automationProcess=automationProcess;
		this.isActive=isActive;
		this.clientOnBoardComments=clientOnBoardComments;
		this.frequency=frequency;
		
		
		  this.billId=billId; this.setupFee=setupFee; this.monthlyFee = monthlyFee;
		  this.devlopementFee=devlopementFee;
		  this.isClientPayingOldCharges=isClientPayingOldCharges;
		  this.isWaivedOff=isWaivedOff; //this.waiverMail=waiverMail;
		  this.billStartDate=billStartDate; this.billEndDate=billEndDate;
		  this.crmName=crmName; this.crmailID=crmailID;
		  this.billingComments=billingComments; this.goCheckNoteId=goCheckNoteId;
		  this.fundcount=fundcount;
		 
		  if(developmentId!=null)
		  this.developmentId=developmentId; 
		
		  if(developmentHours!=null)
		  this.developmentHours=developmentHours; 
		  if(developmentCost!=null)
		  this.developmentCost=developmentCost;		  
		  if(isDevelopmentWaivedOff!=null)
		  this.isDevelopmentWaivedOff=isDevelopmentWaivedOff;		  
		  if(developmentStartDate!=null)
		  this.developmentStartDate=developmentStartDate;
		  if(developmentEndDate!=null)
		  this.developmentEndDate=developmentEndDate;
		  if(developmentComments!=null)
		  this.developmentComments=developmentComments;
		 
		 
	}

	/*
	 * public TestDto(int riskAggregatorId, String riskAggregatorName, String
	 * riskAggregatorContact, int clientID, String clientName, int fundId, String
	 * fundName, int clientOnboardId, Date setUpDate, Date endDate,
	 * AutomationProcess automationProcess, IsActive isActive, String
	 * clientOnBoardComments, String frequency, int billId, int setupFee, int
	 * monthlyFee, int devlopementFee, IsClientPayingOldCharges
	 * isClientPayingOldCharges, IsWaivedOff isWaivedOff , byte[] waiverMail ) {
	 * 
	 * this.riskAggregatorId=riskAggregatorId; this.riskAggregatorName =
	 * riskAggregatorName; this.riskAggregatorContact=riskAggregatorContact;
	 * 
	 * this.clientID=clientID; this.clientName = clientName;
	 * 
	 * this.fundID=fundId; this.fundName = fundName;
	 * 
	 * this.clientOnboardId=clientOnboardId; this.setUpDate=setUpDate;
	 * this.endDate=endDate; this.automationProcess=automationProcess;
	 * this.isActive=isActive; this.clientOnBoardComments=clientOnBoardComments;
	 * this.frequency=frequency;
	 * 
	 * this.billId=billId; this.setupFee=setupFee; this.monthlyFee = monthlyFee;
	 * this.devlopementFee=devlopementFee;
	 * this.isClientPayingOldCharges=isClientPayingOldCharges;
	 * this.isWaivedOff=isWaivedOff; //this.waiverMail=waiverMail;
	 * 
	 * }
	 */

	public TestDto(String riskAggregatorName, String clientName, String fundName, int monthlyFee) {
		this.riskAggregatorName = riskAggregatorName;
		this.clientName = clientName;
		this.fundName = fundName;
		this.monthlyFee = monthlyFee;
	}

	public TestDto(int clientID, int riskAggregatorId, int fundID) {
		this.clientID = clientID;
		this.riskAggregatorId = riskAggregatorId;
		this.fundID = fundID;
	}

	public TestDto(int clientID) {
		super();
		this.clientID = clientID;
	}

	
	
	
	
	public int getDevelopmentId() {
		return developmentId;
	}

	public void setDevelopmentId(int developmentId) {
		this.developmentId = developmentId;
	}

	public int getDevelopmentHours() {
		return developmentHours;
	}

	public void setDevelopmentHours(int developmentHours) {
		this.developmentHours = developmentHours;
	}

	public int getDevelopmentCost() {
		return developmentCost;
	}

	public void setDevelopmentCost(int developmentCost) {
		this.developmentCost = developmentCost;
	}

	public IsWaivedOff getIsDevelopmentWaivedOff() {
		return isDevelopmentWaivedOff;
	}

	public void setIsDevelopmentWaivedOff(IsWaivedOff isDevelopmentWaivedOff) {
		this.isDevelopmentWaivedOff = isDevelopmentWaivedOff;
	}

	public Date getDevelopmentStartDate() {
		return developmentStartDate;
	}

	public void setDevelopmentStartDate(Date developmentStartDate) {
		this.developmentStartDate = developmentStartDate;
	}

	public Date getDevelopmentEndDate() {
		return developmentEndDate;
	}

	public void setDevelopmentEndDate(Date developmentEndDate) {
		this.developmentEndDate = developmentEndDate;
	}

	public String getDevelopmentComments() {
		return developmentComments;
	}

	public void setDevelopmentComments(String developmentComments) {
		this.developmentComments = developmentComments;
	}

	public Date getBillStartDate() {
		return billStartDate;
	}

	public void setBillStartDate(Date billStartDate) {
		this.billStartDate = billStartDate;
	}

	public Date getBillEndDate() {
		return billEndDate;
	}

	public void setBillEndDate(Date billEndDate) {
		this.billEndDate = billEndDate;
	}

	public String getCrmName() {
		return crmName;
	}

	public void setCrmName(String crmName) {
		this.crmName = crmName;
	}

	public String getCrmailID() {
		return crmailID;
	}

	public void setCrmailID(String crmailID) {
		this.crmailID = crmailID;
	}

	public String getBillingComments() {
		return billingComments;
	}

	public void setBillingComments(String billingComments) {
		this.billingComments = billingComments;
	}

	public int getGoCheckNoteId() {
		return goCheckNoteId;
	}

	public void setGoCheckNoteId(int goCheckNoteId) {
		this.goCheckNoteId = goCheckNoteId;
	}

	public int getFundcount() {
		return fundcount;
	}

	public void setFundcount(int fundcount) {
		this.fundcount = fundcount;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getSetupFee() {
		return setupFee;
	}

	public void setSetupFee(int setupFee) {
		this.setupFee = setupFee;
	}

	public int getDevlopementFee() {
		return devlopementFee;
	}

	public void setDevlopementFee(int devlopementFee) {
		this.devlopementFee = devlopementFee;
	}

	public IsClientPayingOldCharges getIsClientPayingOldCharges() {
		return isClientPayingOldCharges;
	}

	public void setIsClientPayingOldCharges(IsClientPayingOldCharges isClientPayingOldCharges) {
		this.isClientPayingOldCharges = isClientPayingOldCharges;
	}

	public IsWaivedOff getIsWaivedOff() {
		return isWaivedOff;
	}

	public void setIsWaivedOff(IsWaivedOff isWaivedOff) {
		this.isWaivedOff = isWaivedOff;
	}

	public byte[] getWaiverMail() {
		return waiverMail;
	}

	public void setWaiverMail(byte[] waiverMail) {
		this.waiverMail = waiverMail;
	}

	public int getClientOnboardId() {
		return clientOnboardId;
	}

	public void setClientOnboardId(int clientOnboardId) {
		this.clientOnboardId = clientOnboardId;
	}

	public String getRiskAggregatorContact() {
		return riskAggregatorContact;
	}

	public void setRiskAggregatorContact(String riskAggregatorContact) {
		this.riskAggregatorContact = riskAggregatorContact;
	}

	public Date getSetUpDate() {
		return setUpDate;
	}

	public void setSetUpDate(Date setUpDate) {
		this.setUpDate = setUpDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public AutomationProcess getAutomationProcess() {
		return automationProcess;
	}

	public void setAutomationProcess(AutomationProcess automationProcess) {
		this.automationProcess = automationProcess;
	}

	public IsActive getIsActive() {
		return isActive;
	}

	public void setIsActive(IsActive isActive) {
		this.isActive = isActive;
	}

	public String getClientOnBoardComments() {
		return clientOnBoardComments;
	}

	public void setClientOnBoardComments(String clientOnBoardComments) {
		this.clientOnBoardComments = clientOnBoardComments;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public int getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(int monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public String getRiskAggregatorName() {
		return riskAggregatorName;
	}

	public void setRiskAggregatorName(String riskAggregatorName) {
		this.riskAggregatorName = riskAggregatorName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public int getRiskAggregatorId() {
		return riskAggregatorId;
	}

	public void setRiskAggregatorId(int riskAggregatorId) {
		this.riskAggregatorId = riskAggregatorId;
	}

	public int getFundID() {
		return fundID;
	}

	public void setFundID(int fundID) {
		this.fundID = fundID;
	}

	@Override
	public String toString() {
		return "TestDto [clientID=" + clientID + ", riskAggregatorId=" + riskAggregatorId + ", fundID=" + fundID
				+ ", monthlyFee=" + monthlyFee + ", riskAggregatorName=" + riskAggregatorName + ", clientName="
				+ clientName + ", fundName=" + fundName + "]";
	}

	/*
	 * @Override public String toString() { return "TestDto [riskAggregatorId=" +
	 * riskAggregatorId + ", clientName=" + clientName + " , monthlyFee=" +
	 * monthlyFee +"]"; }
	 */

}
