package com.globeop.riskfeed.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globeop.riskfeed.dto.OnBoardFunds;
import com.globeop.riskfeed.entity.ClientOnboardTable;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.FundTable;
import com.globeop.riskfeed.entity.OnBordDto;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.enums.AutomationProcess;
import com.globeop.riskfeed.enums.IsActive;

@Service
public class OnBordService {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private FundService fundService;
	
	@Autowired
	private RiskAggregatorService riskAggregatorService;
	
	@Autowired
	private ClientOnboardService theClientOnboardService;
	
	public void addDetails(OnBordDto onBordDto) {
				
		ClientTable client=clientService.findById(onBordDto.getClientId());
		
		System.out.println("@@@@@@@@@@@@@ "+client.getClientShortName());
		FundTable fund = new FundTable();
		fund.setFundShortName(onBordDto.getFundName());
		fund.setModified_date(LocalDate.now());
		//fund.setClient(client);
		
		client.addFund(fund);
		
		clientService.save(client);
		
		//fundService.save(fund);
	}
	
	
	public List addFundDetails(OnBordDto onBordDto) {
		List duplicateList=new ArrayList();
		ClientTable client=clientService.findById(onBordDto.getClientId());
		String fundNames=onBordDto.getFundName();
		if(fundNames.startsWith(",")){
			fundNames=onBordDto.getFundName().substring(1,fundNames.length());
		}
		String Funds[]=fundNames.split(",");
		for(String s: Funds) {
			FundTable fund = new FundTable();
			System.out.println("@@@ "+s);
			fund.setModified_date(LocalDate.now());
			fund.setFundShortName(s);
			//check for duplicate entry
			boolean result = fundService.checkFundAlreadyExist(s);
			if(result == false) {
				client.addFund(fund);
			}else {
				// duplicate entry dont add.
				duplicateList.add(s);
			}
		}		
		clientService.save(client);
		return duplicateList;
	}
	
	public void addOnboardDetails(OnBordDto onBordDto) {
	
		ClientTable client = clientService.findById(onBordDto.getClientId());
		RiskAggregator riskAggregator = riskAggregatorService.findById(onBordDto.getRiskAggregatorId());
		System.out.println(riskAggregator);
		
		List<OnBoardFunds> onBoardFundsList = onBordDto.getOnBoardFundsList();
		
		// Added this call to save funds in Fundtable as well before onboarding and also checks if fund already exists.
		addFundDetails(onBordDto);
		
		for(OnBoardFunds funds: onBoardFundsList) {			
			FundTable fundTable = fundService.findByFundShortName(funds.getFundName());			
			ClientOnboardTable theClientOnboardTable = new ClientOnboardTable();			
			theClientOnboardTable.setSetUpDate(LocalDate.parse(onBordDto.getSetUpDate()));
			if( !("".equalsIgnoreCase(onBordDto.getEndDate()) || null==onBordDto.getEndDate()) ) {				
				theClientOnboardTable.setEndDate(LocalDate.parse(onBordDto.getEndDate()));
			}			
			theClientOnboardTable.setAutomationProcess(AutomationProcess.valueOf(onBordDto.getAutomationProcess()));
			theClientOnboardTable.setIsActive(IsActive.valueOf(onBordDto.getIsActive() ));
			theClientOnboardTable.setComments(onBordDto.getComments());
			theClientOnboardTable.setFrequency(funds.getFrequency());
			theClientOnboardTable.setModified_date(LocalDate.now());
			
			theClientOnboardTable.setClient(client);
			theClientOnboardTable.setRiskAggregator(riskAggregator);
			theClientOnboardTable.setFund(fundTable);

			
			theClientOnboardService.save(theClientOnboardTable);
		}

		
	}
	
	public List<ClientOnboardTable> findByClientAndRiskAggregator(ClientTable theClientTable, RiskAggregator theAggregator) {		
		return theClientOnboardService.findByClientAndRiskAggregator(theClientTable, theAggregator);
	}
}
