package com.globeop.riskfeed.service;

import java.time.LocalDate;
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
	
	
	public void addFundDetails(OnBordDto onBordDto) {
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
			client.addFund(fund);
		}		
		clientService.save(client);
	}
	
	public void addOnboardDetails(OnBordDto onBordDto) {
		//OnBordDto [clientName=BFAM, clientId=2, riskAggregatorId=1, fundName=null, fundIds=null, setUpDate=2020-04-04, endDate=2020-04-04,
		//automationProcess=RiskMQ, isActive=Active, comments=CCCCCCCCCCCCCCCCCC, frequency=null, 
		//onBoardFundsList=[OnBoardFunds [fundName=FUND1, frequency=D], OnBoardFunds [fundName=FUND2, frequency=W], OnBoardFunds [fundName=FUND3, frequency=D,W,M], OnBoardFunds [fundName=FUND4, frequency=null]]]
	
		System.out.println("@@@@@@@@@@@@@@ Inside addOnboardDetails @@@@@@@@@@@@@@@@@@@ ");
		
		ClientTable client = clientService.findById(onBordDto.getClientId());
		RiskAggregator riskAggregator = riskAggregatorService.findById(onBordDto.getRiskAggregatorId());
		System.out.println(riskAggregator);
		
		List<OnBoardFunds> onBoardFundsList = onBordDto.getOnBoardFundsList();
		
		for(OnBoardFunds funds: onBoardFundsList) {
			System.out.println("11111111 "+funds.getFundName());
			FundTable fundTable = fundService.findByFundShortName(funds.getFundName());
			System.out.println("22222222 "+fundTable.getFundShortName() + " >> "+ onBordDto.getSetUpDate() + " >> "+onBordDto.getEndDate());
			ClientOnboardTable theClientOnboardTable = new ClientOnboardTable();
			//theClientOnboardTable.setSetUpDate(new Date());//(GenricUtil.convertStringToDate(onBordDto.getSetUpDate()));
			theClientOnboardTable.setSetUpDate(LocalDate.parse(onBordDto.getSetUpDate()));
			theClientOnboardTable.setEndDate(LocalDate.parse(onBordDto.getEndDate()));
			theClientOnboardTable.setAutomationProcess(AutomationProcess.valueOf(onBordDto.getAutomationProcess()));
			theClientOnboardTable.setIsActive(IsActive.valueOf(onBordDto.getIsActive() ));
			theClientOnboardTable.setComments(onBordDto.getComments());
			theClientOnboardTable.setFrequency(funds.getFrequency());
			theClientOnboardTable.setModified_date(LocalDate.now());
			
			client.addClientOnboard(theClientOnboardTable);
			
			riskAggregator.addClientOnboard(theClientOnboardTable);
			fundTable.addClientOnboard(theClientOnboardTable);
			
			//fundService.save(fundTable);
			
			
		}
		clientService.save(client);
		riskAggregatorService.save(riskAggregator);
		
	}
	
	public List<ClientOnboardTable> findByClientAndRiskAggregator(ClientTable theClientTable, RiskAggregator theAggregator) {		
		return theClientOnboardService.findByClientAndRiskAggregator(theClientTable, theAggregator);
	}
}
