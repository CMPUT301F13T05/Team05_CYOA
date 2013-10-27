package com.uofa.adventure_app.controller;

import java.util.ArrayList;

import com.uofa.adventure_app.model.Story;

public class WebServiceController {
	
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
	 * Returns a story with a given id.
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

}
