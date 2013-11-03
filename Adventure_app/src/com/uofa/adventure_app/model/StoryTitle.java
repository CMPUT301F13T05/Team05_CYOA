/*
Adventure App - Allows you to create an Adventure Book, or Download
	books from other users.
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

import java.util.ArrayList;
import java.util.UUID;

public class StoryTitle {

	private String title;
	private ArrayList<User> users; 
	private UUID id;
	
	public StoryTitle() {
		this.users = new ArrayList<User>();
		this.id = UUID.randomUUID();
		this.title = "";
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	public String title() {
		return this.title;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
/**
 * Adds an User to the Current List of users
 * @param author
 */
	
	//TODO: Check if author exists in list already.
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public ArrayList<User> users() {
		return this.users;
	}
	
	// Shouldn't this be private?!
	public void setId(UUID id) {
		this.id = id;
	}
	
	public UUID id() {
		return this.id;
	}

}
