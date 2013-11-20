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
package com.uofa.adventure_app.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.uofa.adventure_app.controller.http.HttpObject;
import com.uofa.adventure_app.enums.HttpRequestType;


public class WebServiceController {
	
	public String httpWithType(HttpObject obj) {
		
		//String ErrorMessage = null;
		HttpURLConnection conn = null;
		String responseMessage = null;
		
		// These things come from our object...
		URL url = obj.url();
		HttpRequestType requestType = obj.type();
		String postString = obj.postString();
		// We should probably only have a post string, if type 
		// = "POST"
		System.out.println(obj.url().toString());
		try {
			// Open a Connection for The URL in the obj
			conn = (HttpURLConnection) url.openConnection();
			
			// Get the Status code, we used a enum but the code is a string
			conn.setRequestMethod(requestType.getStatusCode());
			
			// This is always the same for our code, to use in another project you could
			// add this to a HttpObject
			conn.setRequestProperty("Accept","application/json");
			
			if(requestType.equals(HttpRequestType.POST)) {
				conn.setDoOutput(true);

				// Sets the length, no buffer needed.
				if(postString != null) {
					conn.setFixedLengthStreamingMode(postString.length());
					// Out put the post string in UTF-8
					System.err.println(postString);
					conn.getOutputStream().write(postString.getBytes(Charset.forName("UTF-8")));
				} else {
					throw new IOException("Invalid Post String");
				}
			}
			// Gets a Response Code.
			int status = conn.getResponseCode();
			
			if (status / 100 != 2) {
				responseMessage = conn.getResponseMessage();
			}
			
			// response = new Response(status, new Hashtable<String,
			// List<String>>(), conn.getResponseMessage().getBytes());
			if (responseMessage == null && requestType.equals(HttpRequestType.POST) || requestType.equals(HttpRequestType.GET)) {
				InputStream in = new BufferedInputStream(conn.getInputStream());
				responseMessage = readStream(in);
				}

		} catch (IOException e) {
			e.printStackTrace(System.err);
		} finally {
			// Disconnect!
			if (conn != null)
				conn.disconnect();
		}
		return responseMessage;
		
	}
	

	// utilities
	private static String readStream(InputStream in) throws IOException {
        byte[] contents = new byte[1024];
         int bytesRead=0;
         String s = new String();
         while ((bytesRead = in.read(contents)) != -1) {;
        	 String addString = new String(contents, 0, bytesRead);
        	 s = s + addString;
         }
		return s;

	}

}
