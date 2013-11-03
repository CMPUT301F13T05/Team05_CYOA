package com.uofa.adventure_app.elastic;

import java.util.ArrayList;
import java.util.Collection;

public class ElasticSearchResponse<T> {
	String _index;
	String _type;
	String _id;
	int _version;
	boolean exists;
	T _source;
	double max_score;
	int took;
	boolean timed_out;
	transient Object _shards;
	Hits<T> hits;

	public Collection<ElasticSearchResponse<T>> getHits() {
		return hits.getHits();
	}

	public Collection<T> getSources() {
		Collection<T> out = new ArrayList<T>();
		for (ElasticSearchResponse<T> essrt : getHits()) {
			out.add(essrt.getSource());
		}
		return out;
	}

	public String toString() {
		return (super.toString() + ":" + took + "," + _shards + "," + exists
				+ "," + hits);
	}

	public T getSource() {
		return _source;
	}
}