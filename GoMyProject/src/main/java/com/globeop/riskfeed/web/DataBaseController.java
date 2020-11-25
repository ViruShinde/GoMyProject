package com.globeop.riskfeed.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.globeop.riskfeed.service.DatabaseService;

@RestController
public class DataBaseController {

	@Autowired
	DatabaseService databaseService;
	@RequestMapping(value="/databaseList",method = RequestMethod.GET)
	@ResponseBody				  
	public List getDatabaselist(@RequestParam (value="p1", required=false, defaultValue="-1") String serverId){
    	System.out.println(" INSIDE ServerID "+serverId);		 	   		
    	return databaseService.getdatabaselist(serverId);	
	}
	
	
	@RequestMapping(value="/tablelist",method = RequestMethod.GET)
	@ResponseBody				  
	public List getTableList(@RequestParam (value="p1") String serverId,@RequestParam (value="p2") String selectedDatabaseName){
    	System.out.println(" INSIDE table list "+selectedDatabaseName);		 	   		
    	return databaseService.getTableList(serverId,selectedDatabaseName);	
	}
	
	/*
	 * @RequestMapping(value="/tabledesc",method = RequestMethod.GET)
	 * 
	 * @ResponseBody public List getTableDesc(@RequestParam (value="p1") String
	 * databaseName){ System.out.println(" INSIDE table list "+databaseName); return
	 * databaseService.getTableList(databaseName); }
	 */
	
	@RequestMapping(value="/getresult",method = RequestMethod.GET)
	@ResponseBody				  
	public Object getResult(@RequestParam (value="p1") String database, @RequestParam (value="p2") String serverId,@RequestParam (value="p3") String env,@RequestParam (value="p4") String query, @RequestParam (value="p5") String databaseName){
    	System.out.println(" INSIDE getResult list "+query);	
    	Object obj = databaseService.getResult(database, serverId, env, query,databaseName);	
    	//System.out.println(obj.toString());
    	//return databaseService.getResult(database, serverId, env, query,databaseName);
    	return obj;
	}
}
