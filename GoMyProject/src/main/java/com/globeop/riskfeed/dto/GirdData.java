package com.globeop.riskfeed.dto;

import java.util.ArrayList;
import java.util.List;

public class GirdData {

	List header = new ArrayList();
	List data = new ArrayList();
	
	private String error="";
		
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List getHeader() {
		return header;
	}
	public void setHeader(List header) {
		this.header = header;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "GirdData [header=" + header + ", data=" + data + ", error=" + error + "]";
	}
	
	
	
	
	
}
