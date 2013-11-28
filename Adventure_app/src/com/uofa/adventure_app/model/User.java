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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

import com.uofa.adventure_app.interfaces.UniqueId;

public class User extends UniqueId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	/**
	 * Generates an Empty User. A uid is always generated.
	 */
	public User() {
		super();
	}
	
	public User(String name, UUID uid) {
		super();
		this.name = name;
		this.uid = uid;
	}
	
	/**
	 * Creates an author with name and uid.
	 * @param name
	 */
	public User(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	/**
	 * Set the name of the User
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	

	/**
	 * Get the name of the author
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Always treat de-serialization as a full-blown constructor, by validating
	 * the final state of the de-serialized object.
	 */
	private void readObject(ObjectInputStream aInputStream)
			throws ClassNotFoundException, IOException {
		// always perform the default de-serialization first
		aInputStream.defaultReadObject();

	}

	/**
	 * This is the default implementation of writeObject. Customise if
	 * necessary.
	 */
	private void writeObject(ObjectOutputStream aOutputStream)
			throws IOException {
		// perform the default serialization for all non-transient, non-static
		// fields
		aOutputStream.defaultWriteObject();
	}
	
}
