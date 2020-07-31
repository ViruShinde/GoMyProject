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
	
	@Column(name = "Modified_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modified_date;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="RiskAggregatorId")
	private RiskAggregator riskAggregator;

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

		
}
