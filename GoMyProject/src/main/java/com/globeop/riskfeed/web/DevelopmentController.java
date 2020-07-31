package com.globeop.riskfeed.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.DevelopmentTable;
import com.globeop.riskfeed.entity.OnBordDto;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.service.ClientService;
import com.globeop.riskfeed.service.DevelopmentService;
import com.globeop.riskfeed.service.RiskAggregatorService;
import com.globeop.riskfeed.validator.OnBordValidator;

@Controller
public class DevelopmentController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private RiskAggregatorService riskAggregatorService;
	
	@Autowired
	private DevelopmentService developmentService;
	
	 @Autowired
	 private OnBordValidator onBordValidator;
	    
	 @InitBinder
	 protected void initBinder(WebDataBinder binder) {
		 binder.addValidators(onBordValidator);
	 }
	    
	 @GetMapping("/AddDevelopmentDetails")
	    public String showDevelopmentDetailsForm(OnBordDto onBordDto) {  
		 	onBordDto.setOnBoardForm("developmentForm");
	    	return "developmentForm";
	    }
	 
	 
	 @PostMapping("/AddDevelopmentDetails")
	 //public String uploadFile(@RequestParam("file") MultipartFile file) {
	 public String uploadFile(@ModelAttribute("onBordDto") @Valid OnBordDto onBordDto, Errors errors, @RequestParam("file") MultipartFile file, Model model) {
		 
		 //System.out.println(onBordDto);
		 
		 if(errors.hasErrors()) {
			 System.out.println("ERROR accoured");	
			 if(file.isEmpty())				 
			 	errors.rejectValue("waiverMail", "OnBordDto.waivedOffMail.empty");
			 return "developmentForm";								
		 }else if(file.isEmpty()){
			 System.out.println("ERROR accoured");
			 errors.rejectValue("waiverMail", "OnBordDto.waivedOffMail.empty");
			 return "developmentForm";
		 }else {
			 developmentService.saveDetails(onBordDto, file);
		 }

		 
		//return "redirect:/AddDevelopmentDetails";
		return "redirect:/DevelopmentDetails/client/"+onBordDto.getClientId()+"/riskAggregator/"+onBordDto.getRiskAggregatorId();
		 
		/*
		 * String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		 * .path("/downloadFile/") .path(developmentTable.getDevelopmentId()+"")
		 * .toUriString();
		 */

		/*
		 * return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
		 * file.getContentType(), file.getSize());
		 */
	    }
	 
	// /DevelopmentDetails/client/3/riskAggregator/2
	 @GetMapping("/DevelopmentDetails/client/{clientId}/riskAggregator/{riskAggregatorId}")
	    public String getDevelopmentDetailsOfClient(@PathVariable Integer clientId,@PathVariable Integer riskAggregatorId ,Model model) {    
	    	ClientTable theClientTable = clientService.findById(clientId);
	    	RiskAggregator theAggregator = riskAggregatorService.findById(riskAggregatorId);
	    	List<DevelopmentTable> developmentList= developmentService.findByClientAndRiskAggregator(theClientTable, theAggregator);  
	    	List<DevelopmentTable> developmentList2=new ArrayList<DevelopmentTable>();
	    	for(DevelopmentTable  dev : developmentList) {
	    		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	    				  .path("/downloadFile/") .path(dev.getDevelopmentId()+"")
	    				  .toUriString();	    		
	    		//String fileDownloadUri="/downloadFile/"+dev.getDevelopmentId();
	    		DevelopmentTable devTable=dev;
	    		devTable.setDownloadUrl(fileDownloadUri);	    		
	    		developmentList2.add(devTable);
	    	}	    		  		    	
	    	model.addAttribute("developmentList", developmentList2);
	    	return "developmentDetails";
	    }
	 
	 @GetMapping("/getDevelopmentDetails")
	 public String getDevelopmentDetails(Model model) {    
	    	//ClientTable theClientTable = clientService.findById(clientId);
	    	//RiskAggregator theAggregator = riskAggregatorService.findById(riskAggregatorId);
	    	List<DevelopmentTable> developmentList= developmentService.findAll();  
	    	List<DevelopmentTable> developmentList2=new ArrayList<DevelopmentTable>();
	    	for(DevelopmentTable  dev : developmentList) {
	    		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	    				  .path("/downloadFile/") .path(dev.getDevelopmentId()+"")
	    				  .toUriString();	    		
	    		//String fileDownloadUri="/downloadFile/"+dev.getDevelopmentId();
	    		DevelopmentTable devTable=dev;
	    		devTable.setDownloadUrl(fileDownloadUri);	    		
	    		developmentList2.add(devTable);
	    	}	    		  		    	
	    	model.addAttribute("developmentList", developmentList);
	    	return "developmentDetails";
	    }
	 
	 @GetMapping("/downloadFile/{fileId}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable int fileId) {
	        // Load file from database
		   DevelopmentTable developmentTable=null;
	       try {
	    	   developmentTable = developmentService.getFile(fileId);
	    	   return ResponseEntity.ok()
		                .contentType(MediaType.parseMediaType(developmentTable.getFileType()))
		                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + developmentTable.getFileName() + "\"")
		                .body(new ByteArrayResource(developmentTable.getApprovalMail()));
	       } catch (Exception e) {
	    	   e.printStackTrace();
	       } 		   
	       return null;	       
	    }
	 
	 
	 
}
