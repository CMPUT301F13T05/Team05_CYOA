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
import com.uofa.adventure_app.controller.StoryController;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

import android.test.ActivityInstrumentationTestCase2;

/**
 * @author Joel
 * See documentation for StoryController 
 */
public class StoryControllerTest extends
		ActivityInstrumentationTestCase2<BrowserActivity>
{

	private StoryController testStoryController;
	private Story testStory = new Story();
	ArrayList<Story> testStories = null;
	
	/**
	 * @param name
	 */
	public StoryControllerTest()
	{
		super(BrowserActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		
		testStoryController = new StoryController();
		//testStory = new ArrayList<Story>();
	}
	
	public void testAddStory()
	{
		testStoryController.addStory(testStory);
		testStories = testStoryController.getStories();
		assertTrue(testStories.contains(testStory));
	}
	
	public void testReplaceStory()
	{
		User testUser4 = new User("Tommy");
		// testing if adding an individual user works
		testStory.addUser(testUser4);
		testStoryController.replaceStory(testStory);
	}
	
	public void testGetStories(){
		//testStories.clear();
		testStories = testStoryController.getStories();
		assertNotNull(testStories);
	}
	
} // class end
