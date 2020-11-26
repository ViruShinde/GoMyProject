package com.globeop.riskfeed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name="MisReport")
public class MisReport {
	
	@Id   
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "riskAggregator")
	private String riskAggregator;
	
	@Column(name = "client")
	private String client;
	
	@Column(name = "fund")
	private String fund;
	
	@Column(name = "cobDate")
	private String cobDate;
	
	@Column(name = "knowledgeDate")
	private String knowledgeDate;
	
	@Column(name = "frequency")
	private String frequency;
	
	@Column(name = "riskFileNav")
	private String riskFileNav;
	
	@Column(name = "goCheckNav")
	private String goCheckNav;
	
	@Column(name = "navDifference")
	private String navDifference;
	
	@Column(name = "fileName")
	private String fileName;
	
	@Column(name = "startTime")
	private String startTime;
	
	@Column(name = "endTime")
	private String endTime;
	
	@Column(name = "processTime")
	private String processTime;
	
	@Column(name = "automationProcess")
	private String automationProcess;
	

	public MisReport() {		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getRiskFileNav() {
		return riskFileNav;
	}

	public void setRiskFileNav(String riskFileNav) {
		this.riskFileNav = riskFileNav;
	}

	public String getGoCheckNav() {
		return goCheckNav;
	}

	public void setGoCheckNav(String goCheckNav) {
		this.goCheckNav = goCheckNav;
	}

	public String getNavDifference() {
		return navDifference;
	}

	public void setNavDifference(String navDifference) {
		this.navDifference = navDifference;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}
	
	

	public String getAutomationProcess() {
		return automationProcess;
	}

	public void setAutomationProcess(String automationProcess) {
		this.automationProcess = automationProcess;
	}

	@Override
	public String toString() {
		return "MisReport [id=" + id + ", riskAggregator=" + riskAggregator + ", client=" + client + ", fund=" + fund
				+ ", cobDate=" + cobDate + ", knowledgeDate=" + knowledgeDate + ", frequency=" + frequency
				+ ", riskFileNav=" + riskFileNav + ", goCheckNav=" + goCheckNav + ", navDifference=" + navDifference
				+ ", fileName=" + fileName + ", startTime=" + startTime + ", endTime=" + endTime + ", processTime="
				+ processTime + ", automationProcess=" + automationProcess + "]";
	}

		
}
