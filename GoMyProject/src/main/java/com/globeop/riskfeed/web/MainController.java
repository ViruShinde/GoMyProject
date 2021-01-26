package com.globeop.riskfeed.web;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.globeop.risk.web.util.RecordNotFoundException;
import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.FundTable;
import com.globeop.riskfeed.entity.OnBordDto;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.service.ClientOnboardService;
import com.globeop.riskfeed.service.ClientService;
import com.globeop.riskfeed.service.DatabasedetailsService;
import com.globeop.riskfeed.service.FundService;
import com.globeop.riskfeed.service.OnBordService;
import com.globeop.riskfeed.service.PageServiceHelper;
import com.globeop.riskfeed.service.RiskAggregatorService;

@Controller
public class MainController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private OnBordService onBordService;

	@Autowired
	private FundService fundService;
	
	@Autowired
	private RiskAggregatorService riskAggregatorService;

	@Autowired
	private ClientOnboardService theClientOnboardService;
	
	@Autowired
	private DatabasedetailsService theDatabasedetailsService;
	
    @GetMapping("/")
    public String root(Model model) {    	
    	List<TestDto> overAllDetails = theClientOnboardService.getOverAllDetails(); 
    	model.addAttribute("overAllDetails", overAllDetails);    	
        return "index";
    }
    
    // To get the same NAV bar for all pages 
    @GetMapping({ "/Nav", "/Test/NAV" })
    public String Nav() {
        return "Nav";
    }

    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("date",new Date());
        //return "login";
    	return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    
    @GetMapping("/mis-log")
    public String misLog() {
        return "misLog";
    }

    
    
    @RequestMapping("/Home")
	public String home () {
		
		return "index";
		
	} 
    @RequestMapping("/Database")
	public String Database () {		
		return "database";		
	}
    
    @RequestMapping("/g2o-UAT")
	public String g2OUat (Model model) {	
    	model.addAttribute("database", "G2O");
    	model.addAttribute("env", "G2O-UAT");
    	model.addAttribute("servers", theDatabasedetailsService.findByEnvironment("G2O-UAT"));
		return "database";		
	}
    
    @RequestMapping("/g2o-PRD")
   	public String g2OPRD (Model model) {	
       	model.addAttribute("database", "G2O");
       	model.addAttribute("env", "G2O-PRD");
       	model.addAttribute("servers", theDatabasedetailsService.findByEnvironment("G2O-PRD"));
   		return "database";		
   	}
       
    
    @GetMapping("/Test")
    public String test() {
        return "Test";
    }
     
    
    @RequestMapping("/allDetails")
   	public String allDetails() {		
   		return "allDetails";		
   	} 

    
    @Autowired
    PageServiceHelper thePageServiceHelper;
    
    @GetMapping({"/getRiskAggregator", "/getRiskAggregator/{id}" })
 	public String getRiskaggregator(@PathVariable(required = false) String id,Model model) {	
     	//0 and 10 are initial page and default size    	
     	Page page=thePageServiceHelper.getDetails("riskAggregator",id,0, "riskAggregatorId","asc","",10);		
 		return thePageServiceHelper.commonMethod("riskAggregator",id,1,"riskAggregatorId", "asc","",10,page,model);
 	}
  
     @GetMapping("/getRiskAggregator/page/{pageNo}")
 	public String getRiskaggregatorPaginated(@PathVariable (value = "pageNo") int pageNo, 
 			@Param("sortField") String sortField,
 			@Param("sortDir") String sortDir,
 			@PathVariable(required = false) String id, String url,
 			@RequestParam (value="records", required=false, defaultValue="10") String records,
 			@RequestParam ("keyword") String keyword,
 			Model model) {
 		int pageSize = Integer.parseInt(records);			
 		Page page=thePageServiceHelper.getDetails("riskAggregator",id,pageNo, sortField,sortDir,keyword,pageSize);
 		return thePageServiceHelper.commonMethod("riskAggregator",id,pageNo,sortField,sortDir,keyword,pageSize,page,model);
 		}
    
    @GetMapping({"/getClient", "/getClient/{id}" })
	public String viewHomePage(@PathVariable(required = false) String id,Model model) {	
    	//0 and 10 are initial page and default size    	
    	Page page=thePageServiceHelper.getDetails("client",id,0, "clientID","asc","",10);		
		return thePageServiceHelper.commonMethod("client",id,1,"clientID", "asc","",10,page,model);
	}
 
    @GetMapping("/getClient/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			@PathVariable(required = false) String id, String url,
			@RequestParam (value="records", required=false, defaultValue="10") String records,
			@RequestParam ("keyword") String keyword,
			Model model) {
		int pageSize = Integer.parseInt(records);			
		Page page=thePageServiceHelper.getDetails("client",id,pageNo, sortField,sortDir,keyword,pageSize);
		return thePageServiceHelper.commonMethod("client",id,pageNo,sortField,sortDir,keyword,pageSize,page,model);
		}
    
 // Note here ID is ClientID and not FundID
 @GetMapping("/getFundByClient/{id}")
 public String getFundById(@PathVariable String id,Model model) { 
	 System.out.println("get funds "+id);
	Page page=thePageServiceHelper.getDetails("fund",id,0, "fundID","asc","",10);		
	return thePageServiceHelper.commonMethod("fund",id,1,"fundID", "asc","",10,page,model);
 	
 }
 
 @GetMapping({"/getFundByClient/page/{pageNo}", "/getFundByClient/{id}/page/{pageNo}" })
	public String findPaginatedFund(@PathVariable (value = "pageNo") int pageNo, 
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			@PathVariable(required = false) String id, String url,
			@RequestParam (value="records", required=false, defaultValue="10") String records,
			@RequestParam ("keyword") String keyword,
			Model model) {
		int pageSize = Integer.parseInt(records);		
		Page page=thePageServiceHelper.getDetails("fund",id,pageNo, sortField,sortDir,keyword,pageSize);
		return thePageServiceHelper.commonMethod("fund",id,pageNo,sortField,sortDir,keyword,pageSize,page,model);
	}



    // return form to add new client
    @GetMapping("/AddClient")
    public String showFormForAdd(Model model) {   
    	ClientTable clientTable = new ClientTable();
    	model.addAttribute("client", clientTable);       	
    	return "client-form";
    }
    
    // Add new client and return list of clients
    @RequestMapping(value="/AddClient", method=RequestMethod.POST)
	public String saveUpdateDetails (@ModelAttribute("client") ClientTable theClientTable,Model model) {		
		try {
			System.out.println(" >>> "+theClientTable.getClientShortName());
			theClientTable.setModified_date(LocalDate.now());
			theClientTable.setClientShortName(theClientTable.getClientShortName().toUpperCase());
			boolean result = clientService.checkClientAlreadyExist(theClientTable.getClientShortName().toUpperCase());
			if(result==false) {
				clientService.save(theClientTable);
			}else {
				model.addAttribute("message", "Client already exists");   
				return "client-form";
			}
			//clientService.save(theClientTable);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/getClient";
	}
    
      
 // return form to add new client
    @GetMapping("/AddRiskAggregator")
    public String riskAggregatorForm(Model model) {   
    	RiskAggregator riskAggregator = new RiskAggregator();
    	model.addAttribute("riskAggregator", riskAggregator);       	
    	return "riskAggregator-form";
    }
    
    // Add new client and return list of clients
    @RequestMapping(value="/AddRiskAggregator", method=RequestMethod.POST)
	public String saveRiskAggregator (@ModelAttribute("riskAggregator") RiskAggregator theRiskAggregator,Model model) {		
		try {
			System.out.println(" >>> "+theRiskAggregator);
			theRiskAggregator.setModified_date(LocalDate.now());
			theRiskAggregator.setRiskAggregatorName(theRiskAggregator.getRiskAggregatorName().toUpperCase());
			boolean result = riskAggregatorService.checkRiskAggregatorAlreadyExist(theRiskAggregator.getRiskAggregatorName().toUpperCase());			
			if(result==false) {
				riskAggregatorService.save(theRiskAggregator);
			}else {
				model.addAttribute("message", "RiskAggregator already exists");   
				return "riskAggregator-form";
			}
			//riskAggregatorService.save(theRiskAggregator);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/getRiskAggregator";
	}
         
    

	
	  // get OnBord page
	  
	  @GetMapping("/OnBordClient")
	  public String showOnBordForm(Model model) {
	  OnBordDto onBordDto = new OnBordDto(); model.addAttribute("onBordDto",
	  onBordDto); return "OnBord"; }
	 
	// get OnBord page
	@GetMapping("/TestOnBordDetails")
	public String testShowOnBordForm2(OnBordDto onBordDto) {       	    	
		return "OnBord";
	}    
	  
	  
    
    
    //Testing 
    @GetMapping("/OnBordClient2/{id}")
   	public String showOnBordForm2 (@ModelAttribute("OnBordDetails") OnBordDto onBordDto, @PathVariable Integer id,Model model) {		
   		try {   			
   			//onBordService.addDetails(onBordDto);
   			model.addAttribute("onBordDto", onBordDto);    
   		} catch (Exception e) {
   			e.printStackTrace();
   			return "error";
   		}
       	
       	//return "SaveUpdate";
   		//return "redirect:/getClient";
   		return "OnBord";
   	}
    
    
    
    // return form to add new funds
    @GetMapping("/AddFunds")
    public String addFunds(Model model) {       	
    	OnBordDto onBordDto = new OnBordDto();
    	model.addAttribute("onBordDto", onBordDto);           	
    	return "fund-form";
    }
    
 // Add new funds and return list of funds
    @RequestMapping(value="/AddFunds", method=RequestMethod.POST)
	public String addFunds (@ModelAttribute("fund") OnBordDto onBordDto, RedirectAttributes redirectAttributes) {		
		try {
			//System.out.println(theFundTable.getClientID()+" >>> "+theFundTable.getFundShortName());
			//theFundTable.setModified_date(new Date());
			//fundService.update(theFundTable);
			List duplicateList = onBordService.addFundDetails(onBordDto);
			if(duplicateList.size() > 0) {
				//System.out.println(duplicateList);
				redirectAttributes.addFlashAttribute("message", "Fund Already exists : "+duplicateList);
			}
			//System.out.println(onBordDto);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/getFund/"+onBordDto.getClientId();
	}
    
    
    //@DeleteMapping("/deleteFund/{id}")
	/*
	 * @PostMapping("/delete") public String
	 * deleteEmployeeById(@RequestParam("fundId") int theId) throws
	 * RecordNotFoundException { fundService.deleteById(theId); return
	 * "redirect:/getFund/"+theId; }
	 */
    
    @GetMapping("/deleteFund")
	public String deleteFund(@RequestParam("fundID") int theFundId,@RequestParam("clientID") int theClientId) {
		
    	System.out.println("Inside delete funds");
		// delete the fund
    	
    	fundService.deleteById(theFundId);
		
		// redirect to /getFund/{}
    	return "redirect:/getFund/"+theClientId;
		
	}
}
