package com.globeop.riskfeed.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.entity.ClientTable;

//@RepositoryRestResource(path ="clients")
public interface ClientRepository extends JpaRepository<ClientTable, Integer> {

	public List<ClientTable> findByClientShortName(String clientShortName);
	
	public List<ClientTable> findByClientShortNameStartingWith(String clientShortName);
	
	public List<ClientTable> findByClientShortNameLike(String clientShortName);
	
	@Query(value= "SELECT NEW com.globeop.riskfeed.dto.TestDto(c.clientID) from ClientTable AS c ")
	public List<TestDto> testQuery();
	
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.ClientTable(c.clientID, c.clientShortName, c.modified_date) from ClientTable AS c ")
	public Page<ClientTable> findAllPageable(Pageable pageable);

	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.ClientTable(c.clientID, c.clientShortName, c.modified_date) from ClientTable AS c where c.clientID=?1 ")
	public Page<ClientTable> findByIdPageable(Pageable pageable, int id);
	
	@Query(value= "SELECT NEW com.globeop.riskfeed.entity.ClientTable(c.clientID, c.clientShortName, c.modified_date) "
			+ "from ClientTable AS c "
			+ "where c.clientShortName LIKE %?1%")
	public Page<ClientTable> searchClientPageable(Pageable pageable, String keyword);
}
