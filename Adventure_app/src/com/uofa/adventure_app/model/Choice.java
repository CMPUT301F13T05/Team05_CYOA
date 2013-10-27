package com.uofa.adventure_app.model;

import org.json.JSONObject;

import com.uofa.adventure_app.interfaces.JSONEncode;

public class Choice implements JSONEncode {
	
	private Fragement fragement;
	
	/**
	 * Place in your model and when needed will be called by JSON
	 * @return
	 * 		The JSON object
	 */
	// TODO: implement
	public JSONObject encodeJSON() {
		JSONObject object = new JSONObject();
		return object;
	}
	
	/**
	 * The model will decode the JSON object for itself
	 * @param object
	 * 		Object from JSON
	 */
	// TODO: implement
	public void decodeJSON(JSONObject object) {
		// do nothing...
	}
}
