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
	  + "c.riskAggregator.riskAggregatorId, c.riskAggregator.riskAggregatorName, c.riskAggregator.riskAggregatorContact, "
	  + "c.client.clientID, c.client.clientShortName, "
	  + "c.fund.fundID, c.fund.fundShortName,"
	  + "c.clientOnboardId, c.setUpDate, c.endDate, c.automationProcess, c.isActive, c.comments, c.frequency,"
	  + "b.billId , b.setupFee, b.monthlyFee, b.devlopementFee, b.isClientPayingOldCharges, b.isWaivedOff, b.billStartDate, b.billEndDate, b.crmName, b.crmailID, b.billingComments, b.goCheckNoteId, b.fundcount,"	  
	  + "d.developmentId , d.developmentHours, d.developmentCost, d.isWaivedOff, d.startDate, d.endDate, d.developmentComments ) "
	  
	  + " from ClientOnboardTable AS c "
	  
	  + " RIGHT JOIN BillTable AS b " 
	  + " on c.client.clientID = b.client.clientID " 
	  + " AND c.riskAggregator.riskAggregatorId = b.riskAggregator.riskAggregatorId "	  
	
	  + " LEFT JOIN DevelopmentTable AS d " 
	  + " on c.client.clientID = d.client.clientID " 
	  + " AND c.riskAggregator.riskAggregatorId = d.riskAggregator.riskAggregatorId "
	 
	 )
	
	 
			  
	public List<TestDto> getAllBillingDetails();
	//public List<TestDto> testQuery();
	  
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.dto.TestDto("
			  + "c.riskAggregator.riskAggregatorName,  "
			  + "c.client.clientShortName) "
			  
			  + " from ClientOnboardTable AS c "
			  + " where c.riskAggregator.riskAggregatorId Not in ( select b.riskAggregator.riskAggregatorId from BillTable AS b ) " 
			  + " and c.client.clientID Not in ( select b.client.clientID from BillTable AS b) " )
	  
	public List<TestDto> getAllPendingBillingDetails(); 
	  
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.dto.TestDto("
			  + "c.riskAggregator.riskAggregatorId,  "
			  + "c.riskAggregator.riskAggregatorName,  "
			  + "COUNT( DISTINCT c.client ) ) "
			  
			  + " from ClientOnboardTable AS c "
			  +" WHERE c.isActive = 'Active' "
			  +" GROUP BY c.riskAggregator"
			  +" ORDER BY c.riskAggregator.riskAggregatorName asc"
			 )
	  
	public List<TestDto> getOveraAllDetails(); 
	  
	  
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.dto.TestDto("
			  + "c.client.clientID,  "
			  + "c.client.clientShortName, "
			  + "b.billStartDate  )"	
			  
			  + " from ClientOnboardTable AS c "
			  + " left JOIN BillTable AS b on " 
 			  + " b.riskAggregator.riskAggregatorId = c.riskAggregator.riskAggregatorId "
			  + " where c.riskAggregator.riskAggregatorId =?1"
			  + " and c.isActive = 'Active' "
			  +" GROUP BY c.client.clientID"			  
			 )
	  public List<TestDto> findByRiskAggregator2(int theAggregatorId);
	  
	  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.dto.TestDto("
			  + "c.clientOnboardId,  "
			  + "c.client.clientID,  "
			  + "c.client.clientShortName, "
			  + "c.fund.fundShortName, "
			  + "c.setUpDate, "
			  + "c.isActive, "
			  + "c.frequency, "
			  + "c.automationProcess, "
			  + "c.comments, "
			  + "c.modified_date )"
			  
			  + " from ClientOnboardTable AS c "
			  + " where c.riskAggregator.riskAggregatorId =?1"
			  + " and c.client.clientID =?2"
			  + " and c.isActive = 'Active'"
			  //+" GROUP BY c.client.ClientID"			  
			 )
	  public List<TestDto> findFundsDetailsByClientAndRiskAggregator(int riskAggregatorId,int clientId);
	  
	  

			  
	  @Query(value=
			  "SELECT NEW com.globeop.riskfeed.dto.TestDto("
			  + "c.riskAggregator.riskAggregatorId,  "
			  + "c.riskAggregator.riskAggregatorName,  "
			  + "c.client.clientID,  "
			  + "c.client.clientShortName, "
			  + "b.billId, "
			  + "b.billStartDate, "
			  + "b.billEndDate )"
			  
			  + " from ClientOnboardTable AS c "
			  
 			  + " LEFT JOIN BillTable AS b " 
 			  + " on b.client.clientID = c.client.clientID " 
 			  + " AND b.riskAggregator.riskAggregatorId = c.riskAggregator.riskAggregatorId "
 			  + " GROUP BY c.riskAggregator, c.client"
			  + " ORDER BY c.riskAggregator.riskAggregatorName asc"
 			  
			 )
	  
	public List<TestDto> getClientOnBoardBillDetails(); 
}
