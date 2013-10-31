package com.uofa.adventure_app.controller.http;

import java.net.URL;

import com.uofa.adventure_app.enums.HttpRequestType;

public class HttpObject {
	
	private HttpRequestType type;
	private String postString;
	private URL url;
	
	public HttpObject(HttpRequestType type, String postString, URL url) {
		this.type = type;
		this.postString = postString;
		this.url = url;
	}
	
	public HttpRequestType type() {
		return this.type;
	}
	
	public String postString() {
		return this.postString;
	}
	
	public URL url() {
		return this.url;
	}

}
