package com.globeop.riskfeed.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//@JsonIgnoreProperties({"clientOnboardSet", "modified_date"})
//@JsonIgnoreProperties({"clientOnboardSet"})
@Entity
@Table(name="RiskAggregator")
public class RiskAggregator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RiskAggregatorId")
	private int riskAggregatorId;
	
	@Column(name="RiskAggregatorName")
	private String riskAggregatorName;
	
	@Column(name="RiskAggregatorContact")
	private String riskAggregatorContact;
	
	@Column(name="Modified_date")
	private LocalDate modified_date;
		
	
	@JsonManagedReference	
	@OneToMany(targetEntity = ClientOnboardTable.class, cascade = CascadeType.ALL, mappedBy="riskAggregator") 	
    private Set<ClientOnboardTable> clientOnboardSet;
	
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(targetEntity = BillTable.class, cascade = CascadeType.ALL, mappedBy="riskAggregator") 	
    private Set<BillTable> billSet;

	@JsonManagedReference
	@JsonIgnore
	@OneToMany(targetEntity = FtpServerDetails.class, cascade = CascadeType.ALL, mappedBy="riskAggregator") 	
    private Set<FtpServerDetails> ftpServerDetailsSet;
	public RiskAggregator() {		
	}
	
	
	public Set<FtpServerDetails> getFtpServerDetailsSet() {
		return ftpServerDetailsSet;
	}


	public void setFtpServerDetailsSet(Set<FtpServerDetails> ftpServerDetailsSet) {
		this.ftpServerDetailsSet = ftpServerDetailsSet;
	}


	public int getRiskAggregatorId() {
		return riskAggregatorId;
	}

	public void setRiskAggregatorId(int riskAggregatorId) {
		this.riskAggregatorId = riskAggregatorId;
	}

	public void setModified_date(LocalDate modified_date) {
		this.modified_date = modified_date;
	}



	public String getRiskAggregatorName() {
		return riskAggregatorName;
	}

	public void setRiskAggregatorName(String riskAggregatorName) {
		this.riskAggregatorName = riskAggregatorName;
	}

	public String getRiskAggregatorContact() {
		return riskAggregatorContact;
	}

	public void setRiskAggregatorContact(String riskAggregatorContact) {
		this.riskAggregatorContact = riskAggregatorContact;
	}

	
	public LocalDate getModified_date() {
		return modified_date;
	}

	
	public Set<ClientOnboardTable> getClientOnboardSet() {
		return clientOnboardSet;
	}

	public void setClientOnboardSet(Set<ClientOnboardTable> clientOnboardSet) {
		this.clientOnboardSet = clientOnboardSet;
	}
	
	public void addClientOnboard(ClientOnboardTable theClientOnboardTable) {
		if(clientOnboardSet==null) {
			clientOnboardSet = new HashSet<ClientOnboardTable>();
		}
		theClientOnboardTable.setRiskAggregator(this);
		this.clientOnboardSet.add(theClientOnboardTable);
    }
	
	public void addBill(BillTable theBillTable) {
		if(theBillTable==null) {
			billSet = new HashSet<BillTable>();
		}
		theBillTable.setRiskAggregator(this);
		this.billSet.add(theBillTable);
    }

	public void addFtpServerDetails(FtpServerDetails theFtpServerDetails) {
		if(theFtpServerDetails==null) {
			ftpServerDetailsSet = new HashSet<FtpServerDetails>();
		}
		theFtpServerDetails.setRiskAggregator(this);
		this.ftpServerDetailsSet.add(theFtpServerDetails);
    }

	@Override
	public String toString() {
		return "RiskAggregator [riskAggregatorId=" + riskAggregatorId + ", riskAggregatorName=" + riskAggregatorName
				+ ", riskAggregatorContact=" + riskAggregatorContact + ", modified_date=" + modified_date + "]";
	}
	
	


	/*
	 * @Override public String toString() { return "RiskAggregator [id=" + id +
	 * ", RiskAggregatorName=" + riskAggregatorName + ", RiskAggregatorContact=" +
	 * riskAggregatorContact + ", Modified_date=" + Modified_date + "]"; }
	 */

	/*
	 * @Override public String toString() { return "RiskAggregator [id=" + id +
	 * ", RiskAggregatorName=" + RiskAggregatorName + ", RiskAggregatorContact=" +
	 * RiskAggregatorContact + ", Modified_date=" + Modified_date +
	 * ", clientOnboardSet=" + clientOnboardSet + "]"; }
	 */	
	
	
}
