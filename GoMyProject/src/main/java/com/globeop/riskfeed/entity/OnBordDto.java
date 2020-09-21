package com.globeop.riskfeed.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.globeop.riskfeed.dto.OnBoardFunds;
import com.globeop.riskfeed.enums.IsWaivedOff;


public class OnBordDto implements Serializable{
	
	private String onBoardForm="";
	
	//@NotNull(message = "Please select Client")
	private String clientName;
	
	private int clientId;
	
	//@NotNull(message = "Please select RiskAggregator")
	private int riskAggregatorId;
	
	private String riskAggregatorName;
	
	//@NotEmpty(message = "Please select funds")
	private String fundName;

	private String fundIds;
	
	//@NotEmpty(message = "Please select setup Date")
	private String setUpDate;
	
	private String endDate="";
	
	//@NotEmpty(message = "Please select atleast one")	
	private String automationProcess;
	
	//@NotEmpty(message = "Please select atleast one")
	private String isActive;
	
	//@NotEmpty(message = "Please provide comments")
	private String comments;
	
	//@NotEmpty(message = "Please select frequency")
	private String frequency;

	private List<OnBoardFunds> onBoardFundsList = new ArrayList<OnBoardFunds>();
	
	// for Development form
	
	private int developmentHours;
	
	private int developmentCost; 
	
	private String isWaivedOff;
	
	private String startDate;
	
	private String developmentComments;
	
	private byte[] ApprovalMail;
	
	
	// for Bill 
	
	private int setupFee;
	
	private int monthlyFee;
	
	private int devlopementFee;
	
	private String isClientPayingOldCharges;
	
	// use common isWaivedOff field.
	
	private byte[] waiverMail;
	
	//for dillStartDate and dillEndDate use startDate and endDate field 
	
	// for ClientID and riskAggregatorId use common clientId,riskAggregatorId field.
		
	private String crmName;
	
	private String crmailID;
	
	private byte[] clientApprovalMail;
	
	//for billingComments use common field comments;
	
	private int goCheckNoteId;
	
	private byte[] terminationMail;
	
	private int fundcount;
	
	// For FTP details
	private int ftpPathId;
	
	private String ftpPath;	
	
	private int ftpDetailID;
	
	private String ftpName;
	
	private String ftpUserName;
	
	private String ftpPassword;
	
	private String ftpType;
	
	
	
	public int getFtpPathId() {
		return ftpPathId;
	}


	public void setFtpPathId(int ftpPathId) {
		this.ftpPathId = ftpPathId;
	}


