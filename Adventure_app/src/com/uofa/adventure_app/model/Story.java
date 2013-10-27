package com.uofa.adventure_app.model;

import java.util.ArrayList;
import java.util.SortedMap;

import org.json.JSONObject;

import com.uofa.adventure_app.interfaces.JSONEncode;

public class Story implements JSONEncode {

	private String title;
	private ArrayList<Author> authors; 
	private ArrayList<Fragement> fragements;
	
	/**
	 * Place in your model and when needed will be called by JSON
	 * @return
	 * 		The JSON object
	 */
	public JSONObject encodeJSON() {
		JSONObject object = new JSONObject();
		return object;
	}
	
	/**
	 * The model will decode the JSON object for itself
	 * @param object
	 * 		Object from JSON
	 */
	public void decodeJSON(JSONObject object){
		
	}
	
}
