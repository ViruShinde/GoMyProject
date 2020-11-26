package com.globeop.riskfeed.util;



import com.globeop.gocheck.api.GoCheckApiConfig;
import com.globeop.gocheck.api.GoCheckApiConfigParser;
import com.globeop.gocheck.api.TaskItemRetriever;
import com.globeop.gocheck.api.TaskItemRetrieverFactory;
import com.globeop.gocheck.tasks.TaskItem;
import org.xml.sax.XMLReader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

public class KDFinder
{
    public static final String FILE_NAME="/home/rskmtrx/etc/gocheck-api-config.xml";
    public static final String LOCAL_FILE="D:\\datafiles\\gocheck-api-config.xml";

    public String getValue(String client, String fundname, String cobdate, String process){
        try{

            Calendar cal=new GregorianCalendar();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date dt=sdf.parse(cobdate);
            cal.setTime(dt);

            if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY){

                cal.add(Calendar.DAY_OF_YEAR, 3) ;
            }
            else if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){

                cal.add(Calendar.DAY_OF_YEAR, 1) ;
            }else if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){

                cal.add(Calendar.DAY_OF_YEAR, 2) ;
            }else if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY && process.equalsIgnoreCase("Weekly")){
                cal.add(Calendar.DAY_OF_YEAR,0);
            } else{

                cal.add(Calendar.DAY_OF_YEAR, 1);
            }
            Date taskdate=cal.getTime();
            String task=sdf.format(taskdate);
                                 
            int len=task.length();
            if(process.contains("MONTHLY")){
                if((task.substring(len-2,len)).equals("02") || (task.substring(len-2,len)).equals("03")){
                    // task=task.replace(task.substring(len-2,len),"01");
                    task=task.substring(0,8);
                    task=task+"01";
                }
            }
                        
            Class clazz = Class.forName("org.apache.xerces.parsers.SAXParser");
            XMLReader reader = (XMLReader) clazz.newInstance();
            GoCheckApiConfigParser goCheckApiConfigParser = new GoCheckApiConfigParser(reader);
            reader.setContentHandler(goCheckApiConfigParser);
            //reader.parse(new InputSource(Resources.getResourceAsStream("/home/rskmtrx/etc/gocheck-api-config.xml")));
            File file = new File(FILE_NAME);
            if( file.exists() ){
                //System.out.println("found " + FILE_NAME  );
                reader.parse(FILE_NAME);
            }else{
                //System.out.println("Local file found  " + LOCAL_FILE  );
                reader.parse(LOCAL_FILE);
            }
            GoCheckApiConfig config = goCheckApiConfigParser.getGoCheckClientConfig();
            TaskItemRetriever taskItemRetriever =  TaskItemRetrieverFactory.newInstance(config);

            Properties fundProp = new Properties();
            int taskId=0;
            if(process.contains("MONTHLY")){
                fundProp=GenricUtil.loadPropertyFile("Monthly_Funds.properties");
            }else if(process.contains("DAILY")){
                fundProp=GenricUtil.loadPropertyFile("Daily_Funds.properties");
            }else if(process.contains("WEEKLY")){
                fundProp=GenricUtil.loadPropertyFile("Weekly_Funds.properties");
            }

            taskId = GenricUtil.checkFundInPropertyFile(fundProp,fundname);
            TaskItem taskItem = taskItemRetriever.getTaskItem(client,fundname,task,taskId);
            return taskItem.getItemValue().replaceAll(" ",":");

        }catch (Exception ex){
        	//ex.printStackTrace();
            return "Exception";
        }

    }

    public String getPeriodEndDate(String client, String fundname, String cobdate, String process, int cobDateTaskItemId){
        try{
            Calendar cal=new GregorianCalendar();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date dt=sdf.parse(cobdate);
            cal.setTime(dt);

            if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY){

                cal.add(Calendar.DAY_OF_YEAR, 3) ;
            }
            else if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){

                cal.add(Calendar.DAY_OF_YEAR, 1) ;
            }else if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){

                cal.add(Calendar.DAY_OF_YEAR, 2) ;
            }else if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY && process.equalsIgnoreCase("Weekly")){
                cal.add(Calendar.DAY_OF_YEAR,0);
            } else{

                cal.add(Calendar.DAY_OF_YEAR, 1);
            }
            Date taskdate=cal.getTime();
            String task=sdf.format(taskdate);

            int len=task.length();
            if(process.contains("ME")){
                if((task.substring(len-2,len)).equals("02") || (task.substring(len-2,len)).equals("03")){
                    // task=task.replace(task.substring(len-2,len),"01");
                    task=task.substring(0,8);
                    task=task+"01";
                }
            }
            Class clazz = Class.forName("org.apache.xerces.parsers.SAXParser");
            XMLReader reader = (XMLReader) clazz.newInstance();
            GoCheckApiConfigParser goCheckApiConfigParser = new GoCheckApiConfigParser(reader);
            reader.setContentHandler(goCheckApiConfigParser);
            //reader.parse(new InputSource(Resources.getResourceAsStream("/home/rskmtrx/etc/gocheck-api-config.xml")));
            File file = new File(FILE_NAME);
            if( file.exists() ){
                //System.out.println("found " + FILE_NAME  );
                reader.parse(FILE_NAME);
            }else{
                //System.out.println("Local file found  " + LOCAL_FILE  );
                reader.parse(LOCAL_FILE);
            }
            //reader.parse("/home/rskmtrx/etc/gocheck-api-config.xml");
            //reader.parse(LOCAL_FILE);  // for local use
            GoCheckApiConfig config = goCheckApiConfigParser.getGoCheckClientConfig();
            TaskItemRetriever taskItemRetriever =  TaskItemRetrieverFactory.newInstance(config);
            //int taskId=132225;    //generic taskDefinition itemID in GoCheck for all MonthEnd tasks
            TaskItem taskItem = taskItemRetriever.getTaskItem(client,fundname,task,cobDateTaskItemId);
            return getformattedDate(taskItem.getItemValue()).toString();
        }catch (Exception ex){
            //ex.printStackTrace();
            return cobdate;
        }
    }

    public static Boolean checkTaskCompletionStatus(String client, String fund, String taskDate){
        try{
            Boolean status1=false;
            Boolean status2=false;
            Class clazz = Class.forName("org.apache.xerces.parsers.SAXParser");
            XMLReader reader = (XMLReader) clazz.newInstance();
            GoCheckApiConfigParser goCheckApiConfigParser = new GoCheckApiConfigParser(reader);
            reader.setContentHandler(goCheckApiConfigParser);
            //reader.parse(new InputSource(Resources.getResourceAsStream("/home/rskmtrx/etc/gocheck-api-config.xml")));
            File file = new File(FILE_NAME);
            if( file.exists() ){
                //System.out.println("found " + FILE_NAME  );
                reader.parse(FILE_NAME);
            }else{
                //System.out.println("Local file found  " + LOCAL_FILE  );
                reader.parse(LOCAL_FILE);
            }
            //reader.parse("/home/rskmtrx/etc/gocheck-api-config.xml");
            //reader.parse(LOCAL_FILE);  // for local use
            GoCheckApiConfig config = goCheckApiConfigParser.getGoCheckClientConfig();
            TaskItemRetriever taskItemRetriever =  TaskItemRetrieverFactory.newInstance(config);
            //TaskItem taskItem = taskItemRetriever.getTaskItem(client,fund,taskDate,5097);   //  #18 NAV check
            TaskItem taskItem = taskItemRetriever.getTaskItem(client,fund,taskDate,5097);   //  #8 KD check
            status1=taskItem.isCompleted();

           // System.out.println(taskItem.isCompleted());
            //TaskItem taskItem2 = taskItemRetriever.getTaskItem(client,fund,taskDate,77302);    // #15 rootcause check
            TaskItem taskItem2 = taskItemRetriever.getTaskItem(client,fund,taskDate,77070);    // #7 check P&L reporting has been reviewed and is approved for release to Client
            status2=taskItem2.isCompleted();
           // System.out.println(taskItem2.isCompleted());
            if (status1 == true && status2 == true){
               return true;
            }else{
                return false;
            }
        }   catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static String getformattedDate(String inputDate) {
        try {
            SimpleDateFormat input = new SimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
            Date inputdate = input.parse(inputDate);
            return output.format(inputdate);
        } catch (Exception ex) {
            return inputDate;
        }
    }
    public static void main(String[] args){
        try{
            String fundname="";
            String cobdate="";
            String client="";
            String process="";
            int item=0;
            for(int i=0;i<args.length;i++){
                if(args[i].equalsIgnoreCase("-client")){client=args[i+1];}
                if(args[i].equalsIgnoreCase("-fund")){fundname=args[i+1];}
                if (args[i].equalsIgnoreCase("-cobdate")){cobdate = args[i + 1];}
                if (args[i].equalsIgnoreCase("-process")){process = args[i + 1];}

            }
                        
            KDFinder t=new KDFinder();
            System.out.println(t.getValue(client, fundname, cobdate,process));
            //System.out.println(t.getValue("BFAM","AOF_MA","2020-10-31","MONTHLY"));
            return;
            
        }catch (Exception ex){
            System.out.println("Exception");
            return;
        }

    }



}