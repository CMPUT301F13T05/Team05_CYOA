package com.uofa.adventure_app.elastic;

import java.util.Collection;


public class HitsAll<T> {
    int total;
    double max_score;
    Collection<ElasticSearchResponseAll<T>> hits;
    public Collection<ElasticSearchResponseAll<T>> getHits() {
        return hits;
    }
    public String toString() {
        return (super.toString()+","+total+","+max_score+","+hits);
    }
}