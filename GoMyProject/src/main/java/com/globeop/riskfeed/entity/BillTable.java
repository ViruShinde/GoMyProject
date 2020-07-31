package com.globeop.riskfeed.entity;

import java.io.Serializable;
import java.time.LocalDate;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globeop.riskfeed.enums.IsClientPayingOldCharges;
import com.globeop.riskfeed.enums.IsWaivedOff;


@Entity  
@Table(name="BillTable")  
public class BillTable implements Serializable{
	
	@Id   
	@Column(name = "BillId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int billId;
	
	@Column(name = "SetupFee")
	private int setupFee;
	
	@Column(name = "MonthlyFee")
	private int monthlyFee;
	
	@Column(name = "DevlopementFee")
	private int devlopementFee;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "IsClientPayingOldCharges")
	private IsClientPayingOldCharges isClientPayingOldCharges;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "IsWaivedOff")
	private IsWaivedOff isWaivedOff;
	
	@Lob
	@Column(name = "WaiverMail")
	private byte[] waiverMail;
	
	@Column(name = "WaiverFileName")
	private String waiverFileName;
	
	@Column(name = "BillStartDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate billStartDate;
	
	@Column(name = "BillEndDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate billEndDate;

	@JsonBackReference
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ClientID", nullable = false)    
	private ClientTable client;
	
	@JsonBackReference
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
	@JoinColumn(name="RiskAggregatorId", nullable = false)
	private RiskAggregator riskAggregator;
	
	@Column(name = "CRMName")
	private String crmName;
	
	@Column(name = "CRMEmailId")
	private String crmailID;
	
	@Lob
	@Column(name = "ClientApprovalMail")
	private byte[] clientApprovalMail;
	
	@Column(name = "ClientApprovalMailName")
	private String clientApprovalMailName;
	
	@Column(name = "BillingComments")
	private String 	billingComments;
	
	@Column(name = "GoCheckNoteId")
	private int goCheckNoteId;
	
	@Lob
	@Column(name = "TerminationMail")
	private byte[] terminationMail;
	
	@Column(name = "TerminationMailName")
	private String terminationMailName;
	
	@Column(name = "Fundcount")
	private int fundcount;
	
	@Column(name = "Modified_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modified_date;
		
	@Transient
	private String waiverMailUrl;
	
	@Transient
	private String clientApprovalMailUrl;
	
	@Transient
	private String terminationMailUrl;
	
	public BillTable() {}

	public BillTable(byte[] waiverMail, String waiverFileName, byte[] clientApprovalMail, String clientApprovalMailName,
			byte[] terminationMail, String terminationMailName) {
		this.waiverMail = waiverMail;
		this.waiverFileName = waiverFileName;
		this.clientApprovalMail = clientApprovalMail;
		this.clientApprovalMailName = clientApprovalMailName;
		this.terminationMail = terminationMail;
		this.terminationMailName = terminationMailName;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getSetupFee() {
		return setupFee;
	}

	public void setSetupFee(int setupFee) {
		this.setupFee = setupFee;
	}

	public int getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(int monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public int getDevlopementFee() {
		return devlopementFee;
	}

	public void setDevlopementFee(int devlopementFee) {
		this.devlopementFee = devlopementFee;
	}

	public IsClientPayingOldCharges getIsClientPayingOldCharges() {
		return isClientPayingOldCharges;
	}

	public void setIsClientPayingOldCharges(IsClientPayingOldCharges isClientPayingOldCharges) {
		this.isClientPayingOldCharges = isClientPayingOldCharges;
	}

	public IsWaivedOff getIsWaivedOff() {
		return isWaivedOff;
	}

	public void setIsWaivedOff(IsWaivedOff isWaivedOff) {
		this.isWaivedOff = isWaivedOff;
	}

	public byte[] getWaiverMail() {
		return waiverMail;
	}

	public void setWaiverMail(byte[] waiverMail) {
		this.waiverMail = waiverMail;
	}

	public LocalDate getBillStartDate() {
		return billStartDate;
	}

	public void setBillStartDate(LocalDate billStartDate) {
		this.billStartDate = billStartDate;
	}

	public LocalDate getBillEndDate() {
		return billEndDate;
	}

	public void setBillEndDate(LocalDate billEndDate) {
		this.billEndDate = billEndDate;
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

	public String getCrmName() {
		return crmName;
	}

	public void setCrmName(String crmName) {
		this.crmName = crmName;
	}

	public String getCrmailID() {
		return crmailID;
	}

	public void setCrmailID(String crmailID) {
		this.crmailID = crmailID;
	}

	public byte[] getClientApprovalMail() {
		return clientApprovalMail;
	}

	public void setClientApprovalMail(byte[] clientApprovalMail) {
		this.clientApprovalMail = clientApprovalMail;
	}

	public String getBillingComments() {
		return billingComments;
	}

	public void setBillingComments(String billingComments) {
		this.billingComments = billingComments;
	}

	public int getGoCheckNoteId() {
		return goCheckNoteId;
	}

	public void setGoCheckNoteId(int goCheckNoteId) {
		this.goCheckNoteId = goCheckNoteId;
	}

	public byte[] getTerminationMail() {
		return terminationMail;
	}

	public void setTerminationMail(byte[] terminationMail) {
		this.terminationMail = terminationMail;
	}

	public int getFundcount() {
		return fundcount;
	}

	public void setFundcount(int fundcount) {
		this.fundcount = fundcount;
	}

	public LocalDate getModified_date() {
		return modified_date;
	}

	public void setModified_date(LocalDate modified_date) {
		this.modified_date = modified_date;
	}
	
		

	public String getWaiverFileName() {
		return waiverFileName;
	}

	public void setWaiverFileName(String waiverFileName) {
		this.waiverFileName = waiverFileName;
	}

	public String getClientApprovalMailName() {
		return clientApprovalMailName;
	}

	public void setClientApprovalMailName(String clientApprovalMailName) {
		this.clientApprovalMailName = clientApprovalMailName;
	}

	public String getTerminationMailName() {
		return terminationMailName;
	}

	public void setTerminationMailName(String terminationMailName) {
		this.terminationMailName = terminationMailName;
	}

	public String getWaiverMailUrl() {
		return waiverMailUrl;
	}

	public void setWaiverMailUrl(String waiverMailUrl) {
		this.waiverMailUrl = waiverMailUrl;
	}

	public String getClientApprovalMailUrl() {
		return clientApprovalMailUrl;
	}

	public void setClientApprovalMailUrl(String clientApprovalMailUrl) {
		this.clientApprovalMailUrl = clientApprovalMailUrl;
	}

	public String getTerminationMailUrl() {
		return terminationMailUrl;
	}

	public void setTerminationMailUrl(String terminationMailUrl) {
		this.terminationMailUrl = terminationMailUrl;
	}

	@Override
	public String toString() {
		return "BillTable [billId=" + billId + ", setupFee=" + setupFee + ", monthlyFee=" + monthlyFee
				+ ", devlopementFee=" + devlopementFee + ", isClientPayingOldCharges=" + isClientPayingOldCharges
				+ ", isWaivedOff=" + isWaivedOff + ", waiverFileName=" + waiverFileName + ", billStartDate="
				+ billStartDate + ", billEndDate=" + billEndDate + ", client=" + client.getClientShortName() + ", riskAggregator="
				+ riskAggregator.getRiskAggregatorName() + ", crmName=" + crmName + ", crmailID=" + crmailID + ", clientApprovalMailName="
				+ clientApprovalMailName + ", billingComments=" + billingComments + ", goCheckNoteId=" + goCheckNoteId
				+ ", terminationMailName=" + terminationMailName + ", fundcount=" + fundcount + ", modified_date="
				+ modified_date + ", waiverMailUrl=" + waiverMailUrl + ", clientApprovalMailUrl="
				+ clientApprovalMailUrl + ", terminationMailUrl=" + terminationMailUrl + "]";
	}

	

	
	
	
	
	
	
}
