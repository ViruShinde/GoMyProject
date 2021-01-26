package com.globeop.riskfeed.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OnBoardPageableService extends PageableService {
	
	public Page getDetails(Pageable pageable,String requestFor,String riskAggregatorId,String clientId, int pageNo,String sortField,String sortDir,String keyword, int recordSize) ;

}
