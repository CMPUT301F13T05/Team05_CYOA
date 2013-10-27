package com.uofa.adventure_app.model;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import com.uofa.adventure_app.interfaces.JSONEncode;

public class Author implements JSONEncode {
	
	private String name;
	private UUID uid;
	
	/**
	 * Generates an Empty Author. A uid is always generated.
	 */
	public Author() {
		super();
		this.setUid(UUID.randomUUID());
	}
	
	/**
	 * Creates an author with name and uid.
	 * @param name
	 */
	public Author(String name) {
		super();
		this.name = name;
		this.setUid(UUID.randomUUID());
	}

	/**
	 * Set the name of the Author
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	private void setUid(UUID uid) {
		this.uid = uid;
	}
	
	/**
	 * Get the name of the author
	 * @return
	 */
	public String name() {
		return this.name;
	}
	
	/**
	 * Get the Unique id of the Author
	 * @return
	 */
	public UUID uid() {
		return this.uid;
	}

	/**
	 * Place in your model and when needed will be called by JSON
	 * @return
	 * 		The JSON object
	 */
	public JSONObject encodeJSON() {
		
		JSONObject object = new JSONObject();
		try {
			object.put("name", this.name());
			object.put("uid", this.uid().toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
		
	}
	
	/**
	 * The model will decode the JSON object for itself
	 * @param object
	 * 		Object from JSON
	 */
	public void decodeJSON(JSONObject object) {
		// Objects will be null if this fails.
		String newName = null;
		UUID newUid = null;
		
		try {
			newName = object.getString("name");
			String uidString = object.getString("uid");
			newUid = UUID.fromString(uidString);
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setName(newName);
		this.setUid(newUid);
	}
	
}
