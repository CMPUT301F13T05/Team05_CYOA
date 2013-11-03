package com.uofa.adventure_app.elastic;

import java.util.ArrayList;
import java.util.Collection;

public class ElasticSearchResponseAll<T> {
	String _index;
	String _type;
	String _id;
	int _version;
	boolean exists;
	T fields;
	double max_score;
	int took;
	boolean timed_out;
	transient Object _shards;
	HitsAll<T> hits;

	public Collection<ElasticSearchResponseAll<T>> getHits() {
		return hits.getHits();
	}

	
	public Collection<T> getFields() {
		Collection<T> out = new ArrayList<T>();
		for (ElasticSearchResponseAll<T> essrt : getHits()) {
			out.add(essrt.getField());
		}
		return out;
	}

	public String toString() {
		return (super.toString() + ":" + took + "," + _shards + "," + exists
				+ "," + hits);
	}


	
	public T getField() {
		return fields;
	}
}