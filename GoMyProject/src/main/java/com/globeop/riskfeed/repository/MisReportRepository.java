package com.globeop.riskfeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.globeop.riskfeed.entity.MisReport;

public interface MisReportRepository extends JpaRepository<MisReport, Integer>, JpaSpecificationExecutor<MisReport>{
	    
}
