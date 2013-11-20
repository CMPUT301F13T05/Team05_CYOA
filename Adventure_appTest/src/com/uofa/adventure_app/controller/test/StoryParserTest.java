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
import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.StoryParser;
import com.uofa.adventure_app.model.Story;
import android.test.ActivityInstrumentationTestCase2;

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
		
		// need example of a story to parse!
		//stories = p.parseStory(null);
		//assertNotNull(stories);
		
		// NEED AN EXAMPLE FOR THIS
		//stories = p.parseStory("This is my example of a story that has been parsed?!");
		assertNotNull(stories);
	}

}// class end
