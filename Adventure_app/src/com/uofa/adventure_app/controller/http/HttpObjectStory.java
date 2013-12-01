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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import com.google.gson.Gson;
import com.uofa.adventure_app.enums.HttpRequestType;
import com.uofa.adventure_app.model.Annotation;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;
/**
 * gets the JSON String and either publishes (uploads) it, or downloads it.
 * @author Chris Pavlicek
 *
 */
 public class HttpObjectStory {
	
	private static final String commonUrlString = "http://cmput301.softwareprocess.es:8080/cmput301f13t05/story/";
	private static final String commonUrlFragement = "http://cmput301.softwareprocess.es:8080/cmput301f13t05/fragements/";

	/**
	 * Gets the ID, title, and users for all of the stories.
	 * @return HttpObject
	 */
	public HttpObject fetchAll() {
		HttpObject obj = null;
		String searchQuery = "{  \"fields\" : [\"id\", \"title\", \"users\"]}";
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
	 * @param UUID id
	 * @return HttpObject
	 */

	// TODO: implement
	public HttpObject fetch(UUID id) {
		// This should search all Fields
		HttpObject obj = null;
		//String searchQuery = "{\"query\" : {\"term\" : {\"_id\" : \"" + id.toString() + "\"}}}";
		//searchQuery = "";
		//String searchQuery = "{ \"query\": { \"match_all\": {}}}";
		try {
			obj = new HttpObject(HttpRequestType.POST, "" , new URL(commonUrlString+ "_search?q=_id:" + id.toString()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * Fetches a specific fragement based onthe id.
	 * @param UUID id
	 * @return HttpObject
	 */
	public HttpObject fetchFragement(UUID id) {
		// This should search all Fields
		HttpObject obj = null;
		//String searchQuery = "{\"query\" : {\"term\" : {\"_id\" : \"" + id.toString() + "\"}}}";
		//searchQuery = "";
		//String searchQuery = "{ \"query\": { \"match_all\": {}}}";
		try {
			obj = new HttpObject(HttpRequestType.POST, "" , new URL(commonUrlFragement+ "_search?q=_id:" + id.toString()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * Publishes a Story to the Internet!
	 * 
	 * @param Story story
	 * @return HttpObject
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
	 * publishe s a specific fragement, and makes sure that it is added to the story.
	 * @param Fragement fragement
	 * @param UUID storyId
	 * @return HttpObject
	 */
	public HttpObject publishFragement(Fragement fragement, UUID storyId) {
		Gson gson = new Gson();
		HttpObject obj = null;
		
		try {
			obj = new HttpObject(HttpRequestType.POST, gson.toJson(fragement), new URL(commonUrlFragement + fragement.uid().toString()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	public HttpObject publishAnnotation(Annotation annotation, UUID fragementId) {
		Gson gson = new Gson();
		HttpObject obj = null;
		String query = "{\"script\": \"if (ctx._source[\"annotations\"] == null) { ctx._source.annotations = annotation } else { ctx._source.annotations += annotation }\"\"params\": {\"annotation\": "+ gson.toJson(annotation) + "}}";
		//
		try {
			obj = new HttpObject(HttpRequestType.POST, query, new URL(commonUrlFragement + fragementId.toString() + "/_update"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * Searches the Server for the given key
	 * 
	 * @param String searchKey
	 * @return HttpObject
	 */

	public HttpObject searchObject(String searchKey) {
		
		// This should search all Fields
		String searchQuery = "{  \"fields\" : [\"id\", \"title\", \"users\"],\"query\": { \"query_string\" : { \"query\" : \"" + searchKey + "\" }}}";
		//String searchQuery = "{\"query\" : { \"query_string\" : { \"query\" : \"" + searchKey + "\" }}}";
		HttpObject obj = null;
		//String searchQuery = "{ \"query\": { \"match_all\": {}}}";
		try {
			obj = new HttpObject(HttpRequestType.POST,searchQuery , new URL(commonUrlString + "_search?&size=1000000"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * Deletes a Story from the database.
	 * @param String id
	 * @return HttpObject
	 */
	public HttpObject deleteObject(String id) {
		
		String searchQuery = "";
		HttpObject obj = null;
		try {
			obj = new HttpObject(HttpRequestType.DELETE, searchQuery , new URL(commonUrlString + id + "/"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
}
