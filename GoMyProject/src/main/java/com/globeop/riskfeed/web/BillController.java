package com.globeop.riskfeed.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.globeop.riskfeed.entity.BillTable;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.DevelopmentTable;
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
	public String uploadFile(@ModelAttribute("onBordDto") @Valid  OnBordDto onBordDto, Errors errors, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3, Model model) {
	 
		 //System.out.println(onBordDto);
		 
		if(errors.hasErrors()) {
				System.out.println("ERROR accoured");				       				
				return "billForm";								
			}else {
				theBillService.saveDetails(onBordDto, file1,file2,file3);
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
}