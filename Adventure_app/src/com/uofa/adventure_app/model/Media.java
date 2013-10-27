package com.uofa.adventure_app.model;

import org.json.JSONObject;

import com.uofa.adventure_app.enums.MediaType;
import com.uofa.adventure_app.interfaces.JSONEncode;

public class Media implements JSONEncode {
	
	private String path;
	private MediaType mediaType;
	
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
