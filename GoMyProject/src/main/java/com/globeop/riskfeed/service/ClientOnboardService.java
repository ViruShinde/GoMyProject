package com.globeop.riskfeed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.entity.ClientOnboardTable;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.FundTable;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.repository.ClientOnboardRepository;

@Service
public class ClientOnboardService implements CommonService<ClientOnboardTable> {

	@Autowired
	private ClientOnboardRepository theClientOnboardRepository;
	
	@Override
	public List<ClientOnboardTable> findAll() {		
		return theClientOnboardRepository.findAll();
	}

	@Override
	public ClientOnboardTable findById(int theId) {
		
		Optional<ClientOnboardTable> result = theClientOnboardRepository.findById(theId);
		ClientOnboardTable theClientOnboardTable=null;
		
		if (result.isPresent()) {
			theClientOnboardTable = result.get();
		}
		else {
			throw new RuntimeException("Did not find ClientOnboard id - " + theId);
		}
		return theClientOnboardTable;	
	}

	@Override
	public void save(ClientOnboardTable obj) {
		theClientOnboardRepository.save(obj);
		
	}

	@Override
	public void deleteById(int theId) {
		theClientOnboardRepository.deleteById(theId);
		
	}

	public List<ClientOnboardTable> findByClientAndRiskAggregator(ClientTable theClientTable, RiskAggregator theAggregator){
		return theClientOnboardRepository.findByClientAndRiskAggregator(theClientTable, theAggregator);
	}
	
	public List<TestDto> findFundsDetailsByClientAndRiskAggregator(int clientId, int riskAggregatorId){
		return theClientOnboardRepository.findFundsDetailsByClientAndRiskAggregator(riskAggregatorId,clientId);
	}
	public List<ClientOnboardTable> getClientsOfRiskAggregator( RiskAggregator theAggregator){
		//return theClientOnboardRepository.findByRiskAggregator(theAggregator.getId());
		return theClientOnboardRepository.findByRiskAggregator(theAggregator);
	}
		
	public List<TestDto> findByRiskAggregator2( int theAggregatorId){
		return theClientOnboardRepository.findByRiskAggregator2(theAggregatorId);
	}
	
	public List<TestDto> getAllBillingDetails() {
		return theClientOnboardRepository.getAllBillingDetails();		
	}
	
	public List<TestDto> test() {
		//return theClientOnboardRepository.testQuery();
		return theClientOnboardRepository.getAllBillingDetails();
		//return null;
	}
	
	public List<TestDto> getPendingBillingDetails() {
		//return theClientOnboardRepository.testQuery();
		return theClientOnboardRepository.getAllPendingBillingDetails();
		//return null;
	}
	
	public List<ClientOnboardTable> test2() {
		return theClientOnboardRepository.testQuery2();
	}
	
	public List<TestDto> getOverAllDetails() {
		return theClientOnboardRepository.getOveraAllDetails();
	}
	
	public List<TestDto> getClientOnBoardBillDetails() {
		
		List<TestDto> t =new ArrayList<TestDto>();
		
		try {
			t = theClientOnboardRepository.getClientOnBoardBillDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("===============");
		System.out.println(t);
		return t;
		//return theClientOnboardRepository.getClientOnBoardBillDetails();		
	}
}
