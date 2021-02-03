package com.globeop.riskfeed.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.globeop.riskfeed.entity.FtpServerDetails;
import com.globeop.riskfeed.entity.OnBordDto;
import com.globeop.riskfeed.service.FtpService;
import com.globeop.riskfeed.service.PageServiceHelper;
import com.globeop.riskfeed.validator.OnBordValidator;

@Controller
public class FtpController {

	
    @Autowired
    private OnBordValidator onBordValidator;
    
    @Autowired
    private FtpService theFtpService;
    
    @Autowired
    private PageServiceHelper thePageServiceHelper;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
       binder.addValidators(onBordValidator);
    }

    @GetMapping("/AddFtp")
    public String showOnBordForm2(OnBordDto onBordDto) {  
    	onBordDto.setOnBoardForm("ftp-form");
    	return "ftp-form";
    }
   
    

    @PostMapping("/AddFtp")
	public String addFtpDetails( @ModelAttribute("onBordDto") @Valid  OnBordDto onBordDto, Errors errors, Model model, RedirectAttributes redirectAttributes) {					
		if(errors.hasErrors()) {
			System.out.println("ERROR accoured");				       				
			return "ftp-form";								
		}else{
			//System.out.println(onBordDto);
			String result=theFtpService.addCompleteFtpDetails(onBordDto);
			if("Error".equalsIgnoreCase(result)) {
				errors.rejectValue("Error", "OnBordDto.addFtp.error");
				return "ftp-form";
				
			}else {									
				redirectAttributes.addFlashAttribute("message", "Details Added Successfully");
				return "redirect:/getFtpDetails/"+result;
			}
		}		    	
    }    

    @GetMapping({"/getFtpDetails2","/getFtpDetails2/{id}"})
    public String GetFtpDetails(@PathVariable(required = false) Integer id,Model model) {      	
    	List<FtpServerDetails> ftpDetails = new ArrayList<FtpServerDetails>();
    	//System.out.println(" ID *************"+id);    	
    	if(null != id) {
    		ftpDetails.removeAll(ftpDetails);
    		ftpDetails.add(theFtpService.findByFtpServerDetailsId(id));
    	}else {
    		ftpDetails=theFtpService.findAllFtpServerDetails();
    	}
    	model.addAttribute("ftpDetails",ftpDetails);    	
    	return "ftpDetails";
    	//return "redirect:/ftp-form";
    }
    
    @GetMapping({"/getFtpDetails", "/getFtpDetails/{id}" })
	public String viewHomePage(@PathVariable(required = false) String id,Model model) {	
    	//0 and 10 are initial page and default size    	
    	Page page=thePageServiceHelper.getDetails("ftpDetails",id,0, "ftpDetailID","asc","",10);		
		return thePageServiceHelper.commonMethod("ftpDetails",id,1,"ftpDetailID", "asc","",10,page,model);
	}
 
    @GetMapping("/getFtpDetails/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			@PathVariable(required = false) String id, String url,
			@RequestParam (value="records", required=false, defaultValue="10") String records,
			@RequestParam ("keyword") String keyword,
			Model model) {
		int pageSize = Integer.parseInt(records);			
		Page page=thePageServiceHelper.getDetails("ftpDetails",id,pageNo, sortField,sortDir,keyword,pageSize);
		return thePageServiceHelper.commonMethod("ftpDetails",id,pageNo,sortField,sortDir,keyword,pageSize,page,model);
		}    
}
