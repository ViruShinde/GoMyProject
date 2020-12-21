package com.globeop.riskfeed.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globeop.riskfeed.dto.ResponseDto;
import com.globeop.riskfeed.dto.SchedularDto;
import com.globeop.riskfeed.entity.ScheduledDetails;
import com.globeop.riskfeed.service.ClientService;
import com.globeop.riskfeed.service.FundService;
import com.globeop.riskfeed.service.RiskAggregatorService;
import com.globeop.riskfeed.service.ScheduledDetailsService;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@Controller
public class ScheduledDetailsController {

	@Autowired
	ScheduledDetailsService theScheduledDetailsService;
	
	@Autowired
	RiskAggregatorService theRiskAggregatorService;
	
	@Autowired
	ClientService theClientService;
	
	@Autowired
	FundService theFundService;
		
	@GetMapping("/scheduler")
    public String login(Model model) {
    	model.addAttribute("date",new Date());
    	return "scheduler";
    }
	
   @GetMapping("/AddScheduler")
   public String showOnBordForm2(ScheduledDetails scheduledDetails) {     	
    	return "scheduler-form";
   }
   
   @Autowired
   RestTemplate restTemplate;

   @RequestMapping(value = "/AddScheduler", method = RequestMethod.POST)
   public String AddScheduler(@ModelAttribute("scheduledDetails") ScheduledDetails scheduledDetails, Model model,RedirectAttributes redirectAttributes) {
	  //ScheduledDetails scheduledDetails = theScheduledDetailsService.getScheduledDetailsFromOnBordDto(onBordDto);
	  ObjectMapper objectMapper = new ObjectMapper();
	  ResponseDto responseDto = new ResponseDto();
	  System.out.println("Inside AddScheduler method "+scheduledDetails);
     
	  String response="";
	  
	  HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<ScheduledDetails> entity = new HttpEntity<ScheduledDetails>(scheduledDetails,headers);
      
      response = restTemplate.exchange(
         "http://localhost:8082/schedular/addScheduladDetails", HttpMethod.POST, entity, String.class).getBody();
      try {
		responseDto = objectMapper.readValue(response, ResponseDto.class);
	} catch (JsonParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      model.addAttribute("responseDto",responseDto);
      if("False".equalsIgnoreCase( responseDto.getError()) ) {    	  
          return "scheduler-form";  
      }else {									
			redirectAttributes.addFlashAttribute("message", "Details Added Successfully");
			return "redirect:/getscheduledetails/"+responseDto.getId();
      }
      
   }   	
	 
	 @GetMapping({"/getscheduledetails","/getscheduledetails/{id}"})
		public String viewHomePage(@PathVariable(required = false) String id,Model model) {		 
		 	System.out.println("getscheduledetails>>>>>>>>>>>>>"+id+"<<" );
			return findPaginated(1, "scheduledDetailsId", "asc", id, "getscheduledetails", model);		
		}
	 
	 @GetMapping("/getscheduledetails/page/{pageNo}")
		public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir, String id, String url,
				Model model) {
			int pageSize = 5;
			if(null==url || "".equals(url)) {
				url="page";
			}
			
			
			Page<SchedularDto> page = theScheduledDetailsService.findPaginated(id,pageNo, pageSize, sortField, sortDir,url );
			return commonMethod(page, model, pageNo, pageSize, sortField, sortDir);
			
			/*
			List<SchedularDto> listScheduledDetails = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			
			model.addAttribute("listScheduledDetails", listScheduledDetails);
			return "scheduleDetails";
			*/
		}
	 
	 @GetMapping("/getscheduledetails/search")
		public String search( @Param("keyword") String keyword, Model model) {
		 keyword=keyword.trim();		 
		 Page<SchedularDto> page = theScheduledDetailsService.getscheduledetailsSearch(keyword);	
		 model.addAttribute("keyword",keyword);
		 return commonMethod(page, model, 0, 5, "scheduledDetailsId", "asc");
		 //return null;
	 }
	 
	 public String commonMethod(Page<SchedularDto> page, Model model, int pageNo, int pageSize, String sortField, String sortDir ) {
		 
		 List<SchedularDto> listScheduledDetails = page.getContent();			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");			
			model.addAttribute("listScheduledDetails", listScheduledDetails);
			return "scheduleDetails";
	 }
	 
	 
	 @GetMapping("/editscheduledDetails/{id}")
	    public String editscheduledDetails(@PathVariable (value = "id") int id,Model model) {	    
		 	ScheduledDetails scheduledDetails = theScheduledDetailsService.findById(id); // need to get the details from restemplate
		 	
		 	model.addAttribute("scheduledDetails",scheduledDetails);		 	
		 	model.addAttribute("riskAggregatorName",theRiskAggregatorService.findById(scheduledDetails.getRiskAggregatorId()).getRiskAggregatorName().toString() );
	       	model.addAttribute("clientShortName",theClientService.findById(scheduledDetails.getClientId()).getClientShortName().toString());
	       	model.addAttribute("fundShortName",theFundService.findById(scheduledDetails.getFundId()).getFundShortName().toString());      
		 	return "editScheduleDetails";	    	
	   }
	 
	 @GetMapping("/deletescheduledDetails/{id}")
	 //@DeleteMapping("/deletescheduledDetails/{id}")
	 public String deletscheduledDetails(@PathVariable (value = "id") int id,Model model,RedirectAttributes redirectAttributes) {	    
		 theScheduledDetailsService.deleteById(id);
		 redirectAttributes.addFlashAttribute("message", "Details Deleted Successfully");
		 return "redirect:/getscheduledetails";
	 }
}
