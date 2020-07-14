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
@Table(name="FTPPathDetails")  
public class FTPPathDetails {
	
	@Id   
	@Column(name = "FTPPathId")
	@GeneratedValue	(strategy=GenerationType.IDENTITY)  
	private int FTPPathId;
	
	@Column(name = "FTPPath")
	private String FTPPath;	
	
	@Column(name = "Comments")
	private String Comments;
	
	@Column(name = "Modified_date")
	private Date Modified_date;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ClientID")
	private ClientTable client;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="RiskAggregatorId")
	private RiskAggregator riskAggregator;
	
	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}
	public Date getModified_date() {
		return Modified_date;
	}
	public void setModified_date(Date modified_date) {
		Modified_date = modified_date;
	}
	public int getFTPPathId() {
		return FTPPathId;
	}
	public void setFTPPathId(int fTPPathId) {
		FTPPathId = fTPPathId;
	}
	public String getFTPPath() {
		return FTPPath;
	}
	public void setFTPPath(String fTPPath) {
		FTPPath = fTPPath;
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
