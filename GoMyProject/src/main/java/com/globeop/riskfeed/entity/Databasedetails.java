package com.globeop.riskfeed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name="DatabaseDetails")
public class Databasedetails {

	private static final long serialVersionUID = 1L;

	@Id   
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int id; 
	
	@Column(name = "ServerName")
	private String serverName;
	
	@Column(name = "Environment")
	private String environment;
	
	@Column(name = "Driver")
	private String driver;
	
	@Column(name = "Url")
	private String url;
	
	@Column(name = "Port")
	private String port;
	
	@Column(name = "UserName")
	private String userName;
	
	@Column(name = "Password")
	private String password;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Databasedetails [id=" + id + ", serverName=" + serverName + ", environment=" + environment + ", driver="
				+ driver + ", url=" + url + ", port=" + port + ", userName=" + userName + ", password=" + password
				+ "]";
	}
	
	
	
	
}
