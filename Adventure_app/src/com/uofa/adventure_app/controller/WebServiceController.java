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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.google.gson.Gson;
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
		boolean returnValue = false;
		try {
			new PostStory(story).execute(new URL(commonUrlString + story.id()));
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

	// This code Should be refactored and Combined with other Server requests
	// that have similar code....
	// Post Request to the Server, with the Story
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
			Object BufferedInputStream;Adventure
			if (status / 100 != 2) {
			}
			*/
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
			 */
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return true;
	}

	/**
	 * This Class is used for Posting a Story
	 * 
	 * @author chris
	 * 
	 */
	private class PostStory extends AsyncTask<URL, Void, Boolean> {

		private Story story;

		public PostStory(Story story) {
			this.story = story;
		}

		protected Boolean doInBackground(URL... urls) {
			post(story, urls[0]);

			return true;
		}

		protected boolean onPostExecute(Long result) {

			return true;
		}

	}

	// utilities
	private static byte[] readStream(InputStream in) throws IOException {
		byte[] buf = new byte[1024];
		int count = 0;
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		while ((count = in.read(buf)) != -1)
			out.write(buf, 0, count);
		return out.toByteArray();
	}

}
