package com.globeop.riskfeed.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.FTPPathDetails;
import com.globeop.riskfeed.entity.FtpServerDetails;
import com.globeop.riskfeed.entity.OnBordDto;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.repository.FTPPathDetailsRepository;
import com.globeop.riskfeed.repository.FtpServerDetailsRepository;

@Service
public class FtpService  {
	
	@Autowired
	FtpServerDetailsRepository theFtpServerDetailsRepository;
	
	@Autowired
	FTPPathDetailsRepository theFTPPathDetailsRepository;	
	
	@Autowired
	RiskAggregatorService theRiskAggregatorService;
	
	@Autowired
	ClientService theClientService;
	
	public List<FtpServerDetails> findAllFtpServerDetails() {	
		return theFtpServerDetailsRepository.findAll();
	}
	
	public List<FTPPathDetails> findAllFTPPathDetails() {	
		return theFTPPathDetailsRepository.findAll();
	}

	
	public FtpServerDetails findByFtpServerDetailsId(int theId) {		
		Optional<FtpServerDetails> result = theFtpServerDetailsRepository.findById(theId);
		FtpServerDetails theFtpServerDetails=null;
		
		if (result.isPresent()) {
			theFtpServerDetails = result.get();
		}
		else {
			throw new RuntimeException("Did not find FtpServerDetails id - " + theId);
		}
		return theFtpServerDetails;
	}
	
	public FTPPathDetails findByFTPPathDetailsId(int theId) {		
		Optional<FTPPathDetails> result = theFTPPathDetailsRepository.findById(theId);
		FTPPathDetails theFTPPathDetails=null;
		
		if (result.isPresent()) {
			theFTPPathDetails = result.get();
		}
		else {
			throw new RuntimeException("Did not find FTPPathDetails id - " + theId);
		}
		return theFTPPathDetails;
	}

	
	public FtpServerDetails saveFtpServerDetails(FtpServerDetails obj) {
		// TODO Auto-generated method stub
		obj=theFtpServerDetailsRepository.save(obj);					
		return obj;
	}
	
	public FTPPathDetails saveFTPPathDetails(FTPPathDetails obj) {
		// TODO Auto-generated method stub
		obj=theFTPPathDetailsRepository.save(obj);
		return obj;
	}
	
	public String addCompleteFtpDetails(OnBordDto onBordDto) {
		String result="Error";
		try {
			RiskAggregator theRiskAggregator = theRiskAggregatorService.findById(onBordDto.getRiskAggregatorId()) ;
			ClientTable theClientTable=null;
			FtpServerDetails theFtpServerDetails=null;
			if(onBordDto.getClientId() != 0) {		
				theClientTable=theClientService.findById(onBordDto.getClientId());	
				theFtpServerDetails=new FtpServerDetails(onBordDto.getFtpName(), onBordDto.getFtpUserName(), onBordDto.getFtpPassword(), onBordDto.getFtpType(),onBordDto.getFtpPath(),onBordDto.getComments(),theClientTable,theRiskAggregator,LocalDate.now());
			}else {
				theFtpServerDetails=new FtpServerDetails(onBordDto.getFtpName(), onBordDto.getFtpUserName(), onBordDto.getFtpPassword(), onBordDto.getFtpType(),onBordDto.getFtpPath(),onBordDto.getComments(),theRiskAggregator,LocalDate.now());
			}			
						
			theFtpServerDetails=saveFtpServerDetails(theFtpServerDetails);				
			if(theFtpServerDetails == null) {
				result="Error";
			}else {
				result=""+theFtpServerDetails.getFtpDetailID();
			}
			//return true;
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return result;
	}

	
	/*
	 * public List<ClientTable> findByName(String name) {
	 * System.out.println("@@ "+name); //List<ClientTable> result =
	 * clientRepository.findByClientShortName(name.toUpperCase()); List<ClientTable>
	 * result =
	 * clientRepository.findByClientShortNameStartingWith(name.toUpperCase());
	 * //List<ClientTable> result =
	 * clientRepository.findByClientShortNameLike(name.toUpperCase()); return
	 * result; }
	 * 
	 * public boolean checkClientAlreadyExist(String name) { List<ClientTable>
	 * clients =
	 * clientRepository.findByClientShortNameStartingWith(name.toUpperCase());
	 * if(clients==null) { return false; } for(ClientTable client : clients) {
	 * if(client.getClientShortName().equals(name)) return true; } return false; }
	 */

}
