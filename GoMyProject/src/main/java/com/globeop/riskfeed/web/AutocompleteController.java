package com.globeop.riskfeed.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globeop.riskfeed.dto.LabelValueDto;
import com.globeop.riskfeed.dto.SchedularDto;
import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.service.ClientOnboardService;
import com.globeop.riskfeed.service.ClientService;
import com.globeop.riskfeed.service.RiskAggregatorService;
import com.globeop.riskfeed.service.TestService;
import com.globeop.riskfeed.util.GenricUtil;
import com.globeop.riskfeed.util.KDFinder;

@RestController
public class AutocompleteController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private RiskAggregatorService riskAggregatorService;
	
	@Autowired
	private ClientOnboardService clientOnboardService;
	
	private List<ClientTable> allClients;

	@RequestMapping(value = "/clientList", method = RequestMethod.GET)
	@ResponseBody
	public List<LabelValueDto>/* Map<Integer, String> */ /* List<String> */ clientList(
			@RequestParam(value = "riskAggregatorId", required = false, defaultValue = "-1") String riskAggregatorId,
			@RequestParam(value = "term", required = false, defaultValue = "") String name) {
		// System.out.println(" INSIDE clientList "+name);
		List<LabelValueDto> l = new ArrayList<LabelValueDto>();
		// System.out.println(riskAggregatorId +" >> "+name);
		try {
			if (Integer.parseInt(riskAggregatorId) > 0) {
				// if(name.length()>2) {
				List<TestDto> clientOnboardTables = clientOnboardService
						.findByRiskAggregator2(Integer.parseInt(riskAggregatorId));
				allClients = new ArrayList<ClientTable>();
				for (TestDto t : clientOnboardTables) {
					ClientTable c = new ClientTable();
					c.setClientID(t.getClientID());
					c.setClientShortName(t.getClientName());
					allClients.add(c);
				}
				// allClients = clientService.findByName(name);
				// }
			}

			else if (name.length() > 2) {
				allClients = clientService.findAll();
				// allClients = clientService.findByName(name);
			}

			for (ClientTable c : allClients) {
				if (c.getClientShortName().toString().contains(name.toUpperCase())) {
					// l.add(c.getClientShortName());
					// test.put(c.getClientID(), c.getClientShortName());
					LabelValueDto labelValueDto = new LabelValueDto();
					labelValueDto.setLabel(c.getClientShortName());
					labelValueDto.setValue(c.getClientID() + "");
					l.add(labelValueDto);
				}
			}
			// System.out.println(" >> "+l);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return l;
	}

	@RequestMapping(value = "/riskAggregatorList", method = RequestMethod.GET)
	@ResponseBody
	public List<LabelValueDto> riskAggregatorList(
			@RequestParam(value = "term", required = false, defaultValue = "") String name) {
		List<LabelValueDto> l = new ArrayList<LabelValueDto>();
		List<RiskAggregator> riskAggregators = riskAggregatorService.findAll();

		for (RiskAggregator ra : riskAggregators) {
			LabelValueDto labelValueDto = new LabelValueDto();
			labelValueDto.setLabel(ra.getRiskAggregatorName());
			labelValueDto.setValue(ra.getRiskAggregatorId() + "");
			l.add(labelValueDto);
		}
		return l;
	}

	@RequestMapping(value = "/getFundsFromClient", method = RequestMethod.GET)
	@ResponseBody
	public List<LabelValueDto> test2(
			@RequestParam(value = "p1", required = false, defaultValue = "") String clientName) {
		// System.out.println(clientName);
		return GenricUtil.getClientFundList(clientName);
	}

	@RequestMapping(value = "/getFundsFromClient2", method = RequestMethod.GET)
	@ResponseBody
	public List<LabelValueDto> getFundsFromClient2(
			@RequestParam(value = "p1", required = false, defaultValue = "") int clientId,
			@RequestParam(value = "p2", required = false, defaultValue = "") int riskAggregatorId) {
		List<LabelValueDto> funds = new ArrayList<>();
		List<TestDto> onBoardedFunds = clientOnboardService.findFundsDetailsByClientAndRiskAggregator2(clientId,
				riskAggregatorId);
		for (TestDto t : onBoardedFunds) {
			LabelValueDto labelValueDto = new LabelValueDto();
			labelValueDto.setLabel(t.getFundName());
			labelValueDto.setValue(t.getFundID() + "");
			funds.add(labelValueDto);
		}
		return funds;
	}

	@RequestMapping(value = "/checkFundCount", method = RequestMethod.GET)
	@ResponseBody
	public List<TestDto> checkFundCount(
			@RequestParam(value = "p1", required = true, defaultValue = "") String riskAggregatorId,
			@RequestParam(value = "p2", required = true, defaultValue = "") String clientId) {
		// System.out.println(riskAggregatorId + " >> "+clientId);
		List<TestDto> fundList = new ArrayList<TestDto>();
		try {
			fundList = clientOnboardService.findFundsDetailsByClientAndRiskAggregator(Integer.parseInt(clientId),
					Integer.parseInt(riskAggregatorId));
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return fundList;
	}

	@RequestMapping(value = "/fetchKd", method = RequestMethod.GET)
	@ResponseBody
	public LabelValueDto fetchKd(@RequestParam(value = "p1") String client, @RequestParam(value = "p2") String fund,
			@RequestParam(value = "p3") String cobDate, @RequestParam(value = "p4") String frequency) {
		LabelValueDto labelValueDto = new LabelValueDto();
		KDFinder kdFinder = new KDFinder();
		String value = kdFinder.getValue(client, fund, cobDate, frequency);
		labelValueDto.setLabel("KD");
		labelValueDto.setValue(value);
		return labelValueDto;
	}

	/*
	@Autowired
	TestService theTestService;

	// @RequestMapping(value="/testPage",method = RequestMethod.GET)
	@GetMapping({ "/testPage", "/testPage/{id}" })
	@ResponseBody
	public Page viewHomePage(@PathVariable(required = false) String id) {
		System.out.println("******* id *" + id);
		return theTestService.getDetails(id, "clientID", "client");
	}

	public Page getPageClient() {

		return theTestService.getDetails("", "clientID", "client");
	}

	@GetMapping({ "/testPageFund", "/testPageFund/{id}" })
	@ResponseBody
	public Page testFundPage(@PathVariable(required = false) String id) {
		System.out.println("******* id *" + id);
		return theTestService.getDetails(id, "fundID", "fund");
	}
	
	 @RequestMapping("/testPage/search/")
	 //@RequestParam (value="p1", required=false, defaultValue="") String clientName	
	 @ResponseBody
	 public Page search( @RequestParam (value="keyword", required=false, defaultValue="") String keyword, @RequestParam (value="records", required=false, defaultValue="5") String noOfRecords, Model model) {
	 //public String search( @RequestParam("keyword") String keyword, @RequestParam("records") String noOfRecords, Model model) {
		 keyword=keyword.trim();
		 //System.out.println(keyword +":"+noOfRecords);
		 int no=Integer.parseInt(noOfRecords);
		 Page page = theTestService.getSearchDetails(keyword, no, "client", "clientID");	
		 model.addAttribute("keyword",keyword);
		 model.addAttribute("records",no);
		 //return commonMethod(page, model, 1, no, "scheduledDetailsId", "asc");
		 //return null;
		 return page;
	 }
	 
	 @RequestMapping("/testPageFund/search/")
	 //@RequestParam (value="p1", required=false, defaultValue="") String clientName	
	 @ResponseBody
	 public Page searchFund( @RequestParam (value="keyword", required=false, defaultValue="") String keyword, @RequestParam (value="records", required=false, defaultValue="5") String noOfRecords, Model model) {
	 //public String search( @RequestParam("keyword") String keyword, @RequestParam("records") String noOfRecords, Model model) {
		 keyword=keyword.trim();
		 //System.out.println(keyword +":"+noOfRecords);
		 int no=Integer.parseInt(noOfRecords);
		 Page page = theTestService.getSearchDetails(keyword, no, "fund", "fundID");	
		 model.addAttribute("keyword",keyword);
		 model.addAttribute("records",no);
		 //return commonMethod(page, model, 1, no, "scheduledDetailsId", "asc");
		 //return null;
		 return page;
	 }
	 */
}
