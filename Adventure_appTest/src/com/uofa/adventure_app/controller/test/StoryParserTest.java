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
package com.uofa.adventure_app.controller.test;

import java.util.ArrayList;
import java.util.UUID;

import android.test.ActivityInstrumentationTestCase2;

import com.google.gson.Gson;
import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.StoryParser;
import com.uofa.adventure_app.model.Choice;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

/**
 * @author Joel
 * See documentation for StoryParser 
 */

// A few questions for this method.
// In which activity is it normally called?
// can you give an example of what it would be called upon.

public class StoryParserTest extends
		ActivityInstrumentationTestCase2<BrowserActivity>
{
	
	public StoryParserTest()
	{
		super(BrowserActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
	}
	
	public void testStoryParse()
	{
		StoryParser p = new StoryParser();
		ArrayList<Story> stories = new ArrayList<Story>();
		
		// Create "Fake Story"
		Story s = new Story(UUID.fromString("3312036f-d0fa-4a59-9b1b-614d156de296"));
		
		// Sample input string, we are seeing if we get a story object back, hard to
		// check if proper fragement ect..
		// Just Checks story id for now seems to be working.
		String parseString = "{\"took\":0,\"timed_out\":false,\"_shards\":{\"total\":5,\"successful\":5,\"failed\":0},\"hits\":{\"total\":1,\"max_score\":1.0,\"hits\":[{\"_index\":\"cmput301f13t05\",\"_type\":\"story\",\"_id\":\"3312036f-d0fa-4a59-9b1b-614d156de296\",\"_score\":1.0, \"_source\" : {\"fragements\":[{\"annotations\":[],\"body\":\"Enter Body Text for Your First Fragement Here.\",\"choices\":[],\"media\":[],\"title\":\"Your First Fragement\",\"randomFlag\":false,\"uid\":\"53b9c7ae-44a5-488e-bd09-7d7a7af3eeb3\"}],\"id\":\"3312036f-d0fa-4a59-9b1b-614d156de296\",\"users\":[{\"name\":\"test\",\"uid\":\"b6d60578-2732-4c89-9cab-a19c7f5bf0bd\"}],\"startFragement\":{\"annotations\":[],\"body\":\"Enter Body Text for Your First Fragement Here.\",\"choices\":[],\"media\":[],\"title\":\"Your First Fragement\",\"randomFlag\":false,\"uid\":\"53b9c7ae-44a5-488e-bd09-7d7a7af3eeb3\"},\"title\":\"Your Story Title\",\"isLocal\":false}}]}}";
		stories = p.parse(parseString);
		System.out.println(stories);
		assertNotNull(stories);

		// Get our only story at index 0
		Story parsedStory = stories.get(0);
		
		assertEquals(s,parsedStory);
	}

}// class end
