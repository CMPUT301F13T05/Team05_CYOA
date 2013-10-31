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
package com.uofa.adventure_app.model;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Author {
	
	private String name;
	private UUID uid;
	
	/**
	 * Generates an Empty Author. A uid is always generated.
	 */
	public Author() {
		super();
		this.setUid(UUID.randomUUID());
	}
	
	/**
	 * Creates an author with name and uid.
	 * @param name
	 */
	public Author(String name) {
		super();
		this.name = name;
		this.setUid(UUID.randomUUID());
	}

	/**
	 * Set the name of the Author
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	private void setUid(UUID uid) {
		this.uid = uid;
	}
	
	/**
	 * Get the name of the author
	 * @return
	 */
	public String name() {
		return this.name;
	}
	
	/**
	 * Get the Unique id of the Author
	 * @return
	 */
	public UUID uid() {
		return this.uid;
	}

	
}
