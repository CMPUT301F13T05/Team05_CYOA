package com.uofa.adventure_app.interfaces;

import java.util.UUID;

public abstract class UniqueId {
	private UUID uid;
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

}
