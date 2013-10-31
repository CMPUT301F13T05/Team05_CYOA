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

import java.util.ArrayList;
import java.util.UUID;

public class Story {

	private String title;
	private ArrayList<Author> authors; 
	private ArrayList<Fragement> fragements;
	private UUID id;
	
	public Story() {
		this.authors = new ArrayList<Author>();
		this.fragements = new ArrayList<Fragement>();
		this.id = UUID.randomUUID();
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}
/**
 * Adds an Author to the Current List of Authors
 * @param author
 */
	
	//TODO: Check if author exists in list already.
	public void addAuthor(Author author) {
		this.authors.add(author);
	}
	
	/**
	 * @param fragements the fragements to set
	 */
	public void setFragements(ArrayList<Fragement> fragements) {
		this.fragements = fragements;
	}
	
	public void addFragement(Fragement fragement) {
		this.fragements.add(fragement);
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public UUID id() {
		return this.id;
	}

}
