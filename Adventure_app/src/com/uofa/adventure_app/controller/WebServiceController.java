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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.uofa.adventure_app.controller.http.HttpObject;
import com.uofa.adventure_app.enums.HttpRequestType;
import com.uofa.adventure_app.model.Story;

public class WebServiceController {

	private HttpClient httpclient = new DefaultHttpClient();
	private static final String commonUrlString = "http://cmput301.softwareprocess.es:8080/cmput301f13t05/";

	/**
	 * Gets a story by name
	 * 
	 * @return
	 */
	// TODO: Implement
	public Story fetch(String name) {

		Story story = new Story();
		return story;
	}

	/**
	 * Returns a story with a given id in JSON
	 * 
	 * @param id
	 * @return
	 */

	// TODO: implement
	public Story fetch(Integer id) {
		Story story = new Story();
		return story;
	}

	/**
	 * Publishes a Story to the Internet!
	 * 
	 * @param story
	 * @return
	 */
	public boolean publish(Story story) {
		Gson gson = new Gson();
		boolean returnValue = false;
		try {
			HttpObject obj = new HttpObject(HttpRequestType.POST, gson.toJson(story), new URL(commonUrlString + story.id()));
			new PerformHttp().execute(obj);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}

	/**
	 * Searches the Server for the given key
	 * 
	 * @param searchKey
	 * @return
	 */

	// TODO: Implement
	public ArrayList<Story> search(String searchKey) {
		return new ArrayList<Story>();
	}

	/**
	 * TODO:Implement
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public ArrayList<Story> loadWithIndexRange(int start, int end) {
		return new ArrayList<Story>();
	}

	
	private String httpWithType(HttpObject obj) {
		
		String ErrorMessage = null;
		HttpURLConnection conn = null;
		String responseMessage = null;
		
		// These things come from our object...
		URL url = obj.url();
		HttpRequestType requestType = obj.type();
		String postString = obj.postString();
		// We should probably only have a post string, if type 
		// = "POST"
		
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
					conn.getOutputStream().write(postString.getBytes(Charset.forName("UTF-8")));
				} else {
					throw new IOException("Invalid Post String");
				}

			// Gets a Response Code.
			int status = conn.getResponseCode();
			if (status / 100 != 2) {
				responseMessage = conn.getResponseMessage();
			}
			}
			// response = new Response(status, new Hashtable<String,
			// List<String>>(), conn.getResponseMessage().getBytes());

			if (responseMessage == null) {
				InputStream in = new BufferedInputStream(conn.getInputStream());
				responseMessage = readStream(in);
				}

		} catch (IOException e) {
			e.printStackTrace(System.err);
			// Error Handeling
			/*
			 * mErrorMessage = ((request instanceof POST) ? "POST " : "GET ") +
			 * str(R.string.aerc_failed) + ": " + e.getLocalizedMessage();
			 */
		} finally {
			// Disconnect!
			if (conn != null)
				conn.disconnect();
		}
		return responseMessage;
		
	}
	
	
	// This code Should be refactored and Combined with other Server requests
	// that have similar code....
	// Post Request to the Server, with the Story
	/*
	private boolean post(Story story, URL url) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(story);

		String ErrorMessage = null;
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept","application/json");
			conn.setDoOutput(true);

			// Sets the length, no buffer needed.
			conn.setFixedLengthStreamingMode(jsonString.length());

			// Writes the Bytes to the ouput stream
			conn.getOutputStream().write(
					jsonString.getBytes(Charset.forName("UTF-8")));

			/*
			// Gets a Response Code.
			int status = conn.getResponseCode();
			Object BufferedInputStream;
			if (status / 100 != 2) {
			}
			
			// response = new Response(status, new Hashtable<String,
			// List<String>>(), conn.getResponseMessage().getBytes());

			// if (response == null) {
			BufferedInputStream in = new BufferedInputStream(
					conn.getInputStream());
			byte[] body = readStream(in);
			// response = new Response(conn.getResponseCode(),
			// conn.getHeaderFields(), body);
			// }

		} catch (IOException e) {
			e.printStackTrace(System.err);
			// Error Handeling
			/*
			 * mErrorMessage = ((request instanceof POST) ? "POST " : "GET ") +
			 * str(R.string.aerc_failed) + ": " + e.getLocalizedMessage();
			 
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return true;
	}
*/
	/**
	 * This Class is used for Posting a Story
	 * 
	 * @author chris
	 * 
	 */
	private class PerformHttp extends AsyncTask<HttpObject, Void, String> {

		protected String doInBackground(HttpObject... httpObj) {
			return httpWithType(httpObj[0]);
		}

		protected void onPostExecute(Long result) {

			//return true;
		}

	}

	// utilities
	private static String readStream(InputStream in) throws IOException {
        byte[] contents = new byte[1024];
         int bytesRead=0;
         String s = new String();
         while ((bytesRead = in.read(contents)) != -1) {
             s = new String(contents, 0, bytesRead);
         }
		return s;

	}

}
