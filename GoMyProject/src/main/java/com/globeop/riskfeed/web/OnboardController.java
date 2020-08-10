package com.globeop.riskfeed.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.globeop.riskfeed.dto.OnBoardFunds;
import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.entity.ClientOnboardTable;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.OnBordDto;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.service.ClientOnboardService;
import com.globeop.riskfeed.service.ClientService;
import com.globeop.riskfeed.service.OnBordService;
import com.globeop.riskfeed.service.RiskAggregatorService;
import com.globeop.riskfeed.validator.OnBordValidator;

@Controller
public class OnboardController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private OnBordService onBordService;

	//@Autowired
	//private FundService fundService;
	
	@Autowired
	private RiskAggregatorService riskAggregatorService;
	
	@Autowired
	private ClientOnboardService theClientOnboardService;
	
	
	// get OnBord page
    @GetMapping("/AddOnBordDetails")
    public String showOnBordForm2(OnBordDto onBordDto) {       	    	
    	return "OnBord";
    }
    
    @Autowired
    private OnBordValidator onBordValidator;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
       binder.addValidators(onBordValidator);
    }
    
    // add OnBord details 
    //@RequestMapping("/AddOnBordDetails")
    @PostMapping("/AddOnBordDetails")
	public String addOnBordDetails( /* @ModelAttribute("onBordDto")  @Valid*/  OnBordDto onBordDto, Errors errors) {		
		try {
			System.out.println(onBordDto);
			if(errors.hasErrors()) {
				System.out.println("ERROR accoured");				       				
				return "OnBord";								
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		} 	    	
		//return "redirect:/getClient";
		return "redirect:/OnBordClient";
	}
    
    @GetMapping("/AddOnBordDetails1")
    public String showOnBordForm1(OnBordDto onBordDto) {  
    	onBordDto.setOnBoardForm("onBoardForm1");
    	return "OnBord1";
    }
    
    @PostMapping("/AddOnBordDetails1")
	public String addOnBordDetailsTest( @ModelAttribute("onBordDto") @Valid  OnBordDto onBordDto, Errors errors, Model model) {		
		try {
			//System.out.println(onBordDto.getFundList());
			//onBordService.addDetails(onBordDto);
			if(errors.hasErrors()) {
				System.out.println("ERROR accoured");				       				
				return "OnBord1";								
			}else{
				onBordDto.setOnBoardForm("onBoardForm2");
				RiskAggregator riskAggregator =  riskAggregatorService.findById(onBordDto.getRiskAggregatorId());
				onBordDto.setRiskAggregatorName(riskAggregator.getRiskAggregatorName());
				model.addAttribute("OnBordDto", onBordDto);  
				System.out.println(onBordDto);
				String[] selectedFunds=onBordDto.getFundName().substring(0,onBordDto.getFundName().length()).split(",");
				List<OnBoardFunds> fundList=new ArrayList<OnBoardFunds>();
				for(String fundName : selectedFunds) {
					OnBoardFunds f = new OnBoardFunds();
					f.setFundName(fundName);
					fundList.add(f);
				}
				//onBordDto.setOnBoardFundsList(GenricUtil.getClientFundList2());
				onBordDto.setOnBoardFundsList(fundList);
				System.out.println("@@@@@@@@@@ "+onBordDto.getOnBoardFundsList());
			
				//OnBordDto [clientName=BFAM, clientId=2, riskAggregatorId=1, fundName=null, fundIds=null, setUpDate=2020-04-04, endDate=2020-04-04,
				//automationProcess=RiskMQ, isActive=Active, comments=CCCCCCCCCCCCCCCCCC, frequency=null, 
				//onBoardFundsList=[OnBoardFunds [fundName=FUND1, frequency=D], OnBoardFunds [fundName=FUND2, frequency=W], OnBoardFunds [fundName=FUND3, frequency=D,W,M], OnBoardFunds [fundName=FUND4, frequency=null]]]
			
				//onBordService.addOnboardDetails(onBordDto);
				//model.addAttribute("onBordDto",onBordDto);
				return "OnBord2";
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
    
    @PostMapping("/AddOnBordDetails2")
	public String addOnBordDetailsTest2(  @ModelAttribute("onBordDto") @Valid OnBordDto onBordDto, Errors errors, Model model) {		
		try {
			if(errors.hasErrors()) {
				System.out.println("ERROR accoured");				       				
				return "OnBord2";								
			}else{
				//System.out.println(onBordDto.getFundList());
				//onBordService.addDetails(onBordDto);
				model.addAttribute("OnBordDto", onBordDto);    
				//onBordDto.setOnBoardFundsList(GenricUtil.getClientFundList2());
				//System.out.println("@@@@@@@@@@ "+onBordDto.getOnBoardFundsList());
				System.out.println(onBordDto);
				//OnBordDto [clientName=BFAM, clientId=2, riskAggregatorId=1, fundName=null, fundIds=null, setUpDate=2020-04-04, endDate=2020-04-04,
				//automationProcess=RiskMQ, isActive=Active, comments=CCCCCCCCCCCCCCCCCC, frequency=null, 
				//onBoardFundsList=[OnBoardFunds [fundName=FUND1, frequency=D], OnBoardFunds [fundName=FUND2, frequency=W], OnBoardFunds [fundName=FUND3, frequency=D,W,M], OnBoardFunds [fundName=FUND4, frequency=null]]]
				
				onBordService.addOnboardDetails(onBordDto);
				//model.addAttribute("onBordDto",onBordDto);
				//return "OnBord1";
				
				//System.out.println("link = /OnBordedDetails/client/"+onBordDto.getClientId()+"/riskAggregator/"+onBordDto.getRiskAggregatorId());
				//return "redirect:/OnBordedDetails/client/"+onBordDto.getClientId()+"/riskAggregator/"+onBordDto.getRiskAggregatorId();
				return "redirect:/getFund/"+onBordDto.getRiskAggregatorId()+"/"+onBordDto.getClientId();
			}
						
			/*
			 * if(errors.hasErrors()) { System.out.println("ERROR accoured");
			 * 
			 * return "OnBord"; //return "redirect:/AddOnBordDetails";
			 * 
			 * }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
    
    @GetMapping("/OnBordFund")
    public String OnBordFund(OnBordDto onBordDto) { 
    	onBordDto.setOnBoardForm("onBoardForm1");
    	return "OnBord1";
    }

    @GetMapping("/OnBordFund/{riskAggregatorId}/{clientId}")
    public String OnBordFund(OnBordDto onBordDto,@PathVariable Integer riskAggregatorId,@PathVariable Integer clientId,Model model) { 
    	ClientTable clientTable = clientService.findById(clientId);
    	RiskAggregator riskAggregator = riskAggregatorService.findById(riskAggregatorId);
    	onBordDto.setClientId(clientTable.getClientID());
    	onBordDto.setClientName(clientTable.getClientShortName());
    	onBordDto.setRiskAggregatorId(riskAggregator.getRiskAggregatorId());
    	onBordDto.setRiskAggregatorName(riskAggregator.getRiskAggregatorName());
    	onBordDto.setOnBoardForm("OnBordFundForm");
    	return "OnBord1";
    }
        
    @GetMapping("/OnBordedDetails/client/{clientId}/riskAggregator/{riskAggregatorId}")
    public String getOnBordedDetails(@PathVariable Integer clientId,@PathVariable Integer riskAggregatorId ,Model model) {    
    	ClientTable theClientTable = clientService.findById(clientId);
    	RiskAggregator theAggregator = riskAggregatorService.findById(riskAggregatorId);
    	List<ClientOnboardTable> clientOnboardList= theClientOnboardService.findByClientAndRiskAggregator(theClientTable, theAggregator);
    	System.out.println("clientOnboardList >> "+clientOnboardList);
    	model.addAttribute("clientOnboardList", clientOnboardList);
    	return "OnBordDetails";
    }
    
    @GetMapping("/getOnBordedDetails")
	 public String getDevelopmentDetails(Model model) {    
	    	//ClientTable theClientTable = clientService.findById(clientId);
	    	//RiskAggregator theAggregator = riskAggregatorService.findById(riskAggregatorId);
	    	List<TestDto> clientOnboardList= theClientOnboardService.getClientOnBoardBillDetails();  	    	
	    	model.addAttribute("clientOnboardList", clientOnboardList);	    	
	    	return "OnBordDetails";
	    }
    
    @GetMapping("/getClientsOFRisKAggregator/{id}")
    public String getFundById(@PathVariable Integer id,Model model) {    
    	RiskAggregator theAggregator = riskAggregatorService.findById(id);
    	//List<ClientOnboardTable> clientOnboardList= theClientOnboardService.getClientsOfRiskAggregator(theAggregator);
    	List<TestDto> clientOnboardList=	theClientOnboardService.findByRiskAggregator2(theAggregator.getRiskAggregatorId());
    	List<ClientTable> clientList=new ArrayList<ClientTable>();
    	for(TestDto c : clientOnboardList) {
    		//System.out.println(c.getClientID()+">>"+c.getClientName());
    		ClientTable c2 = new ClientTable();
    		c2.setClientID(c.getClientID());
    		c2.setClientShortName(c.getClientName());
    		c2.setModified_date(c.getSetUpDate());
    		clientList.add(c2);    		
    	}
    	
    	model.addAttribute("riskAggregator", theAggregator.getRiskAggregatorName());
    	model.addAttribute("riskAggregatorId", theAggregator.getRiskAggregatorId());
    	model.addAttribute("clients", clientList);    	
    	return "client";
    }
    
    // example for multiple inputs
	/*
	 * @RequestMapping(value="/owners/{ownerId}/pets/{petId}",
	 * method=RequestMethod.GET) public String findPet(@PathVariable String
	 * ownerId, @PathVariable String petId, Model model) { Owner owner =
	 * ownerService.findOwner(ownerId); Pet pet = owner.getPet(petId);
	 * model.addAttribute("pet", pet); return "displayPet"; }
	 */
    
    @GetMapping("/editClientOnboard")
   	public String editClientOnboard(@RequestParam("ClientOnboardId") int clientOnboardId, Model model) {   		
       	System.out.println("Inside editClientOnboard"+clientOnboardId);
   		// delete the fund
       	ClientOnboardTable clientOnboardTable = theClientOnboardService.findById(clientOnboardId);
       	//clientOnboardTable.setDateTime(LocalDateTime.now());
       	model.addAttribute("clientOnboardTable",clientOnboardTable);
       	model.addAttribute("riskAggregatorName",clientOnboardTable.getRiskAggregator().getRiskAggregatorName());
       	model.addAttribute("clientShortName",clientOnboardTable.getClient().getClientShortName());
       	model.addAttribute("fundShortName",clientOnboardTable.getFund().getFundShortName());       	
   		// redirect to /getFund/{}
       	//return "redirect:/getFund/"+clientOnboardTable.getRiskAggregator().getId()+"/"+clientOnboardTable.getClient().getClientID();
   		return "editFundDetails";
   	}
    
    @PostMapping("/editClientOnboard")
   	public String editClientOnboard(  @ModelAttribute("clientOnboardTable") ClientOnboardTable clientOnboardTable, Model model) {
    	System.out.println("Inside editClientOnboard"+clientOnboardTable.getClientOnboardId());
    	clientOnboardTable.setModified_date(LocalDate.now());
	   	theClientOnboardService.save(clientOnboardTable);
		 return "redirect:/getFund/"+clientOnboardTable.getRiskAggregator().getRiskAggregatorId()+"/"+clientOnboardTable.getClient().getClientID();
		     	//return "editFundDetails";
    }
    
      
}
