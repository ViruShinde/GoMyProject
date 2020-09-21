package com.globeop.riskfeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.globeop.riskfeed.entity.FtpServerDetails;

//@RepositoryRestResource(path ="FtpServerDetails")
public interface FtpServerDetailsRepository extends JpaRepository<FtpServerDetails, Integer> {

}
