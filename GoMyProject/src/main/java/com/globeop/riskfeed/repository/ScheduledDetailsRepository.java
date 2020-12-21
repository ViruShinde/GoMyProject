package com.globeop.riskfeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globeop.riskfeed.entity.ScheduledDetails;

public interface ScheduledDetailsRepository extends JpaRepository<ScheduledDetails, Integer> {

	public ScheduledDetails findByscheduledDetailsId(int id);
}
