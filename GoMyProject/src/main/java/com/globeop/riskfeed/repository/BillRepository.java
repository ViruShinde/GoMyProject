package com.globeop.riskfeed.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.globeop.riskfeed.entity.BillTable;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.DevelopmentTable;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.enums.IsClientPayingOldCharges;
import com.globeop.riskfeed.enums.IsWaivedOff;

public interface BillRepository extends JpaRepository<BillTable, Integer> {

	public List<BillTable> findByClientAndRiskAggregator(ClientTable theClientTable, RiskAggregator theAggregator);
	

	
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.BillTable( b, c, r ) "			
			+ " from BillTable AS b"
			+ " LEFT JOIN ClientTable AS c " 
			+ " on c.clientID = b.client.clientID "
			+ " LEFT JOIN RiskAggregator AS r " 
			+ " on r.riskAggregatorId = b.riskAggregator.riskAggregatorId "
			+ " where b.waiverFileName LIKE %?1% OR b.crmName LIKE %?1% OR b.billingComments LIKE %?1% "
			+ " OR c.clientShortName LIKE %?1% OR r.riskAggregatorName LIKE %?1% ")	
	public Page<BillTable> searchPageable(Pageable pageable, String keyword);

}
