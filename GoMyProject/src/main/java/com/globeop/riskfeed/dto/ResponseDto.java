package com.globeop.riskfeed.dto;

public class ResponseDto {

	private String message="Someting went wrong";
	
	private String error="True";
	
	private int id;	

	public ResponseDto() {
		
	}

	public ResponseDto(String message, String error) {
		this.message = message;
		this.error = error;
	}
	
	

	public ResponseDto(String message, String error, int id) {
		this.message = message;
		this.error = error;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ResponseDto [message=" + message + ", error=" + error + "]";
	}
	
	
	
}
