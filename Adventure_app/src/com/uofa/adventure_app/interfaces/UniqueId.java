package com.uofa.adventure_app.interfaces;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

public abstract class UniqueId implements Serializable {
	protected UUID uid;
	public UniqueId() {
		this.setUid(UUID.randomUUID());
	}
	/**
	 * @return the uid
	 */
	public UUID uid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	//
	public void setUid(UUID uid) {
		this.uid = uid;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		//UniqueId ui = (UniqueId) o;
		if(o.getClass().equals(this.getClass())) {
			UniqueId currentObject = (UniqueId) o;
			if (this.uid().equals(currentObject.uid()))
				return true;
			else
				return false;
		}
		return false;
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
	
}
