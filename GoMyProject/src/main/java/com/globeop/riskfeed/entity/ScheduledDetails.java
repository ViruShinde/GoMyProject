package com.globeop.riskfeed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ScheduledDetails2")
public class ScheduledDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id   
	//@Column(name = "scheduledDetailsId")
	@GeneratedValue	(strategy=GenerationType.IDENTITY)  
	private int scheduledDetailsId;
	
	@Column(name = "min")
	private String min;
	
	@Column(name = "hour")
	private String hour;
	
	@Column(name = "dayOfMonth")
	private String dayOfMonth;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "dayOfWeek")
	private String dayOfWeek;
		
	@Column(name = "riskAggregatorId")
	private int riskAggregatorId;
	
	@Column(name = "clientId")	   
	private int clientId;
	
	@Column(name = "fundId")	
	private int fundId;
	
	@Column(name = "frequency")
	private String frequency="";
	
	@Column(name = "businessDay")
	private String businessDay;
	
	@Column(name = "fundGroup")
	private String fundGroup;
	
	@Column(name = "consolidated")
	private String consolidated;
	
	@Column(name = "isActive")
	private String isActive;	
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "modified_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modified_date;

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

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getFundId() {
		return fundId;
	}

	public void setFundId(int fundId) {
		this.fundId = fundId;
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
		return "ScheduledDetails [scheduledDetailsId=" + scheduledDetailsId + ", min=" + min + ", hour=" + hour
				+ ", dayOfMonth=" + dayOfMonth + ", month=" + month + ", dayOfWeek=" + dayOfWeek + ", riskAggregatorId="
				+ riskAggregatorId + ", clientId=" + clientId + ", fundId=" + fundId + ", frequency=" + frequency
				+ ", businessDay=" + businessDay + ", fundGroup=" + fundGroup + ", consolidated=" + consolidated
				+ ", isActive=" + isActive + ", comments=" + comments + ", modified_date=" + modified_date + "]";
	}

	
	

	
	
	
	
	
}
