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

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.uofa.adventure_app.model.Story;

public class WebServiceController {
	
    private HttpClient httpclient = new DefaultHttpClient();
    private static final String TAG = "HttpClient";
	
/**
 * Gets a story by name
 * @return
 */
	// TODO: Implement
	public Story fetch(String name) {

		Story story = new Story();
		return story;
	}
	/**
	 * Returns a story with a given id in JSON
	 * @param id
	 * @return
	 */
	
	// TODO: implement
	public Story fetch(Integer id) {
		Story story = new Story();
		return story;
	}
	
	/**
	 * Publishes the given story to the server
	 * @param story
	 * @return
	 */
	
	private StringEntity stringEntity(Story story) {
        StringEntity stringEntity = null;
        Gson obj = new Gson();
		String jsonString = obj.toJson(story);
        try {
                stringEntity = new StringEntity(jsonString);
        } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
        		e.printStackTrace();
        		return null;
        }
        
        	return stringEntity;
	}
	
	private void writeStream(OutputStream out, String jsonString) {
		try {
			out.write(jsonString.getBytes(Charset.forName("UTF-8")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// TODO: implement
	public boolean publish(Story story) {
		
		
		return true;
	}
	

	
	/**
	 * Searches the Server for the given key
	 * @param searchKey
	 * @return
	 */
	
	// TODO: Implement
	public ArrayList<Story> search(String searchKey) {
		return new ArrayList<Story>();
	}
	
	/**
	 * TODO:Implement
	 * @param start
	 * @param end
	 * @return
	 */
	public ArrayList<Story> loadWithIndexRange(int start, int end) {
		return new ArrayList<Story>();
	}
	
	/**
	 * This Class is used for Posting a Story
	 * @author chris
	 *
	 */
	private class PostStory extends AsyncTask<URL, Void, Boolean> {
		
		private Story story;
		
		
		
	     protected Boolean doInBackground(URL... urls) {
	         int count = urls.length;
	         long totalSize = 0;

	         return true;
	     }


	     protected boolean onPostExecute(Long result) {
	    	 
	    	 return true;
	     }

	 }
	

}
