package com.uofa.adventure_app.controller.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import com.google.gson.Gson;
import com.uofa.adventure_app.enums.HttpRequestType;
import com.uofa.adventure_app.model.Story;

 public class HttpObjectStory {
	
	private static final String commonUrlString = "http://cmput301.softwareprocess.es:8080/cmput301f13t05/";


	public HttpObject fetchAll() {
		HttpObject obj = null;
		String searchQuery = "{  \"fields\" : [\"id\", \"title\", \"users\"],\"query\": { \"match_all\": {}}}";
		try {
			obj = new HttpObject(HttpRequestType.POST,searchQuery , new URL(commonUrlString + "_search?pretty=1&size=1000000"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * Returns a story with a given id in JSON
	 * 
	 * @param id
	 * @return
	 */

	// TODO: implement
	public HttpObject fetch(UUID id) {
		// This should search all Fields
		HttpObject obj = null;
		String searchQuery = "{\"query\" : {\"term\" : {\"id\" : \"" + id.toString() + "\"}}}";
		//String searchQuery = "{ \"query\": { \"match_all\": {}}}";
		try {
			obj = new HttpObject(HttpRequestType.POST,searchQuery , new URL(commonUrlString + "_search?pretty=1"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * Publishes a Story to the Internet!
	 * 
	 * @param story
	 * @return
	 */
	public HttpObject publishObject(Story story) {
		Gson gson = new Gson();
		HttpObject obj = null;
		try {
			obj = new HttpObject(HttpRequestType.POST, gson.toJson(story), new URL(commonUrlString + story.id()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * Searches the Server for the given key
	 * 
	 * @param searchKey
	 * @return
	 */

	public HttpObject searchObject(String searchKey) {
		
		// This should search all Fields
		String searchQuery = "{\"query\" : { \"query_string\" : { \"query\" : \"" + searchKey + "\" }}}";
		HttpObject obj = null;
		//String searchQuery = "{ \"query\": { \"match_all\": {}}}";
		try {
			obj = new HttpObject(HttpRequestType.POST,searchQuery , new URL(commonUrlString + "_search?pretty=1"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
