package com.globeop.riskfeed.web;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.globeop.riskfeed.dto.RiskFileDto;
import com.globeop.riskfeed.service.RiskFileService;
import com.globeop.riskfeed.validator.RiskFileValidator;

@Controller
public class RiskFileController {
	
	@Autowired
	private RiskFileValidator riskFileValidator;
	    
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	     binder.addValidators(riskFileValidator);
	}
	
	@Autowired
	RiskFileService theRiskFileService;

	@GetMapping("/generateRiskFile")
    public String generateRiskFile(Model model) {
		RiskFileDto riskFileDto = new RiskFileDto();
		riskFileDto.setOnBoardForm("riskFileForm");
		model.addAttribute("riskFileDto",riskFileDto);
		
    	return "generateRiskFeed";
    }

	@PostMapping("/generateRiskFile") // @ModelAttribute("onBordDto") @Valid  OnBordDto onBordDto, Errors errors, Model model
    public ResponseEntity<Resource> generateRiskFile2( @ModelAttribute("riskFileDto")  @Valid RiskFileDto riskFileDto,Errors errors, Model model) {
		System.out.println(">>>>>>>> "+riskFileDto);
		if(errors.hasErrors()) {
			System.out.println("ERROR accoured");					
			//return "generateRiskFeed";								
		}else{
			theRiskFileService.generateRiskFile(riskFileDto);				
		}		
		//System.out.println(">>>>>>>>  "+riskFileDto);
		Path path = Paths.get(riskFileDto.getFilePath());
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				//.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);		
    }
		

}
