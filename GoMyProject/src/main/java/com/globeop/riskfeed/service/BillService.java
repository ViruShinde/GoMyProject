package com.globeop.riskfeed.service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.entity.BillTable;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.DevelopmentTable;
import com.globeop.riskfeed.entity.OnBordDto;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.enums.IsClientPayingOldCharges;
import com.globeop.riskfeed.enums.IsWaivedOff;
import com.globeop.riskfeed.repository.BillRepository;
import com.globeop.riskfeed.repository.ClientOnboardRepository;
import com.globeop.riskfeed.util.GenricUtil;

@Service
public class BillService implements CommonService<BillTable> {
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private ClientService theClientService;
	
	@Autowired RiskAggregatorService  theRiskAggregatorService; 
	
	@Autowired
	ClientOnboardRepository theClientOnboardRepository;

	@Override
	public List<BillTable> findAll() {
		return billRepository.findAll();
	}

	@Override
	public BillTable findById(int theId) {	
		
		Optional<BillTable> result = billRepository.findById(theId);
		BillTable theBill=null;
		
		if (result.isPresent()) {
			theBill = result.get();
		}
		else {
			throw new RuntimeException("Did not find Bill for id - " + theId);
		}
		return theBill;
	}

	@Override
	public void save(BillTable obj) {
		billRepository.save(obj);
		
	}

	@Override
	public void deleteById(int theId) {
		billRepository.deleteById(theId);
	}
	
	
	public void saveDetails(OnBordDto onBordDto,MultipartFile waiverMail,MultipartFile clientApprovalMail, MultipartFile terminationMail) {
		ClientTable client = theClientService.findById(onBordDto.getClientId());
		RiskAggregator riskAggregator = theRiskAggregatorService.findById(onBordDto.getRiskAggregatorId());
		
		String waiverMailName = GenricUtil.getFileName(waiverMail);
		String clientApprovalMailName = GenricUtil.getFileName(clientApprovalMail);
		String terminationMailName = GenricUtil.getFileName(terminationMail);
		System.out.println(onBordDto.getStartDate() + " >> "+onBordDto.getEndDate());
		try {
			BillTable bill = new BillTable(waiverMail.getBytes(), waiverMailName, clientApprovalMail.getBytes(), clientApprovalMailName,terminationMail.getBytes(), terminationMailName);
			bill.setSetupFee(onBordDto.getSetupFee());
			bill.setMonthlyFee(onBordDto.getMonthlyFee());
			bill.setDevlopementFee(onBordDto.getDevlopementFee());	
			bill.setIsClientPayingOldCharges(IsClientPayingOldCharges.valueOf(onBordDto.getIsClientPayingOldCharges()));
			bill.setIsWaivedOff(IsWaivedOff.valueOf(onBordDto.getIsWaivedOff()));	
			
			bill.setBillStartDate(LocalDate.parse(onBordDto.getStartDate()));
			if( !("".equals(onBordDto.getEndDate()) || null==onBordDto.getEndDate()) ) {
				bill.setBillEndDate(LocalDate.parse(onBordDto.getEndDate()));
			}
			
			bill.setClient(client);
			bill.setRiskAggregator(riskAggregator);
			bill.setCrmName(onBordDto.getCrmName());
			bill.setCrmailID(onBordDto.getCrmailID());
			bill.setBillingComments(onBordDto.getComments());
			bill.setGoCheckNoteId(onBordDto.getGoCheckNoteId());
			bill.setSetupFee(onBordDto.getSetupFee());
			
			// to get fund count from OnBoard table
			List<TestDto> fundList = theClientOnboardRepository.findFundsDetailsByClientAndRiskAggregator(onBordDto.getRiskAggregatorId(),onBordDto.getClientId());
			bill.setFundcount(fundList.size());
		
			bill.setModified_date(LocalDate.now());
			
			billRepository.save(bill);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	
	public void updateDetails(BillTable billTable,MultipartFile waiverMail,MultipartFile clientApprovalMail, MultipartFile terminationMail) {
		
		try {
			
		String waiverMailName = GenricUtil.getFileName(waiverMail);
		String clientApprovalMailName = GenricUtil.getFileName(clientApprovalMail);
		String terminationMailName = GenricUtil.getFileName(terminationMail);
				
			/*
			 * BillTable billTable2 = findById(billTable.getBillId());
			 * 
			 * billTable2.setSetupFee(billTable.getSetupFee());
			 * billTable2.setMonthlyFee(billTable.getMonthlyFee());
			 * billTable2.setDevlopementFee(billTable.getDevlopementFee());
			 * billTable2.setIsClientPayingOldCharges(billTable.getIsClientPayingOldCharges(
			 * )); billTable2.setIsWaivedOff(billTable.getIsWaivedOff());
			 * 
			 * billTable2.setBillStartDate(billTable.getBillStartDate());
			 * billTable2.setBillEndDate(billTable.getBillEndDate()); if(
			 * !("".equals(billTable2.getBillEndDate()) ||
			 * null==billTable2.getBillEndDate()) ) {
			 * billTable2.setBillEndDate(billTable2.getBillEndDate()); }
			 * 
			 * billTable2.setCrmName(billTable.getCrmName());
			 * billTable2.setCrmailID(billTable.getCrmailID());
			 * billTable2.setBillingComments(billTable.getBillingComments());
			 * billTable2.setGoCheckNoteId(billTable.getGoCheckNoteId());
			 */
		  
		  billTable.setWaiverMail(waiverMail.getBytes());
		  billTable.setWaiverFileName(waiverMailName);
		  billTable.setClientApprovalMail(clientApprovalMail.getBytes());
		  billTable.setClientApprovalMailName(clientApprovalMailName);
		  billTable.setTerminationMail(terminationMail.getBytes());
		  billTable.setTerminationMailName(terminationMailName);
		  billTable.setModified_date(LocalDate.now());
			
			billRepository.save(billTable);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public List<BillTable> findByClientAndRiskAggregator(ClientTable theClientTable, RiskAggregator theAggregator){
		return billRepository.findByClientAndRiskAggregator(theClientTable, theAggregator);
	}
	
	public BillTable getFile(int Id) throws Exception{
		return billRepository.findById(Id)
            //.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    		.orElseThrow(() -> new FileNotFoundException("File not found with id " + Id));
	}
	
}
