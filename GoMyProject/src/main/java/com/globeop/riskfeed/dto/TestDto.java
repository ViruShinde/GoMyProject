package com.globeop.riskfeed.dto;

import java.time.LocalDate;
import java.util.Date;

import com.globeop.riskfeed.enums.AutomationProcess;
import com.globeop.riskfeed.enums.Frequency;
import com.globeop.riskfeed.enums.IsActive;
import com.globeop.riskfeed.enums.IsClientPayingOldCharges;
import com.globeop.riskfeed.enums.IsWaivedOff;

public class TestDto {

	private int riskAggregatorId, clientID, fundID, clientOnboardId;

	private String riskAggregatorName, riskAggregatorContact, clientName, fundName;

	private LocalDate setUpDate, endDate;

	private AutomationProcess automationProcess;

	private IsActive isActive;
	
	//private Frequency frequency;
	
	private String frequency;

	private String clientOnBoardComments;

	private Integer billId;
	
	private int  setupFee, monthlyFee, devlopementFee;

	private IsClientPayingOldCharges isClientPayingOldCharges;

	private IsWaivedOff isWaivedOff;

	private byte[] waiverMail;
	
	private LocalDate billStartDate, billEndDate;
	
	private String crmName, crmailID, billingComments;
	
	private int goCheckNoteId, fundcount;
	
	private long count;

	private int developmentId, developmentHours, developmentCost;
	
	private IsWaivedOff isDevelopmentWaivedOff;
	
	private LocalDate developmentStartDate, developmentEndDate, modified_date;
	
	private String developmentComments;
	
	
	//2
	public TestDto(String riskAggregatorName, String clientName) {
		this.riskAggregatorName = riskAggregatorName;
		this.clientName = clientName;
	}
	
	//3
	public TestDto(int riskAggregatorId,String riskAggregatorName, long count) {
		this.riskAggregatorId=riskAggregatorId;
		this.riskAggregatorName = riskAggregatorName;
		this.count = count;
	}
	
