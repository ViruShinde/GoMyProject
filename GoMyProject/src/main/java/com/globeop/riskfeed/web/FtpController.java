package com.globeop.riskfeed.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.globeop.riskfeed.entity.FtpServerDetails;
import com.globeop.riskfeed.entity.OnBordDto;
import com.globeop.riskfeed.service.FtpService;
import com.globeop.riskfeed.validator.OnBordValidator;

@Controller
public class FtpController {

	
    @Autowired
    private OnBordValidator onBordValidator;
    
    @Autowired
    private FtpService theFtpService;
    
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

    @GetMapping({"/getFtpDetails","/getFtpDetails/{id}"})
    public String GetFtpDetails(@PathVariable(required = false) Integer id,Model model) {      	
    	List<FtpServerDetails> ftpDetails=theFtpService.findAllFtpServerDetails();
    	System.out.println(" ID *************"+id);    	
    	if(null != id) {
    		ftpDetails.removeAll(ftpDetails);
    		ftpDetails.add(theFtpService.findByFtpServerDetailsId(id));
    	}
    	model.addAttribute("ftpDetails",ftpDetails);    	
    	return "ftpDetails";
    	//return "redirect:/ftp-form";
    }
}
