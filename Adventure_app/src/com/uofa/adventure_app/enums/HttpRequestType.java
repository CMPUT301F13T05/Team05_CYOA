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
package com.uofa.adventure_app.enums;
/**
 * holds HttpRequestType.
 * @author Chris Pavlicek
 *
 */
public enum HttpRequestType {
	GET("GET"), POST("POST"), DELETE("DELETE");
	
	private String statusCode;
	/**
	 * takes a string as a Status code
	 * @param s
	 */
	private HttpRequestType (String s) {
		statusCode = s;
	}
	/**
	 * 
	 *  Returns the Status Code.
	 * @return String
	 */
	public String getStatusCode() {
		return statusCode;
	}
}
