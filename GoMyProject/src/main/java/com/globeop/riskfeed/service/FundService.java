package com.globeop.riskfeed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.FundTable;
import com.globeop.riskfeed.repository.FundTableRepository;

@Service
public class FundService implements CommonService<FundTable> {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private FundTableRepository fundTableRepository;

	@Override
	public List<FundTable> findAll() {
		// TODO Auto-generated method stub
		List<FundTable> l=fundTableRepository.findAll();
		printList(l);
		//return fundTableRepository.findAll();
		return l;
	}

	@Override
	public FundTable findById(int theId) {

		Optional<FundTable> result = fundTableRepository.findById(theId);
		FundTable theFund=null;
		
		if (result.isPresent()) {
			theFund = result.get();
		}
		else {
			throw new RuntimeException("Did not find Fund id - " + theId);
		}
		return theFund;	
	}

	@Override
	public void save(FundTable obj) {
		fundTableRepository.save(obj);
		
	}

	@Override
	public void deleteById(int theId) {
		fundTableRepository.deleteById(theId);
		
	}


	private void printList (List<FundTable> l){
		for(Object o :l) {
			System.out.println(o.toString());
		}
	}
	
	
	public void update(FundTable theFundTable) {
		try {
			//ClientTable c = clientService.findById(theFundTable.getClientID());
		//	c.addFund(theFundTable);
			fundTableRepository.save(theFundTable);
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public List<FundTable> findByClient(ClientTable theClientTable) {
		return fundTableRepository.findByClient(theClientTable);
	}
	
	public FundTable  findByFundShortName(String fundShortName){
		FundTable theFund=null;
		List<FundTable> funds= fundTableRepository.findByFundShortName(fundShortName);
		for(FundTable f:funds) {
			if(f.getFundShortName().equals(fundShortName)) {
				theFund=f;
				break;
			}
		}
		return theFund;
	}
	
	public boolean checkFundAlreadyExist(String name) {
		List<FundTable> funds = fundTableRepository.findByFundShortName(name.toUpperCase());
		if(funds==null) {
			return false;
		}
		for(FundTable fund : funds) {
			if(fund.getFundShortName().equals(name)) 
				return true;						
		}
		return false;
	}
	
}
