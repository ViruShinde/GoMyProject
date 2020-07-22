package com.globeop.riskfeed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.entity.ClientOnboardTable;
import com.globeop.riskfeed.entity.ClientTable;

import com.globeop.riskfeed.entity.RiskAggregator;

public interface ClientOnboardRepository extends JpaRepository<ClientOnboardTable, Integer> {
	
	//public List<ClientOnboardTable> findByClientAndRisk
	
	public List<ClientOnboardTable> findByClientAndRiskAggregator(ClientTable theClientTable, RiskAggregator theAggregator);
	
	//@Query("select ClientID FROM ClientOnboardTable WHERE RiskAggregatorId = ?1")
	//public List<ClientOnboardTable> findByRiskAggregator(int RiskAggregatorId);
	public List<ClientOnboardTable> findByRiskAggregator(RiskAggregator theAggregator);
	
	
	//nativeQuery = true
	//@Query(value="select new com.globeop.riskfeed.dto.TestDto(c.ClientID, c.RiskAggregatorId, c.FundID); FROM ClientOnboardTable AS c ", nativeQuery = true)
	//@Query("SELECT new com.globeop.riskfeed.dto.TestDto(c.ClientID, c.RiskAggregatorId, c.FundID) from clientonboardtable c ")
	//public List<TestDto> testQuery();
	
	@Query(value="SELECT * FROM ClientOnboardTable c",nativeQuery=true)
	public List<ClientOnboardTable> testQuery2();
	
	//working
	//@Query(value= "SELECT NEW com.globeop.riskfeed.dto.TestDto(c.client.ClientID,c.riskAggregator.id,c.fund.FundID ) from ClientOnboardTable AS c ")
	
	//@Query(value= "SELECT NEW com.globeop.riskfeed.dto.TestDto(c.client.clientShortName,c.riskAggregator.RiskAggregatorName, c.fund.FundID ) from ClientOnboardTable AS c ")
	
	
	/*
	 * @Query(value=
	 * "SELECT NEW com.globeop.riskfeed.dto.TestDto(c.riskAggregator.RiskAggregatorName,c.client.clientShortName, c.fund.fundShortName,b.monthlyFee ) "
	 * + " from ClientOnboardTable AS c " + " RIGHT JOIN BillTable AS b " +
	 * " on c.client.ClientID = b.client.ClientID " +
	 * " AND c.riskAggregator.id = b.riskAggregator.id")
	 */
	 
	
	
	/*
	 * @Query(value=
	 * "SELECT NEW com.globeop.riskfeed.dto.TestDto(c.riskAggregator.RiskAggregatorName,c.client.clientShortName, c.fund.fundShortName,b.monthlyFee ) "
	 * + " from ClientOnboardTable AS c , BillTable AS b" +
	 * " WHERE c.client.ClientID = b.client.ClientID " +
	 * " AND c.riskAggregator.id = b.riskAggregator.id")
	 */
	  
	
	
	  @Query(value=
	  "SELECT NEW com.globeop.riskfeed.dto.TestDto("
	  + "c.riskAggregator.id, c.riskAggregator.riskAggregatorName, c.riskAggregator.riskAggregatorContact, "
	  + "c.client.ClientID, c.client.clientShortName, "
	  + "c.fund.FundID, c.fund.fundShortName,"
	  + "c.ClientOnboardId, c.SetUpDate, c.EndDate, c.automationProcess, c.isActive, c.Comments, c.Frequency,"
	  + "b.billId , b.setupFee, b.monthlyFee, b.devlopementFee, b.isClientPayingOldCharges, b.isWaivedOff, b.billStartDate, b.billEndDate, b.crmName, b.crmailID, b.billingComments, b.goCheckNoteId, b.fundcount,"	  
	  + "d.developmentId , d.developmentHours, d.developmentCost, d.isWaivedOff, d.startDate, d.endDate, d.developmentComments ) "
	  
	  + " from ClientOnboardTable AS c "
	  
	  + " RIGHT JOIN BillTable AS b " 
	  + " on c.client.ClientID = b.client.ClientID " 
	  + " AND c.riskAggregator.id = b.riskAggregator.id "	  
	
	  + " LEFT JOIN DevelopmentTable AS d " 
	  + " on c.client.ClientID = d.client.ClientID " 
	  + " AND c.riskAggregator.id = d.riskAggregator.id "
	 
	 )
	
	 
			  
	public List<TestDto> getAllBillingDetails();
	//public List<TestDto> testQuery();
	  
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.dto.TestDto("
			  + "c.riskAggregator.riskAggregatorName,  "
			  + "c.client.clientShortName) "
			  
			  + " from ClientOnboardTable AS c "
			  + " where c.riskAggregator.id Not in ( select b.riskAggregator.id from BillTable AS b ) " 
			  + " and c.client.ClientID Not in ( select b.client.ClientID from BillTable AS b) " )
	  
	public List<TestDto> getAllPendingBillingDetails(); 
	  
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.dto.TestDto("
			  + "c.riskAggregator.id,  "
			  + "c.riskAggregator.riskAggregatorName,  "
			  + "COUNT( DISTINCT c.client ) ) "
			  
			  + " from ClientOnboardTable AS c "
			  +" GROUP BY c.riskAggregator"
			  +" ORDER BY c.riskAggregator.riskAggregatorName asc"
			 )
	  
	public List<TestDto> getOveraAllDetails(); 
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.dto.TestDto("
			  + "c.client.ClientID,  "
			  + "c.client.clientShortName, "
			  + "c.client.Modified_date  )"	
			  
			  + " from ClientOnboardTable AS c "
			  + " where c.riskAggregator.id =?1"
			  +" GROUP BY c.client.ClientID"			  
			 )
	  public List<TestDto> findByRiskAggregator2(int theAggregatorId);
	  
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.dto.TestDto("
			  + "c.ClientOnboardId,  "
			  + "c.client.ClientID,  "
			  + "c.client.clientShortName, "
			  + "c.fund.fundShortName, "
			  + "c.SetUpDate, "
			  + "c.isActive, "
			  + "c.Frequency, "
			  + "c.automationProcess, "
			  + "c.Comments, "
			  + "c.Modified_date )"
			  
			  + " from ClientOnboardTable AS c "
			  + " where c.riskAggregator.id =?1"
			  + " and c.client.ClientID =?2"
			  + " and c.isActive = 'Active'"
			  //+" GROUP BY c.client.ClientID"			  
			 )
	  public List<TestDto> findFundsDetailsByClientAndRiskAggregator(int riskAggregatorId,int clientId);
}
