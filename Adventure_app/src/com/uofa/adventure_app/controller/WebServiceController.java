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
