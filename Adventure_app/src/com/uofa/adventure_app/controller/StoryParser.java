package com.uofa.adventure_app.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uofa.adventure_app.elastic.ElasticSearchResponse;
import com.uofa.adventure_app.model.Story;

public class StoryParser {

	public ArrayList<Story> parseStory(String parseString) {
		ArrayList<Story> stories = new ArrayList<Story>();
		System.out.println(parseString);
		if (parseString != null) {
			Gson gson = new Gson();
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>() {
			}.getType();
			ElasticSearchResponse<Story> esResponse = gson.fromJson(
					parseString, elasticSearchResponseType);
			if (esResponse.getHits() != null) {
				for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
					if(s != null)
						stories.add(s.getObject());
				}
			}
		}
		return stories;

	}

}
