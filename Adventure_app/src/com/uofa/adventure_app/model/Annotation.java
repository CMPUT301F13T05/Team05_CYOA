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
