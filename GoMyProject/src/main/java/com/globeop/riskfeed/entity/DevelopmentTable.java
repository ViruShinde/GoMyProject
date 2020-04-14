package com.globeop.riskfeed.entity;


import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globeop.riskfeed.enums.IsWaivedOff;

@Entity  
@Table(name="DevelopmentTable") 
public class DevelopmentTable {


	@Id   
	@Column(name = "DevelopmentId")
	@GeneratedValue	(strategy=GenerationType.IDENTITY)  
	private int developmentId;
	
	@Column(name = "DevelopmentHours")
	private int developmentHours;
	
	@Column(name = "DevelopmentCost")
	private int developmentCost; 
	
	@Enumerated(EnumType.STRING)
	@Column(name = "IsWaivedOff")
	private IsWaivedOff isWaivedOff;
	
	@Column(name = "StartDate")
	private Date startDate;
	
	@Column(name = "EndDate")
	private Date endDate;
	
	@Column(name = "DevelopmentComments")
	private String developmentComments;
	
	@Lob
	@Column(name = "ApprovalMail")
	private byte[] approvalMail;
	
	@Column(name = "FileName")
	private String fileName;

	@Column(name = "FileType")
    private String fileType;

	@JsonBackReference
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ClientID", nullable = false)    
	private ClientTable client;
	
	@JsonBackReference
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="RiskAggregatorId", nullable = false)
	private RiskAggregator riskAggregator;
	
	@Column(name = "Modified_date")
	private Date modified_date;
	
	@Transient
	private String downloadUrl="";		
    
	public DevelopmentTable(){
		
	}
	
    public DevelopmentTable(String fileName, String fileType, byte[] approvalMail) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.approvalMail = approvalMail;
    }
    

    
	public int getDevelopmentId() {
		return developmentId;
	}

	public void setDevelopmentId(int developmentId) {
		this.developmentId = developmentId;
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

	public IsWaivedOff getIsWaivedOff() {
		return isWaivedOff;
	}

	public void setIsWaivedOff(IsWaivedOff isWaivedOff) {
		this.isWaivedOff = isWaivedOff;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDevelopmentComments() {
		return developmentComments;
	}

	public void setDevelopmentComments(String developmentComments) {
		this.developmentComments = developmentComments;
	}

	public byte[] getApprovalMail() {
		return approvalMail;
	}

	public void setApprovalMail(byte[] theApprovalMail) {
		approvalMail = theApprovalMail;
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

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	
	
	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	@Override
	public String toString() {
		return "DevelopmentTable [developmentId=" + developmentId + ", developmentHours=" + developmentHours
				+ ", developmentCost=" + developmentCost + ", isWaivedOff=" + isWaivedOff + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", developmentComments=" + developmentComments + ", client=" + client.getClientShortName() + ", riskAggregator=" + riskAggregator.getRiskAggregatorName()
				+ ", modified_date=" + modified_date + ", fileName=" + fileName + ", fileType=" + fileType + "]";
	}

	
	}