	//34
	public TestDto(int riskAggregatorId, String riskAggregatorName, String riskAggregatorContact, 
			int  clientID, String clientName, 
			int fundId, String fundName,
			int clientOnboardId, LocalDate setUpDate, LocalDate endDate, AutomationProcess automationProcess, IsActive isActive, String clientOnBoardComments, String frequency,
			int billId, int setupFee, int monthlyFee, int devlopementFee, IsClientPayingOldCharges isClientPayingOldCharges, IsWaivedOff isWaivedOff, LocalDate billStartDate, LocalDate billEndDate, String crmName, String crmailID, String billingComments, int goCheckNoteId, int fundcount,
			
			//d.developmentId,   d.developmentHours,     d.developmentCost, d.isWaivedOff, 						d.startDate,  				d.endDate, 				d.developmentComment
			Integer developmentId
			, Integer developmentHours, Integer developmentCost, IsWaivedOff isDevelopmentWaivedOff, LocalDate developmentStartDate, LocalDate developmentEndDate, String developmentComments 
			
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
		  this.billStartDate=billStartDate; 
		  this.billEndDate=billEndDate;
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

	//4
	public TestDto(String riskAggregatorName, String clientName, String fundName, int monthlyFee) {
		this.riskAggregatorName = riskAggregatorName;
		this.clientName = clientName;
		this.fundName = fundName;
		this.monthlyFee = monthlyFee;
	}
	
	 //10 
	public TestDto(int clientOnboardId,int clientID, String clientName, String fundName, LocalDate setUpDate,IsActive isActive, String frequency, AutomationProcess automationProcess,String comment, LocalDate  modifDate) {		
		this.clientOnboardId=clientOnboardId;
		this.clientID = clientID;
		this.clientName = clientName;
		this.fundName = fundName;
		this.setUpDate=setUpDate;
		this.isActive=isActive;
		this.frequency=frequency;
		this.automationProcess=automationProcess;
		this.clientOnBoardComments =comment;
		this.modified_date = modifDate;
	}

	//3
	public TestDto(int clientID, int riskAggregatorId, int fundID) {
		this.clientID = clientID;
		this.riskAggregatorId = riskAggregatorId;
		this.fundID = fundID;
	}

	//1
	public TestDto(int clientID) {
		super();
		this.clientID = clientID;
	}

	//3
	public TestDto(int clientId, String clientName, LocalDate setUpDate) {
		this.clientID=clientId;
		this.clientName=clientName;
		this.setUpDate = setUpDate;
	}
	
	//7
	public TestDto(int riskAggregatorId, String riskAggregatorName, int clientId, String clientName, Integer billId, LocalDate billStartDate, LocalDate billEndtDate) {
		this.riskAggregatorId=riskAggregatorId;
		this.riskAggregatorName=riskAggregatorName;
		this.clientID=clientId;
		this.clientName=clientName;
		try {
			this.billId=billId;
		} catch (Exception e) {
			this.billId=-1;
		}
		
		this.billStartDate = billStartDate;
		this.billEndDate=billEndtDate;
	}
	
	
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
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

	public LocalDate getDevelopmentStartDate() {
		return developmentStartDate;
	}

	public void setDevelopmentStartDate(LocalDate developmentStartDate) {
		this.developmentStartDate = developmentStartDate;
	}

	public LocalDate getDevelopmentEndDate() {
		return developmentEndDate;
	}

	public void setDevelopmentEndDate(LocalDate developmentEndDate) {
		this.developmentEndDate = developmentEndDate;
	}

	public String getDevelopmentComments() {
		return developmentComments;
	}

	public void setDevelopmentComments(String developmentComments) {
		this.developmentComments = developmentComments;
	}

	public LocalDate getBillStartDate() {
		return billStartDate;
	}

	public void setBillStartDate(LocalDate billStartDate) {
		this.billStartDate = billStartDate;
	}

	public LocalDate getBillEndDate() {
		return billEndDate;
	}

	public void setBillEndDate(LocalDate billEndDate) {
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

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
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

	
	public LocalDate getModified_date() {
		return modified_date;
	}

	public void setModified_date(LocalDate modified_date) {
		this.modified_date = modified_date;
	}

	@Override
	public String toString() {
		return "TestDto [riskAggregatorId=" + riskAggregatorId + ", clientID=" + clientID + ", fundID=" + fundID
				+ ", clientOnboardId=" + clientOnboardId + ", riskAggregatorName=" + riskAggregatorName
				+ ", riskAggregatorContact=" + riskAggregatorContact + ", clientName=" + clientName + ", fundName="
				+ fundName + ", setUpDate=" + setUpDate + ", endDate=" + endDate + ", automationProcess="
				+ automationProcess + ", isActive=" + isActive + ", clientOnBoardComments=" + clientOnBoardComments
				+ ", frequency=" + frequency + ", billId=" + billId + ", setupFee=" + setupFee + ", monthlyFee="
				+ monthlyFee + ", devlopementFee=" + devlopementFee + ", isClientPayingOldCharges="
				+ isClientPayingOldCharges + ", isWaivedOff=" + isWaivedOff + ", billStartDate=" + billStartDate
				+ ", billEndDate=" + billEndDate + ", crmName=" + crmName + ", crmailID=" + crmailID
				+ ", billingComments=" + billingComments + ", goCheckNoteId=" + goCheckNoteId + ", fundcount="
				+ fundcount + ", developmentId=" + developmentId + ", developmentHours=" + developmentHours
				+ ", developmentCost=" + developmentCost + ", isDevelopmentWaivedOff=" + isDevelopmentWaivedOff
				+ ", developmentStartDate=" + developmentStartDate + ", developmentEndDate=" + developmentEndDate
				+ ", count=" + count
				+ ", developmentComments=" + developmentComments + "]";
	}
	
	

	/*
	 * @Override public String toString() { return "TestDto [clientID=" + clientID +
	 * ", riskAggregatorId=" + riskAggregatorId + ", fundID=" + fundID +
	 * ", monthlyFee=" + monthlyFee + ", riskAggregatorName=" + riskAggregatorName +
	 * ", clientName=" + clientName + ", fundName=" + fundName + "]"; }
	 */
	/*
	 * @Override public String toString() { return "TestDto [riskAggregatorId=" +
	 * riskAggregatorId + ", clientName=" + clientName + " , monthlyFee=" +
	 * monthlyFee +"]"; }
	 */

	
	
	
}
