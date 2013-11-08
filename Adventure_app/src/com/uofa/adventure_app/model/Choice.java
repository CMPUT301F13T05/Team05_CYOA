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

import org.json.JSONObject;

import android.app.Fragment;


// is fragement a consisten typo in our project?
// shouldn't it be fragment or does that conflict with built-in Android?!

public class Choice {
	
	private Fragement fragment;
	
	/**
	 * Creates a choice with the Specific {@link Fragement}
	 * @param frag 
	 */
	public Choice(Fragement frag) {
		this.fragment = frag;
	}
	

	/**
	 * Sets the Choice to a specific {@link Fragement}
	 * @param frag
	 */
	public void setChoice(Fragement frag) {
		this.fragment = frag;
	}

	// getter
	/**
	 * Returns the Fragement that the choice is pointing to
	 * @return Fragement
	 */
	public Fragement getChoice() {
		 return this.fragment;
	}
	
}
