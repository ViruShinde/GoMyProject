package com.globeop.riskfeed.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.spi.HibernateEntityManagerImplementor.QueryOptions.ResultMetadataValidator;
import org.springframework.stereotype.Repository;

import com.globeop.riskfeed.dto.GirdData;
import com.globeop.riskfeed.entity.Databasedetails;
import com.globeop.riskfeed.util.GenricUtil;

@Repository
public class DatabaseRepository {
	
	
	private Connection conn = null;
	private void getDatabaseConnection(String server) {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/";
		String user="root";
		String pass="root";
		conn= DriverManager.getConnection(url,user,pass);
		if(conn != null) {
			System.out.println(" connection succesfull");
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getDatabaseConnection(Databasedetails dbDetails) {
		try {
		Class.forName(dbDetails.getDriver());		
		conn= DriverManager.getConnection(dbDetails.getUrl(),dbDetails.getUserName(), GenricUtil.decode(dbDetails.getPassword()) );
		if(conn != null) {
			System.out.println(" connection succesfull");
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getDatabaseConnection(String server, String dbName) {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/"+dbName;
		String user="root";
		String pass="root";
		conn= DriverManager.getConnection(url,user,pass);
		if(conn != null) {
			System.out.println(" connection succesfull");
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			if(conn != null) {
				conn.close();
				System.out.println("cloed connection succesfull");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public List getDatabaseDetails(String server) {
		String query="show databases";
		List databaseList=new ArrayList();
		try {
		getDatabaseConnection(server);
		PreparedStatement pstmt=conn.prepareStatement(query);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			databaseList.add(rs.getString("Database"));
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return databaseList;
	}
	
	public List getDatabaseDetails(Databasedetails dbDetails) {
		// for Mysql DB
		String query="show databases";
		//for sybase
		//String query="sp_helpdb";
		
		List databaseList=new ArrayList();
		try {
		getDatabaseConnection(dbDetails);
		PreparedStatement pstmt=conn.prepareStatement(query);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			databaseList.add(rs.getString("Database"));
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return databaseList;
	}
	
	public List getTableDetails(Databasedetails dbDetails,String dbName) {
		//for Mysql db
		String query="show tables";
		//for sybase
		//String query="sp_help";
		List databaseList=new ArrayList();
		try {
			System.out.println(dbDetails);
		getDatabaseConnection(dbDetails);
		PreparedStatement pstmt=conn.prepareStatement(query);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			databaseList.add(rs.getString("Tables_in_"+dbName));
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return databaseList;
	}
	
	
	public Object getResult(String database, String serverId, String env, String sqlQuery, String dbName,Databasedetails dbDetails) {
		System.out.println("Database - "+database+" ServerID -"+serverId+" env -"+env +" database name -"+dbName);
		
		GirdData gridData = new GirdData();
		List header = new ArrayList();
		List data = new ArrayList();
		Map headerDataMap=null;
		Map dataMap=null;
		List databaseList=new ArrayList();
		try {
		getDatabaseConnection(dbDetails);
		PreparedStatement pstmt=conn.prepareStatement(sqlQuery);
		ResultSet rs=pstmt.executeQuery();
		ResultSetMetaData rsmeta = rs.getMetaData();	
		for (int i=1; i<=rsmeta.getColumnCount(); i++) {
			headerDataMap=new HashMap();
			headerDataMap.put("headerName", rsmeta.getColumnName(i));
			headerDataMap.put("field", rsmeta.getColumnName(i));
			headerDataMap.put("sortable", true);
			headerDataMap.put("filter", true);			
			header.add(headerDataMap);
		}
		gridData.setHeader(header);
		while(rs.next()) {
			dataMap= new HashMap();			
			for (int i=1; i<=rsmeta.getColumnCount(); i++) {
				dataMap.put(rsmeta.getColumnName(i),rs.getObject(i));
			}
			data.add(dataMap);
					
		}
		
		gridData.setData(data);
		
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("############# "+e.getMessage());
			gridData.setError(e.getMessage());
			//e.printStackTrace();			
		}finally {
			closeConnection();
		}
		//System.out.println("###"+gridData);
		return gridData;
	}
		
	
}
