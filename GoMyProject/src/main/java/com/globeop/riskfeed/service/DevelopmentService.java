package com.globeop.riskfeed.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.globeop.riskfeed.entity.ClientOnboardTable;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.DevelopmentTable;
import com.globeop.riskfeed.entity.OnBordDto;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.enums.IsActive;
import com.globeop.riskfeed.enums.IsWaivedOff;
import com.globeop.riskfeed.repository.DevelopmentRepository;
import com.globeop.riskfeed.util.GenricUtil;

@Service
public class DevelopmentService implements CommonService<DevelopmentTable> {

	@Autowired
	private DevelopmentRepository developmentRepository;
	
	@Autowired
	private ClientService clientService;
	

	@Autowired
	private RiskAggregatorService riskAggregatorService;
	
	@Override
	public List<DevelopmentTable> findAll() {		
		return developmentRepository.findAll();
	}

	@Override
	public DevelopmentTable findById(int theId) {		
		
		Optional<DevelopmentTable> result = developmentRepository.findById(theId);
		DevelopmentTable theDevelopment=null;
		
		if (result.isPresent()) {
			theDevelopment = result.get();
		}
		else {
			throw new RuntimeException("Did not find Development details for id - " + theId);
		}
		return theDevelopment;			
	}

	@Override
	public void save(DevelopmentTable obj) {		
		developmentRepository.save(obj);
	}

	@Override
	public void deleteById(int theId) {
		developmentRepository.deleteById(theId);
	}


public DevelopmentTable storeFile(MultipartFile file) throws Exception{
    // Normalize file name
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
        // Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
        	//custome exception
        	//throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        	throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
        }

        DevelopmentTable dbFile = new DevelopmentTable(fileName, file.getContentType(), file.getBytes());

        return developmentRepository.save(dbFile);
    } catch (IOException ex) {
        //throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
    	throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
    }
}


public void saveDetails( OnBordDto onBordDto,MultipartFile file) {
    // Normalize file name
	
	ClientTable client = clientService.findById(onBordDto.getClientId());
	RiskAggregator riskAggregator =  riskAggregatorService.findById(onBordDto.getRiskAggregatorId());
	String fileName="";
	 try {
		 fileName = StringUtils.cleanPath(file.getOriginalFilename());
   
		 
        // Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
        	//custome exception
        	//throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        	//throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
        	System.out.println("Sorry! Filename contains invalid path sequence " + fileName);
        }

        DevelopmentTable developmentTable = new DevelopmentTable(fileName, file.getContentType(), file.getBytes());
        developmentTable.setDevelopmentHours(onBordDto.getDevelopmentHours());
        developmentTable.setDevelopmentCost(onBordDto.getDevelopmentCost());
        developmentTable.setIsWaivedOff(IsWaivedOff.valueOf(onBordDto.getIsWaivedOff()));
        developmentTable.setStartDate(LocalDate.parse(onBordDto.getStartDate()));
        developmentTable.setEndDate(LocalDate.parse(onBordDto.getEndDate()));
        developmentTable.setDevelopmentComments(onBordDto.getComments());
        developmentTable.setClient(client);
        developmentTable.setRiskAggregator(riskAggregator);
        developmentTable.setModified_date(LocalDate.now());
        
        System.out.println(developmentTable);
        
        //return developmentRepository.save(developmentTable);
        developmentRepository.save(developmentTable);
    } catch (Exception ex) {
        //throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
    	//throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
    	//System.out.print("Could not store file " + fileName + ". Please try again!", ex);
    	ex.printStackTrace();
    }
}

	public DevelopmentTable getFile(int Id) throws Exception{
		return developmentRepository.findById(Id)
            //.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    		.orElseThrow(() -> new FileNotFoundException("File not found with id " + Id));
	}
	
	public List<DevelopmentTable> findByClientAndRiskAggregator(ClientTable theClientTable, RiskAggregator theAggregator){
		return developmentRepository.findByClientAndRiskAggregator(theClientTable, theAggregator);
	}
}
