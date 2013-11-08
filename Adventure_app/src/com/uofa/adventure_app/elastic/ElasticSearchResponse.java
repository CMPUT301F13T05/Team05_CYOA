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
package com.uofa.adventure_app.elastic;

import java.util.ArrayList;
import java.util.Collection;

public class ElasticSearchResponse<T> {
	String _index;
	String _type;
	String _id;
	String ok;
	int _version;
	boolean exists;
	T _source;
	T fields;
	double max_score;
	int took;
	boolean timed_out;
	transient Object _shards;
	Hits<T> hits;
/**
 * Gets the hits
 * @return Collection<ElasticSearchResponse<T>>
 */
	public Collection<ElasticSearchResponse<T>> getHits() {
		return hits.getHits();
	}

	/**
	 * Gets the Fields from the Response
	 * @return Collection<T>
	 */
	public Collection<T> getFields() {
		if(hits != null) {
			Collection<T> out = new ArrayList<T>();
			for (ElasticSearchResponse<T> essrt : getHits()) {
				out.add(essrt.getObject());
			}
			return out;
		} 
		return new ArrayList<T>();
	}

	/**
	 * Returns the string format of the Response
	 * @return String
	 */
	public String toString() {
		return (super.toString() + ":" + took + "," + _shards + "," + exists
				+ "," + hits);
	}

	/**
	 * Returns the Source
	 * @return T
	 */
	public T getSource() {
		return _source;
	}
	
	/**
	 * Returns the Field parameted
	 * @return T
	 */ 
	public T getField() {
		return fields;
	}
	
	/**
	 * Gets the Object
	 * @return T
	 */
	public T getObject() {
		if(_source != null) {
			return this.getSource();
		} else {
			return this.getField();
		}
	}

}