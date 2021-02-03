package com.globeop.riskfeed.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity  
@Table(name="FtpServerDetails")  
public class FtpServerDetails {

	
	@Id   
	@Column(name = "FTPDetailID")
	@GeneratedValue	(strategy=GenerationType.IDENTITY)  
	private int ftpDetailID;
	
	
	@Column(name = "FTPName")
	private String ftpName;
	
	@Column(name = "FTPUserName")
	private String ftpUserName;
	
	@Column(name = "FTPPassword")
	private String ftpPassword;
	
	@Column(name = "FTPType")
	private String ftpType;
	
	@Column(name = "FTPPath")
	private String ftpPath;	
	
	@Column(name = "Comments")
	private String comments;	
	
	@Column(name = "Modified_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modified_date;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="ClientID")
	private ClientTable client;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="RiskAggregatorId")
	private RiskAggregator riskAggregator;

	public FtpServerDetails() {
		
	}
	
	public FtpServerDetails(int ftpDetailID, String ftpName, String ftpUserName, String ftpPassword, String ftpType,String comments,
			LocalDate modified_date) {		
		this.ftpDetailID = ftpDetailID;
		this.ftpName = ftpName;
		this.ftpUserName = ftpUserName;
		this.ftpPassword = ftpPassword;
		this.ftpType = ftpType;
		this.comments=comments;
		this.modified_date = modified_date;
		
	}
	
	public FtpServerDetails(int ftpDetailID, String ftpName, String ftpUserName, String ftpPassword, String ftpType,
			LocalDate modified_date, RiskAggregator riskAggregator) {		
		this.ftpDetailID = ftpDetailID;
		this.ftpName = ftpName;
		this.ftpUserName = ftpUserName;
		this.ftpPassword = ftpPassword;
		this.ftpType = ftpType;
		this.modified_date = modified_date;
		this.riskAggregator = riskAggregator;
	}
	

	public FtpServerDetails(int ftpDetailID, String ftpName, String ftpUserName, String ftpPassword, String ftpType,String ftpPath,String comments,ClientTable client,
			 RiskAggregator riskAggregator,LocalDate modified_date) {		
		this.ftpDetailID=ftpDetailID;
		this.ftpName = ftpName;
		this.ftpUserName = ftpUserName;
		this.ftpPassword = ftpPassword;
		this.ftpType = ftpType;
		this.ftpPath = ftpPath;
		this.comments= comments;
		this.client= client;
		this.riskAggregator = riskAggregator;
		this.modified_date = modified_date;
	}
	
	public FtpServerDetails(String ftpName, String ftpUserName, String ftpPassword, String ftpType,String ftpPath,String comments,ClientTable client,
			 RiskAggregator riskAggregator,LocalDate modified_date) {		
		this.ftpName = ftpName;
		this.ftpUserName = ftpUserName;
		this.ftpPassword = ftpPassword;
		this.ftpType = ftpType;
		this.ftpPath = ftpPath;
		this.comments= comments;
		this.client= client;
		this.riskAggregator = riskAggregator;
		this.modified_date = modified_date;
	}
	
	public FtpServerDetails(String ftpName, String ftpUserName, String ftpPassword, String ftpType,String ftpPath,String comments,
			 RiskAggregator riskAggregator,LocalDate modified_date) {		
		this.ftpName = ftpName;
		this.ftpUserName = ftpUserName;
		this.ftpPassword = ftpPassword;
		this.ftpType = ftpType;
		this.ftpPath = ftpPath;
		this.comments= comments;		
		this.riskAggregator = riskAggregator;
		this.modified_date = modified_date;
	}
	
	
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFtpPath() {
		return ftpPath;
	}
	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}	
	public ClientTable getClient() {
		return client;
	}
	public void setClient(ClientTable client) {
		this.client = client;
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

	public LocalDate getModified_date() {
		return modified_date;
	}

	public void setModified_date(LocalDate modified_date) {
		this.modified_date = modified_date;
	}

	public RiskAggregator getRiskAggregator() {
		return riskAggregator;
	}

	public void setRiskAggregator(RiskAggregator riskAggregator) {
		this.riskAggregator = riskAggregator;
	}

	@Override
	public String toString() {
		return "FtpServerDetails [ftpDetailID=" + ftpDetailID + ", ftpName=" + ftpName + ", ftpUserName=" + ftpUserName
				+ ", ftpPassword=" + ftpPassword + ", ftpType=" + ftpType + ", ftpPath=" + ftpPath + ", comments="
				+ comments + ", modified_date=" + modified_date + "]";
	}




		
}
