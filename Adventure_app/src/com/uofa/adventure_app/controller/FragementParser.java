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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uofa.adventure_app.elastic.ElasticSearchResponse;
import com.uofa.adventure_app.interfaces.Parser;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;

public class FragementParser implements Parser<Story> {

	public ArrayList<Story> parseFragement(String parseString) {
		
		//System.out.println("This one: " + parseString);
		
		// Blank story to trick datareturn
		ArrayList<Story> stories = new ArrayList<Story>();
		Story s = new Story();
		stories.add(s);
		if (parseString != null) {
			Gson gson = new Gson();
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Fragement>>() {
			}.getType();
			ElasticSearchResponse<Fragement> esResponse = null;
			try {
				
				esResponse = gson.fromJson(
					parseString, elasticSearchResponseType);
			if (esResponse.getHits() != null) {
				for (ElasticSearchResponse<Fragement> f : esResponse.getHits()) {
					if(f != null) {
						s.addFragement(f.getObject());
					}
				}
			}
			} catch (Exception E) {
			}
			}
		
		return stories;

	}

	@Override
	public ArrayList<Story> parse(String string) {
		return this.parseFragement(string);
	}


}

