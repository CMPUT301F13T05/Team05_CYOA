package com.uofa.adventure_app.enums;

public enum MediaType {
	MUSIC("M"), VIDEO("V"), AUDIO("A");
	
	private String statusCode;
	
	private MediaType (String s) {
		statusCode = s;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	

}
