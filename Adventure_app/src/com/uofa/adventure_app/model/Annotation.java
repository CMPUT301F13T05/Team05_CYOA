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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.uofa.adventure_app.interfaces.UniqueId;
/**
 * Part of the Model contains all of the getters and setters to create
 * an annotation object.
 * @author Kevin Lafond, Chris Pavlicek
 *
 */

public class Annotation extends UniqueId implements Serializable {

	private static final long serialVersionUID = 1L;

	// the video, audio, or image 
	private String media;
	// same as user
	private User user;
	private String annotationString;
	
	/**
	 * creates annotation Object
	 * @param author
	 */
	public Annotation(User user) {
		super();
		this.user = user;
	}
	
	/**
	 * Sets the annotation picture
	 * @param String annotation
	 */
	public void setAnnotationPic(String annotation){
		this.media = annotation;
	}

	/**
	 * Always treat de-serialization as a full-blown constructor, by validating
	 * the final state of the de-serialized object.
	 */
	private void readObject(ObjectInputStream aInputStream)
			throws ClassNotFoundException, IOException {
		// always perform the default de-serialization first
		aInputStream.defaultReadObject();

	}

	/**
	 * This is the default implementation of writeObject. Customise if
	 * necessary.
	 */
	private void writeObject(ObjectOutputStream aOutputStream)
			throws IOException {
		// perform the default serialization for all non-transient, non-static
		// fields
		aOutputStream.defaultWriteObject();
	}


	/**
	 * returns the string of the annotation.
	 * @return StringannotationString
	 */
	public String annotationString() {
		return annotationString;
	}


	/**
	 * sets the string of the annotation.
	 * @param String annotationString
	 */
	public void setAnnotationString(String annotationString) {
		this.annotationString = annotationString;
	}


	/**
	 * returns the creator of the annoataion.
	 * @return User user
	 */
	public User user() {
		return user;
	}


	/**
	 * sets the current user as the user that created the annotation.
	 * @param User user
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * Returns the image attached to the annotation object
	 * @return String 
	 */
	public String media() {
		return media;
	}


}
