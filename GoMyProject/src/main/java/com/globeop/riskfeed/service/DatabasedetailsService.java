package com.globeop.riskfeed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globeop.riskfeed.entity.Databasedetails;
import com.globeop.riskfeed.repository.DatabasedetailsRepository;

@Service
public class DatabasedetailsService implements CommonService<Databasedetails> {

	
	@Autowired
	private DatabasedetailsRepository databasedetailsRepository;
	
	
	@Override
	public List<Databasedetails> findAll() {
		List<Databasedetails> l=databasedetailsRepository.findAll();		
		return l;
	}

	@Override
	public Databasedetails findById(int theId) {
		Optional<Databasedetails> result = databasedetailsRepository.findById(theId);
		Databasedetails databasedetails=null;
		
		if (result.isPresent()) {
			databasedetails = result.get();
		}
		else {
			throw new RuntimeException("Did not find Fund id - " + theId);
		}
		return databasedetails;	
	}

	@Override
	public void save(Databasedetails obj) {
		databasedetailsRepository.save(obj);
		
	}

	@Override
	public void deleteById(int theId) {
		databasedetailsRepository.deleteById(theId);;
		
	}
	
	public List<Databasedetails> findByEnvironment(String env) {
		return databasedetailsRepository.findByEnvironment(env);
	}
	

	
}
