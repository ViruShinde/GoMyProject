package com.globeop.riskfeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.globeop.riskfeed.entity.FTPPathDetails;

//@RepositoryRestResource(path ="FTPPathDetails")
public interface FTPPathDetailsRepository extends JpaRepository<FTPPathDetails, Integer> {

}
