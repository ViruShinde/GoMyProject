package com.globeop.riskfeed.dto;

import java.io.Serializable;

public class RiskFileDto implements Serializable{
	
	private String onBoardForm="";
	
	private String frequency;

	private String riskAggregatorId;
	
	private String riskAggregator;
		
	private String client;
		
	private String fund;
	
	private String cobDate;
	
	private String knowledgeDate="";
	
	private String filePath="";
	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRiskAggregatorId() {
		return riskAggregatorId;
	}

	public void setRiskAggregatorId(String riskAggregatorId) {
		this.riskAggregatorId = riskAggregatorId;
	}

	public String getOnBoardForm() {
		return onBoardForm;
	}

	public void setOnBoardForm(String onBoardForm) {
		this.onBoardForm = onBoardForm;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getRiskAggregator() {
		return riskAggregator;
	}

	public void setRiskAggregator(String riskAggregator) {
		this.riskAggregator = riskAggregator;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getFund() {
		return fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}


	public String getCobDate() {
		return cobDate;
	}

	public void setCobDate(String cobDate) {
		this.cobDate = cobDate;
	}

	public String getKnowledgeDate() {
		return knowledgeDate;
	}

	public void setKnowledgeDate(String knowledgeDate) {
		this.knowledgeDate = knowledgeDate;
	}

	@Override
	public String toString() {
		return "RiskFileDto [onBoardForm=" + onBoardForm + ", frequency=" + frequency + ", riskAggregatorId="
				+ riskAggregatorId + ", riskAggregator=" + riskAggregator + ", client=" + client + ", fund=" + fund
				+ ", cobDate=" + cobDate + ", knowledgeDate=" + knowledgeDate + ", filePath=" + filePath + "]";
	}

	
	
	
}
