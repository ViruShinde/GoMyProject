package com.globeop.riskfeed.entity;

import java.io.Serializable;
import java.util.Date;
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
	private int FundID;


	@Column(name = "FundShortName")
	private String fundShortName;
	
	@Column(name = "Modified_date")
	private Date Modified_date;

		
	
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
		return FundID;
	}

	public void setFundID(int fundID) {
		FundID = fundID;
	}

	public String getFundShortName() {
		return fundShortName;
	}

	public void setFundShortName(String theFundShortName) {
		fundShortName = theFundShortName;
	}

	public Date getModified_date() {
		return Modified_date;
	}

	public void setModified_date(Date modified_date) {
		Modified_date = modified_date;
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
		return "FundTable [FundID=" + FundID + ", FundShortName=" + fundShortName + ", Modified_date=" + Modified_date
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

