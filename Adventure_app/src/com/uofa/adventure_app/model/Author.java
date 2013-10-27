package com.uofa.adventure_app.model;

import java.util.UUID;

public class Author {
	
	private String name;
	private UUID uid;
	
	/**
	 * Generates an Empty Author. A uid is always generated.
	 */
	public Author() {
		super();
		this.uid = UUID.randomUUID();
	}
	
	/**
	 * Creates an author with name and uid.
	 * @param name
	 */
	public Author(String name) {
		super();
		this.name = name;
		this.uid = UUID.randomUUID();
	}

	/**
	 * Set the name of the Author
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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

}
