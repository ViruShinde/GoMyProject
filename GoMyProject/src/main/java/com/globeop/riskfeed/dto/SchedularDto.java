package com.globeop.riskfeed.dto;

import java.time.LocalDate;

public class SchedularDto {


	private static final long serialVersionUID = 1L;
	
	private int scheduledDetailsId;
	
	private String min;
	
	private String hour;
	
	private String dayOfMonth;
	
	private String month;
	
	private String dayOfWeek;
		
	private int riskAggregatorId;
		
	private String riskAggregatorName;
	
	private int clientId;
	
	private String clientName;
	
	private int fundId;
	
	private String fundName;
	
	private String frequency;
	
	private String businessDay;
	
	private String fundGroup;
	
	private String consolidated;
	
	private String isActive;	
	
	private String comments;
	
	private LocalDate modified_date;
	
	
	public SchedularDto() {
		
	}
	
	public SchedularDto(int scheduledDetailsId, String min, String hour, String dayOfMonth, String month,
			String dayOfWeek, int riskAggregatorId, String riskAggregatorName, int clientId, String clientName, int fundId,
			String fundName, String frequency, String businessDay, String fundGroup, String consolidated, String isActive,
			String comments, LocalDate modified_date) {		
		this.scheduledDetailsId = scheduledDetailsId;
		this.min = min;
		this.hour = hour;
		this.dayOfMonth = dayOfMonth;
		this.month = month;
		this.dayOfWeek = dayOfWeek;
		this.riskAggregatorId = riskAggregatorId;
		this.riskAggregatorName = riskAggregatorName;
		this.clientId = clientId;
		this.clientName = clientName;
		this.fundId = fundId;
		this.fundName = fundName;
		this.frequency = frequency;
		this.businessDay = businessDay;
		this.fundGroup = fundGroup;
		this.consolidated = consolidated;
		this.isActive = isActive;
		this.comments = comments;
		this.modified_date = modified_date;
	}

	public int getScheduledDetailsId() {
		return scheduledDetailsId;
	}

	public void setScheduledDetailsId(int scheduledDetailsId) {
		this.scheduledDetailsId = scheduledDetailsId;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getRiskAggregatorId() {
		return riskAggregatorId;
	}

	public void setRiskAggregatorId(int riskAggregatorId) {
		this.riskAggregatorId = riskAggregatorId;
	}

	public String getRiskAggregatorName() {
		return riskAggregatorName;
	}

	public void setRiskAggregatorName(String riskAggregatorName) {
		this.riskAggregatorName = riskAggregatorName;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getFundId() {
		return fundId;
	}

	public void setFundId(int fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getBusinessDay() {
		return businessDay;
	}

	public void setBusinessDay(String businessDay) {
		this.businessDay = businessDay;
	}

	public String getFundGroup() {
		return fundGroup;
	}

	public void setFundGroup(String fundGroup) {
		this.fundGroup = fundGroup;
	}

	public String getConsolidated() {
		return consolidated;
	}

	public void setConsolidated(String consolidated) {
		this.consolidated = consolidated;
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

	public LocalDate getModified_date() {
		return modified_date;
	}

	public void setModified_date(LocalDate modified_date) {
		this.modified_date = modified_date;
	}

	@Override
	public String toString() {
		return "SchedularDto [scheduledDetailsId=" + scheduledDetailsId + ", min=" + min + ", hour=" + hour
				+ ", dayOfMonth=" + dayOfMonth + ", month=" + month + ", dayOfWeek=" + dayOfWeek + ", riskAggregatorId="
				+ riskAggregatorId + ", riskAggregatorName=" + riskAggregatorName + ", clientId=" + clientId
				+ ", clientName=" + clientName + ", fundId=" + fundId + ", fundName=" + fundName + ", frequency="
				+ frequency + ", businessDay=" + businessDay + ", fundGroup=" + fundGroup + ", consolidated="
				+ consolidated + ", isActive=" + isActive + ", comments=" + comments + ", modified_date="
				+ modified_date + "]";
	}
	
	
	
}
