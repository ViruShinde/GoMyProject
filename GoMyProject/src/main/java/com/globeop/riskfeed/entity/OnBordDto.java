package com.globeop.riskfeed.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.globeop.riskfeed.dto.OnBoardFunds;
import com.globeop.riskfeed.enums.IsWaivedOff;


public class OnBordDto {
	
	private String onBoardForm="";
	
	//@NotNull(message = "Please select Client")
	private String clientName;
	
	private int clientId;
	
	//@NotNull(message = "Please select RiskAggregator")
	private int riskAggregatorId;
	
	//@NotEmpty(message = "Please select funds")
	private String fundName;

	private String fundIds;
	
	//@NotEmpty(message = "Please select setup Date")
	private String setUpDate;
	
	private String endDate;
	
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
				+ ", riskAggregatorId=" + riskAggregatorId + ", fundName=" + fundName + ", fundIds=" + fundIds
				+ ", setUpDate=" + setUpDate + ", endDate=" + endDate + ", automationProcess=" + automationProcess
				+ ", isActive=" + isActive + ", comments=" + comments + ", frequency=" + frequency
				+ ", onBoardFundsList=" + onBoardFundsList + "]";
	}





	


	
	
	
}
