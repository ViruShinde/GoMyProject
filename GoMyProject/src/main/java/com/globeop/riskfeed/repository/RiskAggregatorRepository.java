package com.globeop.riskfeed.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.RiskAggregator;

/*@RepositoryRestResource(path ="riskAggregators")*/
public interface RiskAggregatorRepository extends JpaRepository<RiskAggregator, Integer> {
	
	public List<RiskAggregator> findByRiskAggregatorNameStartingWith(String riskAggregatorName);
			
	
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.RiskAggregator(r.riskAggregatorId, r.riskAggregatorName, r.riskAggregatorContact, r.modified_date) from RiskAggregator AS r ")
	public Page<RiskAggregator> findAllPageable(Pageable pageable);

	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.RiskAggregator(r.riskAggregatorId, r.riskAggregatorName, r.riskAggregatorContact, r.modified_date) from RiskAggregator AS r where r.riskAggregatorId=?1 ")
	public Page<RiskAggregator> findByIdPageable(Pageable pageable, int id);
	
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.RiskAggregator(r.riskAggregatorId, r.riskAggregatorName, r.riskAggregatorContact, r.modified_date) "
			+ "from RiskAggregator AS r "
			+ "where r.riskAggregatorName LIKE %?1% OR r.riskAggregatorContact LIKE %?1% ")
	public Page<RiskAggregator> searchRiskAggregatorPageable(Pageable pageable, String keyword);

}
