/*
	Adventure App - Allows you to create an Adventure Book, or Download
 	books from other authors.
    Copyright (C) Fall 2013 Team 5 CMPUT 301 University of Alberta

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.uofa.adventure_app.controller.http;

import java.net.URL;

import com.uofa.adventure_app.enums.HttpRequestType;

public class HttpObject {
	
	private HttpRequestType type;
	private String postString;
	private URL url;
	/**
	 * Creates a HttpObject with the Parameters
	 * @param type
	 * @param postString
	 * @param url
	 */
	public HttpObject(HttpRequestType type, String postString, URL url) {
		this.type = type;
		this.postString = postString;
		this.url = url;
	}
	
	/**
	 * Returns the Type
	 * @return
	 */
	public HttpRequestType type() {
		return this.type;
	}
	
	/**
	 * Returns the Post String
	 * @return String
	 */
	public String postString() {
		return this.postString;
	}
	
	/**
	 * Returns the URL
	 * @return URL
	 */
	public URL url() {
		return this.url;
	}

}
