package com.globeop.riskfeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globeop.riskfeed.entity.Databasedetails;
import com.globeop.riskfeed.repository.DatabaseRepository;

@Service
public class DatabaseService {

	@Autowired
	private DatabaseRepository databaseRepository;
	
	@Autowired 
	private DatabasedetailsService theDatabasedetailsService;
	
	public List getdatabaselist(String serverId) {
		Databasedetails dbDetails = theDatabasedetailsService.findById(Integer.parseInt(serverId));
		return databaseRepository.getDatabaseDetails(dbDetails);	
	}
	
	public List getTableList(String serverId, String selectedDatabaseName) {
		Databasedetails dbDetails = theDatabasedetailsService.findById(Integer.parseInt(serverId));
		dbDetails.setUrl(dbDetails.getUrl()+selectedDatabaseName);
		return databaseRepository.getTableDetails(dbDetails,selectedDatabaseName);	
	}
	
	public Object getResult(String database, String serverId, String env,String query, String databaseName) {
		Databasedetails dbDetails = theDatabasedetailsService.findById(Integer.parseInt(serverId));
		dbDetails.setUrl(dbDetails.getUrl()+databaseName);
		return databaseRepository.getResult(database, serverId, env,query,databaseName,dbDetails);
	}
	
}
