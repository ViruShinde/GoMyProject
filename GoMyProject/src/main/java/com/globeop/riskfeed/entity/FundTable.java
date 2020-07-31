package com.globeop.riskfeed.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity  
@Table(name="FundTable")
public class FundTable implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id   
	@Column(name = "FundID")
	//@GeneratedValue	//(strategy=GenerationType.AUTO)  
	@GeneratedValue	(strategy=GenerationType.IDENTITY)
	//@GeneratedValue	
	private int fundID;


	@Column(name = "FundShortName")
	private String fundShortName;
	
	@Column(name = "Modified_date")
	private LocalDate modified_date;

		
	
	//@ManyToOne
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ClientID", nullable = false)    
	private ClientTable client;
		
	@JsonManagedReference
	@OneToMany(targetEntity = ClientOnboardTable.class, cascade = CascadeType.ALL, mappedBy="fund") 	
    private Set<ClientOnboardTable> clientOnboardSet;
	
	
	
	@JsonBackReference
	public ClientTable getClient() {
		return client;
	}

	public void setClient(ClientTable client) {
		this.client = client;
	}
	

	public int getFundID() {
		return fundID;
	}

	public void setFundID(int fundID) {
		this.fundID = fundID;
	}

	public LocalDate getModified_date() {
		return modified_date;
	}

	public void setModified_date(LocalDate modified_date) {
		this.modified_date = modified_date;
	}

	public String getFundShortName() {
		return fundShortName;
	}

	public void setFundShortName(String theFundShortName) {
		fundShortName = theFundShortName;
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
		theClientOnboardTable.setFund(this);
		this.clientOnboardSet.add(theClientOnboardTable);
    }

	@Override
	public String toString() {
		return "FundTable [fundID=" + fundID + ", FundShortName=" + fundShortName + ", modified_date=" + modified_date
				+ ", clientOnboardSet=" + clientOnboardSet + "]";
	}
	
	
	

	/*
	 * @Override public String toString() { return "FundTable [FundID=" + FundID +
	 * ", FundShortName=" + FundShortName + ", Modified_date=" + Modified_date +
	 * ", client=" + client.toString() + ", clientOnboardSet=" + clientOnboardSet +
	 * "]"; }
	 */

	/*
	 * @Override public String toString() { return "FundTable [FundID=" + FundID +
	 * ", FundShortName=" + FundShortName + ", Modified_date=" + Modified_date +
	 * ", client=" + client.getClientID() + "]"; }
	 */
	
	
	

	/*
	 * @Override public String toString() { return "FundTable [FundID=" + FundID +
	 * ", FundShortName=" + FundShortName + ", Modified_date=" + Modified_date +
	 * ", client=" + client.getClientID() + "]"; }
	 */
	/*
	 * @Override public String toString() { return "FundTable [FundID=" + FundID +
	 * ", FundShortName=" + FundShortName + "]"; }
	 */
	

	
	
	/*
	 * @Override public String toString() { return "FundTable [FundID=" + FundID +
	 * ", FundShortName=" + FundShortName + ", Modified_date=" + Modified_date +
	 * ", client=" + client.getClientID() + "]"; }
	 */
	
	
	
	

}

