package com.globeop.riskfeed.repository;

import javax.persistence.Column;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.globeop.riskfeed.entity.FtpServerDetails;

//@RepositoryRestResource(path ="FtpServerDetails")
public interface FtpServerDetailsRepository extends JpaRepository<FtpServerDetails, Integer> {
	
	
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.FtpServerDetails(ftp.ftpDetailID, ftp.ftpName, ftp.ftpUserName, ftp.ftpPassword, ftp.ftpType, ftp.ftpPath, ftp.comments,c,r,ftp.modified_date) "
			+ " from FtpServerDetails AS ftp"
			+ " LEFT JOIN ClientTable AS c " 
			+ " on c.clientID = ftp.client.clientID "
			+ " LEFT JOIN RiskAggregator AS r " 
			+ " on r.riskAggregatorId = ftp.riskAggregator.riskAggregatorId ")
	public Page<FtpServerDetails> findAllPageable(Pageable pageable);

	
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.FtpServerDetails("
			+ " ftp.ftpDetailID, ftp.ftpName, ftp.ftpUserName, ftp.ftpPassword, ftp.ftpType, ftp.ftpPath, ftp.comments,c,r,ftp.modified_date) "
			+ " from FtpServerDetails AS ftp "
			+ " LEFT JOIN ClientTable AS c " 
			+ " on c.clientID = ftp.client.clientID "
			+ " LEFT JOIN RiskAggregator AS r " 
			+ " on r.riskAggregatorId = ftp.riskAggregator.riskAggregatorId "
			+ " where ftp.ftpName LIKE %?1% OR ftp.ftpUserName LIKE %?1% OR ftp.ftpType LIKE %?1% "
			+ " OR ftp.ftpPath LIKE %?1% OR ftp.comments LIKE %?1% OR c.clientShortName LIKE %?1% OR r.riskAggregatorName LIKE %?1% ")	
	public Page<FtpServerDetails> searchFtpPageable(Pageable pageable, String keyword);
	
}
