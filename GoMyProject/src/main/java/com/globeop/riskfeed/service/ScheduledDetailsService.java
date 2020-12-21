package com.globeop.riskfeed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.globeop.riskfeed.dto.RestPageImpl;
import com.globeop.riskfeed.dto.SchedularDto;
import com.globeop.riskfeed.entity.ScheduledDetails;
import com.globeop.riskfeed.repository.ScheduledDetailsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

@Service
public class ScheduledDetailsService implements CommonService<ScheduledDetails> {

	@Autowired
	private ScheduledDetailsRepository theScheduledDetailsRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<ScheduledDetails> findAll() {		
		return theScheduledDetailsRepository.findAll();
	}

	@Override
	public ScheduledDetails findById(int theId) {
		
		Optional<ScheduledDetails> result = theScheduledDetailsRepository.findById(theId);		
		ScheduledDetails theScheduledDetails=null;
		
		if (result.isPresent()) {			
			theScheduledDetails = result.get();
		}
		else {
			throw new RuntimeException("Did not find client id - " + theId);
		}
		return theScheduledDetails;		
	}

	@Override
	public void save(ScheduledDetails obj) {
		theScheduledDetailsRepository.save(obj);
		
	}
	
		
	@Override
	public void deleteById(int theId) {
		theScheduledDetailsRepository.deleteById(theId);
		
	}
	
	public Page<SchedularDto> findPaginated(String id, int pageNo, int pageSize, String sortField, String sortDirection, String urlpath) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();		
		//Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		//Page<ScheduledDetails> findPaginated = this.theScheduledDetailsRepository.findAll(pageable);
		//System.out.println("@@@@@@@@@@@@@@@ "+id);
		List<SchedularDto> l = new ArrayList<SchedularDto>();
		String url="http://localhost:8082/schedular/getscheduledetails";
		if(urlpath.contains("getscheduledetails")) {
			url="http://localhost:8082/schedular/getscheduledetails";
			if( !(null==id || "".equals(id)) ) {
				url=url+"/"+id;
			}
		}else if(urlpath.contains("page")) {
			url="http://localhost:8082/schedular/getscheduledetails/page/"+pageNo+"?sortField="+sortField+"&sortDir="+sortDirection;
		}
				
		//System.out.println("URL "+url);
		/*
		ResponseEntity<SchedularDto[]> response = restTemplate.getForEntity(url,SchedularDto[].class);	
		
	    SchedularDto[] dto = response.getBody();
	    for(SchedularDto sdto : dto) {
	    	l.add(sdto);
	    }
	    */
	    //Page<ScheduledDetails> findPaginated  =  restTemplate.getForObject(url,Page.class);
		RestPageImpl findPaginated = restTemplate.getForObject(url, RestPageImpl.class);
	    //System.out.println("findPaginated.toString() >>>> "+findPaginated.getContent().toString());
	    //System.out.println(findPaginated.getTotalElements());
	    
	    //return new PageImpl<SchedularDto>(l);
	    return findPaginated;
	    
	    
			    
	}
	
	public Page<SchedularDto> getscheduledetailsSearch(String keyword) {
		
		String url="http://localhost:8082/schedular/getscheduledetails/search/?keyword="+keyword;		
		//Sort sort = "ASC".equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("scheduledDetailsId").ascending():Sort.by("scheduledDetailsId").descending();		
		//Pageable pageable = PageRequest.of(0, 5, sort);
		RestPageImpl findPaginated = restTemplate.getForObject(url, RestPageImpl.class);		
		return findPaginated;
	
	}
	
	
}
