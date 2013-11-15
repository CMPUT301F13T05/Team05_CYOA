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
import java.util.SortedMap;

import org.json.JSONObject;

public class Fragement  {
	
	private SortedMap<Integer, Media> media; // Key will be a line number
	private ArrayList<Annotation> annotations;
	private ArrayList<Choice> choices;
	private String body;

	public Fragement() {
		super();
		this.annotations = new ArrayList<Annotation>();
		this.choices = new ArrayList<Choice>(); 
		this.media = null;

	}	
	
	public Fragement(String body) {
		this();
		this.body = body;
	}	
	
	/**
	 * adds a choice to the fragment
	 * @param choice
	 */
	public void addChoice(Choice choice) {
		this.choices.add(choice);
	}
	

}
