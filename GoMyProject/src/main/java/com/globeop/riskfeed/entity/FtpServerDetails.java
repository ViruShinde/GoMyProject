package com.globeop.riskfeed.entity;

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

@Entity  
@Table(name="FtpServerDetails")  
public class FtpServerDetails {

	
	@Id   
	@Column(name = "FTPDetailID")
	@GeneratedValue	(strategy=GenerationType.IDENTITY)  
	private int FTPDetailID;
	
	
	@Column(name = "FTPName")
	private String FTPName;
	
	@Column(name = "FTPUserName")
	private String FTPUserName;
	
	@Column(name = "FTPPassword")
	private String FTPPassword;
	
	@Column(name = "FTPType")
	private String FTPType;
	
	@Column(name = "Modified_date")
	private Date Modified_date;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="RiskAggregatorId")
	private RiskAggregator riskAggregator;

	public int getFTPDetailID() {
		return FTPDetailID;
	}

	public void setFTPDetailID(int fTPDetailID) {
		FTPDetailID = fTPDetailID;
	}

	public String getFTPName() {
		return FTPName;
	}

	public void setFTPName(String fTPName) {
		FTPName = fTPName;
	}

	public String getFTPUserName() {
		return FTPUserName;
	}

	public void setFTPUserName(String fTPUserName) {
		FTPUserName = fTPUserName;
	}

	public String getFTPPassword() {
		return FTPPassword;
	}

	public void setFTPPassword(String fTPPassword) {
		FTPPassword = fTPPassword;
	}

	public String getFTPType() {
		return FTPType;
	}

	public void setFTPType(String fTPType) {
		FTPType = fTPType;
	}

	public Date getModified_date() {
		return Modified_date;
	}

	public void setModified_date(Date modified_date) {
		Modified_date = modified_date;
	}

	public RiskAggregator getRiskAggregator() {
		return riskAggregator;
	}

	public void setRiskAggregator(RiskAggregator riskAggregator) {
		this.riskAggregator = riskAggregator;
	}
	
	
	
	
}
