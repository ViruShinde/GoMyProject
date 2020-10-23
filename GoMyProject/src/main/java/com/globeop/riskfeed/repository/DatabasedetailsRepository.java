package com.globeop.riskfeed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globeop.riskfeed.entity.Databasedetails;
import com.globeop.riskfeed.entity.FundTable;

public interface DatabasedetailsRepository extends JpaRepository <Databasedetails, Integer> {
	
	public List<Databasedetails> findByEnvironment(String env);
	
}
