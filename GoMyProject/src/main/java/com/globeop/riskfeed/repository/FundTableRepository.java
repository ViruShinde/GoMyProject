package com.globeop.riskfeed.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.FundTable;

public interface FundTableRepository extends JpaRepository<FundTable, Integer> {


	public List<FundTable> findByClient(ClientTable theClientTable);
	
	public List<FundTable> findByFundShortName(String fundShortName);
	
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.FundTable(f.fundID,f.fundShortName,f.modified_date) from FundTable AS f ")
	public Page<FundTable> findAllPageable(Pageable pageable);
	
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.FundTable(f.fundID,f.fundShortName,f.modified_date) from FundTable AS f where f.fundID=?1 ")
	public Page<FundTable> findByIdPageable(Pageable pageable, int id);
	
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.FundTable(f.fundID,f.fundShortName,f.modified_date) "
			+ "from FundTable AS f where f.fundShortName LIKE %?1% ")
	public Page<FundTable> searchFundPageable(Pageable pageable, String keyword);
}
