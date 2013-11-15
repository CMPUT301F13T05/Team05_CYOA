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
package com.uofa.adventure_app.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Timer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uofa.adventure_app.elastic.ElasticSearchResponse;
import com.uofa.adventure_app.model.Story;
import java.util.TimerTask;

public class StoryParser {

	public ArrayList<Story> parseStory(String parseString) {
		System.out.println(parseString);
		ArrayList<Story> stories = new ArrayList<Story>();
		stories.clear();
		if (parseString != null) {
			Gson gson = new Gson();
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>() {
			}.getType();
			ElasticSearchResponse<Story> esResponse = gson.fromJson(
					parseString, elasticSearchResponseType);
			if (esResponse.getHits() != null) {
				for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
					if(s != null) {
						//s.getObject().setIsLocal(false);
						stories.add(s.getObject());
					}
				}
			}
		}
		return stories;

	}


}

