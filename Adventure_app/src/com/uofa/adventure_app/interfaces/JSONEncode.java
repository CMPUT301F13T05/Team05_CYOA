package com.uofa.adventure_app.interfaces;

import org.json.JSONObject;

public interface JSONEncode {
	
	
	/**
	 * Place in your model and when needed will be called by JSON
	 * @return
	 * 		The JSON object
	 */
	public JSONObject encodeJSON();
	
	/**
	 * The model will decode the JSON object for itself
	 * @param object
	 * 		Object from JSON
	 */
	public void decodeJSON(JSONObject object);
}