	public String getFtpPath() {
		return ftpPath;
	}


	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}


	public int getFtpDetailID() {
		return ftpDetailID;
	}


	public void setFtpDetailID(int ftpDetailID) {
		this.ftpDetailID = ftpDetailID;
	}


	public String getFtpName() {
		return ftpName;
	}


	public void setFtpName(String ftpName) {
		this.ftpName = ftpName;
	}


	public String getFtpUserName() {
		return ftpUserName;
	}


	public void setFtpUserName(String ftpUserName) {
		this.ftpUserName = ftpUserName;
	}


	public String getFtpPassword() {
		return ftpPassword;
	}


	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}


	public String getFtpType() {
		return ftpType;
	}


	public void setFtpType(String ftpType) {
		this.ftpType = ftpType;
	}


	public String getRiskAggregatorName() {
		return riskAggregatorName;
	}


	public void setRiskAggregatorName(String riskAggregatorName) {
		this.riskAggregatorName = riskAggregatorName;
	}


	public int getSetupFee() {
		return setupFee;
	}


	public void setSetupFee(int setupFee) {
		this.setupFee = setupFee;
	}


	public int getMonthlyFee() {
		return monthlyFee;
	}


	public void setMonthlyFee(int monthlyFee) {
		this.monthlyFee = monthlyFee;
	}


	public int getDevlopementFee() {
		return devlopementFee;
	}


	public void setDevlopementFee(int devlopementFee) {
		this.devlopementFee = devlopementFee;
	}


	public String getIsClientPayingOldCharges() {
		return isClientPayingOldCharges;
	}


	public void setIsClientPayingOldCharges(String isClientPayingOldCharges) {
		this.isClientPayingOldCharges = isClientPayingOldCharges;
	}


	public byte[] getWaiverMail() {
		return waiverMail;
	}


	public void setWaiverMail(byte[] waiverMail) {
		this.waiverMail = waiverMail;
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


	public byte[] getClientApprovalMail() {
		return clientApprovalMail;
	}


	public void setClientApprovalMail(byte[] clientApprovalMail) {
		this.clientApprovalMail = clientApprovalMail;
	}


	public int getGoCheckNoteId() {
		return goCheckNoteId;
	}


	public void setGoCheckNoteId(int goCheckNoteId) {
		this.goCheckNoteId = goCheckNoteId;
	}


	public byte[] getTerminationMail() {
		return terminationMail;
	}


	public void setTerminationMail(byte[] terminationMail) {
		this.terminationMail = terminationMail;
	}


	public int getFundcount() {
		return fundcount;
	}


	public void setFundcount(int fundcount) {
		this.fundcount = fundcount;
	}


	public String getOnBoardForm() {
		return onBoardForm;
	}


	public void setOnBoardForm(String onBoardForm) {
		this.onBoardForm = onBoardForm;
	}


	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public List<OnBoardFunds> getOnBoardFundsList() {
		return onBoardFundsList;
	}


	public void setOnBoardFundsList(List<OnBoardFunds> onBoardFundsList) {
		this.onBoardFundsList = onBoardFundsList;
	}


	public int getClientId() {
		return clientId;
	}


	public int getRiskAggregatorId() {
		return riskAggregatorId;
	}

	public void setRiskAggregatorId(int riskAggregatorId) {
		this.riskAggregatorId = riskAggregatorId;
	}
	
	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFundIds() {
		return fundIds;
	}

	public void setFundIds(String fundIds) {
		this.fundIds = fundIds;
	}

	public String getSetUpDate() {
		return setUpDate;
	}

	public void setSetUpDate(String setUpDate) {
		this.setUpDate = setUpDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAutomationProcess() {
		return automationProcess;
	}

	public void setAutomationProcess(String automationProcess) {
		this.automationProcess = automationProcess;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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

	public void setClientId(int clientId) {
		this.clientId = clientId;
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



	public String getIsWaivedOff() {
		return isWaivedOff;
	}


	public void setIsWaivedOff(String isWaivedOff) {
		this.isWaivedOff = isWaivedOff;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getDevelopmentComments() {
		return developmentComments;
	}


	public void setDevelopmentComments(String developmentComments) {
		this.developmentComments = developmentComments;
	}


	public byte[] getApprovalMail() {
		return ApprovalMail;
	}


	public void setApprovalMail(byte[] approvalMail) {
		ApprovalMail = approvalMail;
	}


	@Override
	public String toString() {
		return "OnBordDto [onBoardForm=" + onBoardForm + ", clientName=" + clientName + ", clientId=" + clientId
				+ ", riskAggregatorId=" + riskAggregatorId + ", riskAggregatorName=" + riskAggregatorName
				+ ", fundName=" + fundName + ", fundIds=" + fundIds + ", setUpDate=" + setUpDate + ", endDate="
				+ endDate + ", automationProcess=" + automationProcess + ", isActive=" + isActive + ", comments="
				+ comments + ", frequency=" + frequency + ", onBoardFundsList=" + onBoardFundsList
				+ ", developmentHours=" + developmentHours + ", developmentCost=" + developmentCost + ", isWaivedOff="
				+ isWaivedOff + ", startDate=" + startDate + ", developmentComments=" + developmentComments
				+ ", ApprovalMail=" + Arrays.toString(ApprovalMail) + ", setupFee=" + setupFee + ", monthlyFee="
				+ monthlyFee + ", devlopementFee=" + devlopementFee + ", isClientPayingOldCharges="
				+ isClientPayingOldCharges + ", waiverMail=" + Arrays.toString(waiverMail) + ", crmName=" + crmName
				+ ", crmailID=" + crmailID + ", clientApprovalMail=" + Arrays.toString(clientApprovalMail)
				+ ", goCheckNoteId=" + goCheckNoteId + ", terminationMail=" + Arrays.toString(terminationMail)
				+ ", fundcount=" + fundcount + ", ftpPathId=" + ftpPathId + ", ftpPath=" + ftpPath + ", ftpDetailID="
				+ ftpDetailID + ", ftpName=" + ftpName + ", ftpUserName=" + ftpUserName + ", ftpPassword=" + ftpPassword
				+ ", ftpType=" + ftpType + "]";
	}


	/*	@Override
	public String toString() {
		return "OnBordDto [onBoardForm=" + onBoardForm + ", clientName=" + clientName + ", clientId=" + clientId
				+ ", riskAggregatorId=" + riskAggregatorId + ", fundName=" + fundName + ", fundIds=" + fundIds
				+ ", setUpDate=" + setUpDate + ", endDate=" + endDate + ", automationProcess=" + automationProcess
				+ ", isActive=" + isActive + ", comments=" + comments + ", frequency=" + frequency
				+ ", onBoardFundsList=" + onBoardFundsList + ", developmentHours=" + developmentHours
				+ ", developmentCost=" + developmentCost + ", isWaivedOff=" + isWaivedOff + ", startDate=" + startDate
				+ ", developmentComments=" + developmentComments + ", setupFee=" + setupFee + ", monthlyFee="
				+ monthlyFee + ", devlopementFee=" + devlopementFee + ", isClientPayingOldCharges="
				+ isClientPayingOldCharges + ", crmName=" + crmName + ", crmailID=" + crmailID + ", goCheckNoteId="
				+ goCheckNoteId + ", fundcount=" + fundcount + "]";
	}*/
	
	
	




	


	
	
	
}
