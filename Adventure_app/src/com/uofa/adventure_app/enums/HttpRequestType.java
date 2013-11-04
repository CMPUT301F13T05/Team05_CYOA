package com.uofa.adventure_app.enums;

public enum HttpRequestType {
	GET("GET"), POST("POST");
	
	private String statusCode;
	
	private HttpRequestType (String s) {
		statusCode = s;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
}
