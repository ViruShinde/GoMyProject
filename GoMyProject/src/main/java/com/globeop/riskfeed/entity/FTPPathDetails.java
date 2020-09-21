package com.globeop.riskfeed.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity  
@Table(name="FTPPathDetails")  
public class FTPPathDetails {
	
	@Id   
	@Column(name = "FTPPathId")
	@GeneratedValue	(strategy=GenerationType.IDENTITY)  
	private int ftpPathId;
	
	@Column(name = "FTPPath")
	private String ftpPath;	
	
	@Column(name = "Comments")
	private String comments;
	
	@Column(name = "Modified_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modified_date;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ClientID")
	private ClientTable client;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="RiskAggregatorId")
	private RiskAggregator riskAggregator;

	public FTPPathDetails() {
		
	}
	public FTPPathDetails(int ftpPathId, String ftpPath, String comments, LocalDate modified_date, ClientTable client,
			RiskAggregator riskAggregator) {
		this.ftpPathId = ftpPathId;
		this.ftpPath = ftpPath;
		this.comments = comments;
		this.modified_date = modified_date;
		this.client = client;
		this.riskAggregator = riskAggregator;
	}
	
	

	public FTPPathDetails(String ftpPath, String comments, LocalDate modified_date, ClientTable client,
			RiskAggregator riskAggregator) {		
		this.ftpPath = ftpPath;
		this.comments = comments;
		this.modified_date = modified_date;
		this.client = client;
		this.riskAggregator = riskAggregator;
	}



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

	public ClientTable getClient() {
		return client;
	}

	public void setClient(ClientTable client) {
		this.client = client;
	}

	public RiskAggregator getRiskAggregator() {
		return riskAggregator;
	}

	public void setRiskAggregator(RiskAggregator riskAggregator) {
		this.riskAggregator = riskAggregator;
	}
	
	
	

}
