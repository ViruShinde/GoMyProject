package com.globeop.riskfeed.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RiskfeedErrorController implements ErrorController  {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    model.addAttribute("status",status);
	     
	    
	    /*
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "error-404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "error-500";
	        }
	    }
	    */
	    return "error";
	}

    @Override
    public String getErrorPath() {
        return null;
    }
}