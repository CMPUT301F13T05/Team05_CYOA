package com.uofa.adventure_app.interfaces;

import java.util.UUID;

public abstract class UniqueId {
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
	private void setUid(UUID uid) {
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
	
}
