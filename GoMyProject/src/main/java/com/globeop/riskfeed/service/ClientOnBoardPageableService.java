package com.globeop.riskfeed.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class ClientOnBoardPageableService implements PageableService {
	
	public abstract Page getDetails(Pageable pageable,String requestFor,String riskAggregatorId,String clientId, int pageNo,String sortField,String sortDir,String keyword, int recordSize) ;

}
