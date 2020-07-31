package com.globeop.riskfeed.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.globeop.riskfeed.entity.BillTable;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.OnBordDto;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.service.BillService;
import com.globeop.riskfeed.service.ClientService;
import com.globeop.riskfeed.service.RiskAggregatorService;
import com.globeop.riskfeed.validator.OnBordValidator;

@Controller
public class BillController {

	@Autowired 	
	private BillService theBillService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private RiskAggregatorService riskAggregatorService;
	
	@Autowired
    private OnBordValidator onBordValidator;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
       binder.addValidators(onBordValidator);
    }
	
	@GetMapping("/Bill")
	public List<BillTable> getBills() {
		return theBillService.findAll();
	}
	
	@GetMapping("/AddBill")
	    public String showOnBordForm1(OnBordDto onBordDto) {  	    	
			onBordDto.setOnBoardForm("billForm");
	    	return "billForm";
	    }
	 
	@PostMapping("/AddBillDetails")
	 //public String uploadFile(@RequestParam("file") MultipartFile file) {
	public String uploadFile(@ModelAttribute("onBordDto") @Valid  OnBordDto onBordDto, Errors errors, @RequestParam("file1") MultipartFile waiverMail, @RequestParam("file2") MultipartFile clientApprovalMail, @RequestParam("file3") MultipartFile terminationMail, Model model) {
	 
		 //System.out.println(onBordDto);
		 
		if(errors.hasErrors()) {
				System.out.println("ERROR accoured");				       				
				return "billForm";								
			}else {
				theBillService.saveDetails(onBordDto, waiverMail,clientApprovalMail,terminationMail);
				return "redirect:/BillDetails/client/"+onBordDto.getClientId()+"/riskAggregator/"+onBordDto.getRiskAggregatorId();
			}		
	}
	
	
	@GetMapping("/BillDetails")
    public String getBillDetails(Model model) {    
    	List<BillTable> billList= theBillService.findAll();  	
    	List<BillTable> billList2=new ArrayList<BillTable>();
    	
    	for(BillTable  bill : billList) {
    		String WaiverMailDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    				  .path("/WaiverMail/") .path(bill.getBillId()+"")
    				  .toUriString();	
    		String ClientApprovalMailUri = ServletUriComponentsBuilder.fromCurrentContextPath()
  				  .path("/ClientApprovalMail/") .path(bill.getBillId()+"")
  				  .toUriString();	
    		String TerminationMaiUri = ServletUriComponentsBuilder.fromCurrentContextPath()
  				  .path("/TerminationMail/") .path(bill.getBillId()+"")
  				  .toUriString();	
    		//String fileDownloadUri="/downloadFile/"+dev.getDevelopmentId();
    		BillTable billTable=bill;
    		billTable.setWaiverMailUrl(WaiverMailDownloadUri);
    		billTable.setClientApprovalMailUrl(ClientApprovalMailUri);
    		billTable.setTerminationMailUrl(TerminationMaiUri);
    		billList2.add(billTable);	
    		//developmentList2.add(devTable);
    	}	    		  		    	
    	model.addAttribute("billList", billList2);
    	return "billDetails";
    }
	
	@GetMapping("/BillDetails/client/{clientId}/riskAggregator/{riskAggregatorId}")
    public String getDevelopmentDetailsOfClient(@PathVariable Integer clientId,@PathVariable Integer riskAggregatorId ,Model model) {    
    	ClientTable theClientTable = clientService.findById(clientId);
    	RiskAggregator theAggregator = riskAggregatorService.findById(riskAggregatorId);
    	List<BillTable> billList= theBillService.findByClientAndRiskAggregator(theClientTable, theAggregator);  
    	
    	
    	List<BillTable> billList2=new ArrayList<BillTable>();
    	
    	for(BillTable  bill : billList) {
    		String WaiverMailDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    				  .path("/WaiverMail/") .path(bill.getBillId()+"")
    				  .toUriString();	
    		String ClientApprovalMailUri = ServletUriComponentsBuilder.fromCurrentContextPath()
  				  .path("/ClientApprovalMail/") .path(bill.getBillId()+"")
  				  .toUriString();	
    		String TerminationMaiUri = ServletUriComponentsBuilder.fromCurrentContextPath()
  				  .path("/TerminationMail/") .path(bill.getBillId()+"")
  				  .toUriString();	
    		//String fileDownloadUri="/downloadFile/"+dev.getDevelopmentId();
    		BillTable billTable=bill;
    		billTable.setWaiverMailUrl(WaiverMailDownloadUri);
    		billTable.setClientApprovalMailUrl(ClientApprovalMailUri);
    		billTable.setTerminationMailUrl(TerminationMaiUri);
    		billList2.add(billTable);	
    		//developmentList2.add(devTable);
    	}	    		  		    	
    	model.addAttribute("billList", billList2);
    	return "billDetails";
    }
	
	@GetMapping("/ClientApprovalMail/{fileId}")
    public ResponseEntity<Resource> downloadClientApprovalMail(@PathVariable int fileId) {
        // Load file from database
	  BillTable billTable=null;
       try {
    	   billTable = theBillService.getFile(fileId);
    	   return ResponseEntity.ok()
	                //.contentType(MediaType.parseMediaType(billTable.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + billTable.getClientApprovalMailName() + "\"")
	                .body(new ByteArrayResource(billTable.getClientApprovalMail()));
       } catch (Exception e) {
    	   e.printStackTrace();
       } 		   
       return null;	       
    }
	
	@GetMapping("/WaiverMail/{fileId}")
    public ResponseEntity<Resource> downloadWaiverMail(@PathVariable int fileId) {
        // Load file from database
	  BillTable billTable=null;
       try {
    	   billTable = theBillService.getFile(fileId);
    	   return ResponseEntity.ok()
	                //.contentType(MediaType.parseMediaType(billTable.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + billTable.getWaiverFileName() + "\"")
	                .body(new ByteArrayResource(billTable.getWaiverMail()));
       } catch (Exception e) {
    	   e.printStackTrace();
       } 		   
       return null;	       
    }
	
	@GetMapping("/TerminationMail/{fileId}")
    public ResponseEntity<Resource> downloadTerminationMail(@PathVariable int fileId) {
        // Load file from database
	  BillTable billTable=null;
       try {
    	   billTable = theBillService.getFile(fileId);
    	   return ResponseEntity.ok()
	                //.contentType(MediaType.parseMediaType(billTable.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + billTable.getTerminationMailName() + "\"")
	                .body(new ByteArrayResource(billTable.getTerminationMail()));
       } catch (Exception e) {
    	   e.printStackTrace();
       } 		   
       return null;	       
    }
	
	
	@GetMapping("/editBillDetails")
   	public String editBillDetails(@RequestParam("billId") int billId, Model model) {
		BillTable billTable= theBillService.findById(billId);		
		model.addAttribute("clientId",billTable.getClient().getClientID());
       	model.addAttribute("clientShortName",billTable.getClient().getClientShortName());
       	model.addAttribute("riskAggregatorId",billTable.getRiskAggregator().getRiskAggregatorId());
       	model.addAttribute("riskAggregatorName",billTable.getRiskAggregator().getRiskAggregatorName());
       	model.addAttribute("billTable",billTable);
       	    
    	return "editBillDetails";
    }
	
	@PostMapping("/editBillDetails")
   	public String editBillDetails(@ModelAttribute("billTable")  BillTable billTable, Errors errors, @RequestParam("file1") MultipartFile waiverMail, @RequestParam("file2") MultipartFile clientApprovalMail, @RequestParam("file3") MultipartFile terminationMail, Model model) {
		System.out.println("INSIDE POST METHOD" +billTable.getBillId() + " === "+"redirect:/BillDetails/client/"+billTable.getClient().getClientID()+"/riskAggregator/"+billTable.getRiskAggregator().getRiskAggregatorId());
		System.out.println(billTable);
		System.out.println(model.toString());
		System.out.println(billTable.getClient().getClientID() + " >> "+billTable.getRiskAggregator().getRiskAggregatorId() );
	
		
		/*
		 * BillTable billTable2 = theBillService.findById(billTable.getBillId());
		 * billTable2.setSetupFee(billTable.getSetupFee());
		 * billTable2.setMonthlyFee(billTable.getMonthlyFee());
		 * billTable2.setDevlopementFee(billTable.getDevlopementFee());
		 * billTable2.setIsClientPayingOldCharges(billTable.getIsClientPayingOldCharges(
		 * )); billTable2.setIsWaivedOff(billTable.getIsWaivedOff());
		 * 
		 * billTable2.setBillStartDate(billTable.getBillStartDate());
		 * billTable2.setBillEndDate(billTable.getBillEndDate());
		 * billTable2.setCrmName(billTable.getCrmName());
		 * billTable2.setCrmailID(billTable.getCrmailID());
		 * billTable2.setBillingComments(billTable.getBillingComments());
		 * billTable2.setGoCheckNoteId(billTable.getGoCheckNoteId());
		 */
		  
		  theBillService.updateDetails(billTable, waiverMail, clientApprovalMail, terminationMail);
		 		
		return "redirect:/BillDetails/client/"+billTable.getClient().getClientID()+"/riskAggregator/"+billTable.getRiskAggregator().getRiskAggregatorId();
		//return "redirect:/BillDetails";
	}
	
	/*
	 * @PostMapping("/editClientOnboard") public String
	 * editClientOnboard( @ModelAttribute("clientOnboardTable") ClientOnboardTable
	 * clientOnboardTable, Errors errors, Model model) {
	 * System.out.println("INSIDE POST METHOD"
	 * +clientOnboardTable.getClientOnboardId()); ClientOnboardTable
	 * clientOnboardTable2 =
	 * theClientOnboardService.findById(clientOnboardTable.getClientOnboardId());
	 * clientOnboardTable2.setIsActive(clientOnboardTable.getIsActive());
	 * clientOnboardTable2.setAutomationProcess(clientOnboardTable.
	 * getAutomationProcess());
	 * clientOnboardTable2.setFrequency(clientOnboardTable.getFrequency());
	 * clientOnboardTable2.setComments(clientOnboardTable.getComments());
	 * theClientOnboardService.save(clientOnboardTable2);
	 * 
	 * return
	 * "redirect:/getFund/"+clientOnboardTable2.getRiskAggregator().getId()+"/"+
	 * clientOnboardTable2.getClient().getClientID(); //return "editFundDetails"; }
	 */
	    
	 
}