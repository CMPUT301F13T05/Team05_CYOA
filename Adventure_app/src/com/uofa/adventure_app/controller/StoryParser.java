package com.uofa.adventure_app.controller;



import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uofa.adventure_app.elastic.ElasticSearchResponse;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.StoryTitle;

public class StoryParser {
	
	public ArrayList<Story> parseStory(String parseString) {
		ArrayList<Story> stories = new ArrayList<Story>();
		  Gson gson = new Gson();
		  Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>(){}.getType();
          ElasticSearchResponse<Story> esResponse = gson.fromJson(parseString, elasticSearchResponseType);
          for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
        	 stories.add(s.getObject());
          }
		return stories;
	}
	
	public ArrayList<StoryTitle> parseStoryTitle(String parseString) {
		ArrayList<StoryTitle> stories = new ArrayList<StoryTitle>();
		  Gson gson = new Gson();
		  Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<StoryTitle>>(){}.getType();
          ElasticSearchResponse<StoryTitle> esResponse = gson.fromJson(parseString, elasticSearchResponseType);
          System.err.println(esResponse);
          for (ElasticSearchResponse<StoryTitle> s : esResponse.getHits()) {
                  StoryTitle story = s.getObject();
                  stories.add(story);
                  System.err.println(story);
          }
		return stories;
	}

}
