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

import org.json.JSONException;
import org.json.JSONObject;

import com.uofa.adventure_app.interfaces.JSONEncode;

public class Annotation implements JSONEncode {
	
	// the video, audio, or image 
	private Media media;
	
	// same as user
	private Author author;

	/**
	 * Place in your model and when needed will be called by JSON
	 * @return
	 * 		The JSON object
	 */
	public JSONObject encodeJSON() {
		
		JSONObject object = new JSONObject();
		  try {
		    object.put("Annotation", "Annotation Image");
		  } catch (JSONException e) {
		    e.printStackTrace();
		  }
		  return object;
	}
	
	/**
	 * The model will decode the JSON object for itself
	 * @param object
	 * 		Object from JSON
	 */
	
	
	// TODO: implement this -- For now just prints something
	public void decodeJSON(JSONObject object){
		try {
			System.out.println(object.getJSONObject("Annotation"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
