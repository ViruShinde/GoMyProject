package com.globeop.riskfeed.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.repository.ClientRepository;

import org.springframework.data.domain.Pageable;

@Service
public class ClientService implements CommonService<ClientTable>, PageableService<ClientTable> {

	private ClientRepository clientRepository;
	
	@Autowired
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<ClientTable> findAll() {	
		List<ClientTable> l =clientRepository.findAll();
		//System.out.println(l);
		//printList(l);
		//l=null;
		return l;
	}
	
	public List<String> findAll1() {	
		List<ClientTable> l =clientRepository.findAll();
		//System.out.println(l);
		printList(l);
		
		List<String> l2=new ArrayList<String>();
		for (ClientTable clientTable : l) {
			List<String> l3=new ArrayList<String>();
			l3.add(clientTable.getClientID()+"");
			l3.add(clientTable.getClientShortName());
			l3.add(clientTable.getModified_date()+"");
			l2.addAll(l3);
		}
		
		//l=null;
		return l2;
	}
	
	private void printList (List<ClientTable> l){
		for(ClientTable c:l) {
			System.out.println(c);
			//System.out.println(c.getFunds());
		}
	}

	@Override
	public ClientTable findById(int theId) {		
		Optional<ClientTable> result = clientRepository.findById(theId);
		ClientTable theClient=null;
		
		if (result.isPresent()) {
			theClient = result.get();
		}
		else {
			throw new RuntimeException("Did not find client id - " + theId);
		}
		return theClient;
	}

	@Override
	public void save(ClientTable obj) {
		// TODO Auto-generated method stub
		clientRepository.save(obj);
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		
	}
	
	public List<ClientTable> findByName(String name) {
		System.out.println("@@ "+name);
		//List<ClientTable> result = clientRepository.findByClientShortName(name.toUpperCase());
		List<ClientTable> result = clientRepository.findByClientShortNameStartingWith(name.toUpperCase());
		//List<ClientTable> result = clientRepository.findByClientShortNameLike(name.toUpperCase());
		return result;
	}

	public boolean checkClientAlreadyExist(String name) {
		List<ClientTable> clients = clientRepository.findByClientShortNameStartingWith(name.toUpperCase());
		if(clients==null) {
			return false;
		}
		for(ClientTable client : clients) {
			if(client.getClientShortName().equals(name)) 
				return true;						
		}
		return false;
	}
	public List<TestDto> test() {
		return clientRepository.testQuery();
		//return null;
	}
	
	@Override
	public Page<ClientTable> findAllPage(Pageable pageable){
		
		return clientRepository.findAllPageable(pageable);
	}

	@Override
	public Page<ClientTable> findByIdPage(Pageable pageable, int id) {
		// TODO Auto-generated method stub
		return clientRepository.findByIdPageable(pageable, id);
	}

	@Override
	public Page<ClientTable> getSearchDetails(Pageable pageable, String keyword) {
		
		return clientRepository.searchClientPageable(pageable, keyword);
	}

	@Override
	public Page<ClientTable> getSearchDetails(Pageable pageable, String keyword, int clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
