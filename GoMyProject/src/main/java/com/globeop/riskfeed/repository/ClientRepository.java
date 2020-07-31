package com.globeop.riskfeed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.entity.ClientTable;

//@RepositoryRestResource(path ="clients")
public interface ClientRepository extends JpaRepository<ClientTable, Integer> {

	public List<ClientTable> findByClientShortName(String clientShortName);
	
	public List<ClientTable> findByClientShortNameStartingWith(String clientShortName);
	
	public List<ClientTable> findByClientShortNameLike(String clientShortName);
	
	@Query(value= "SELECT NEW com.globeop.riskfeed.dto.TestDto(c.clientID) from ClientTable AS c ")
	public List<TestDto> testQuery();
}
