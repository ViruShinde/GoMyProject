package com.globeop.riskfeed.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.DevelopmentTable;
import com.globeop.riskfeed.entity.FtpServerDetails;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.enums.IsWaivedOff;

public interface DevelopmentRepository extends JpaRepository<DevelopmentTable, Integer> {

	public List<DevelopmentTable> findByClientAndRiskAggregator(ClientTable theClientTable, RiskAggregator theAggregator);
	
	
/*
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.DevelopmentTable(d.developmentId, d.developmentHours, d.developmentCost, d.isWaivedOff, "
			+ "	d.startDate, d.endDate, d.developmentComments, d.approvalMail, d.fileName, "
			+ "	d.fileType, c,r, d.modified_date ) "			
			+ " from DevelopmentTable AS d"
			+ " LEFT JOIN ClientTable AS c " 
			+ " on c.clientID = d.client.clientID "
			+ " LEFT JOIN RiskAggregator AS r " 
			+ " on r.riskAggregatorId = d.riskAggregator.riskAggregatorId ")
	public Page<DevelopmentTable> findAllPageable(Pageable pageable);
*/
	
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.DevelopmentTable(d.developmentId, d.developmentHours, d.developmentCost, d.isWaivedOff, "
			+ "	d.startDate, d.endDate, d.developmentComments, d.approvalMail, d.fileName, "
			+ "	d.fileType, c,r, d.modified_date ) "			
			+ " from DevelopmentTable AS d"
			+ " LEFT JOIN ClientTable AS c " 
			+ " on c.clientID = d.client.clientID "
			+ " LEFT JOIN RiskAggregator AS r " 
			+ " on r.riskAggregatorId = d.riskAggregator.riskAggregatorId "
			+ " where "
			+ " d.developmentComments LIKE %?1% OR "
			+ " c.clientShortName LIKE %?1% OR r.riskAggregatorName LIKE %?1% ")	
	public Page<DevelopmentTable> searchPageable(Pageable pageable, String keyword);
}
