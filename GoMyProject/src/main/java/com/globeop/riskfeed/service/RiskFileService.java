package com.globeop.riskfeed.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.globeop.riskfeed.dto.RiskFileDto;


@Service
public class RiskFileService {

	
	public void generateRiskFile(RiskFileDto riskFileDto) {
		
//		RunScript runScript= new RunScript(riskFileDto);		
//		Thread th = new Thread(runScript);
//        th.start();	
		
		/*
		 * dummy input
		riskFileDto.setClient("BIRCHLANE");
		riskFileDto.setRiskAggregator("HEDGEPLATFORM");
		riskFileDto.setFund("BIRCHLANECREDITALT");
		riskFileDto.setCobDate("2020-10-31");
		riskFileDto.setFrequency("MONTHLY");
		*/
		
		/*
		MyCallable m = new MyCallable(riskFileDto);
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future f = service.submit(m);
		try {
			System.out.println("Process completed >>>>> "+f.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		service.shutdown();
        */
		try {
			generateFile(riskFileDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private Object generateFile(RiskFileDto riskFileDto) throws Exception {
		String s1=Thread.currentThread().getName();
		System.out.println(s1);
		long pid =0;		
		
		FileWriter fw=null;
		BufferedWriter bw=null;
		BufferedReader br=null; 
        try {
            Runtime run = Runtime.getRuntime();
            String script =getScript(riskFileDto.getRiskAggregator());
            System.out.println("script ******* "+script);
            String cmd =  script + " -frequency " + riskFileDto.getFrequency() + " -client " + riskFileDto.getClient() + " -fund " + riskFileDto.getFund() + " -cobdate " + riskFileDto.getCobDate();
            
            if( ! (riskFileDto.getKnowledgeDate()=="" || riskFileDto.getKnowledgeDate().equalsIgnoreCase("EXCEPTION")) ) {
            	cmd = cmd +" -knowledgedate " +riskFileDto.getKnowledgeDate();
            }
            
            System.out.println(cmd);
                  
             // store data in mysql
            //recordProcessInfromation(cmd+ " "+formatter.format(new Date()), ClientKondorName);
            //log.info("Command under execution for client "+ClientKondorName +" and Fund : "+FundName+"...$ " +cmd);
            String logPath = "/home/rskmtrx/projects/RISKFEED/logfiles/"+riskFileDto.getRiskAggregator()+"_"+ riskFileDto.getClient() + "_" + riskFileDto.getFund() +"_"+ riskFileDto.getCobDate()+"_" +LocalDateTime.now()+"_log.txt";
            //logPath="D://datafiles//logfiles.txt";
            //cmd = "dir";
            Process pr = run.exec(cmd);               
            pid = getProcessID(pr);
            //pid=0;
            System.out.println("Process Running >>>>>> "+pid);
            fw = new FileWriter(logPath, true);
//          fw = new FileWriter("D:\\datafiles\\RiskFeedProcessRunLog1.txt",true);
            bw = new BufferedWriter(fw);
            br= new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String s = null;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                bw.write(s);
                bw.newLine();
            }           
            br.close();
        	bw.close();		
			fw.close();
            pr.waitFor();	
            riskFileDto.setFilePath("/home/rskmtrx/projects/RISKFEED/"+riskFileDto.getRiskAggregator()+"/"+riskFileDto.getClient()+"/datafiles/"+riskFileDto.getFund()+"."+riskFileDto.getCobDate()+".RM.csv");
            //riskFileDto.setFilePath(logPath);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		return pid;
	}

	private String getScript(String riskAggregator) {
		return "/home/rskmtrx/projects/RISKFEED/"+riskAggregator+"/send"+riskAggregator+"Files.sh";
	}
	
	public static long getProcessID(Process p)
    {
        long result = -1;
        try
        {
            //for windows
            if (p.getClass().getName().equals("java.lang.Win32Process") ||
                   p.getClass().getName().equals("java.lang.ProcessImpl")) 
            {
            	/*
            	 * not working for windows
                Field f = p.getClass().getDeclaredField("handle");
                f.setAccessible(true);              
                long handl = f.getLong(p);
                Kernel32 kernel = Kernel32.INSTANCE;
                WinNT.HANDLE hand = new WinNT.HANDLE();
                hand.setPointer(Pointer.createConstant(handl));
                result = kernel.GetProcessId(hand);
                f.setAccessible(false);
                */
            }
            //for unix based operating systems
            else if (p.getClass().getName().equals("java.lang.UNIXProcess")) 
            {
                Field f = p.getClass().getDeclaredField("pid");
                f.setAccessible(true);
                result = f.getLong(p);
                f.setAccessible(false);
            }
        }
        catch(Exception ex)
        {
            result = -1;
        }
        return result;
    }
}
