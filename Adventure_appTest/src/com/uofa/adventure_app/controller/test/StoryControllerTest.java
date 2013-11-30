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

import android.test.ActivityInstrumentationTestCase2;

import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.StoryController;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

/**
 * @author Joel, Chris
 * See documentation for StoryController 
 */
public class StoryControllerTest extends
		ActivityInstrumentationTestCase2<BrowserActivity>
{

	private StoryController testStoryController;
	private Story testStory = new Story();
	ArrayList<Story> testStories = null;
	Fragement testFragement = null;
	
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
		// Empty Fragement
		testFragement = new Fragement();
		testStory.addFragement(testFragement);
		//testStory = new ArrayList<Story>();
	}
	
	public void testAddStory()
	{
		testStoryController.addStory(testStory);
		testStories = testStoryController.stories();
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
		testStories = testStoryController.stories();
		assertNotNull(testStories);
	}
	
	public void testCurrentStory(){
		testStoryController.setCurrentStory(testStory);
		Story returnedStory = testStoryController.currentStory();
		
		System.out.println(testStory.id().toString());
		System.out.println(returnedStory.id().toString());
		System.out.println(returnedStory.equals(testStory));
		
		assertTrue(returnedStory.equals(testStory));
	}
	
	public void testCurrentFragement(){
		testStoryController.setCurrentFragement(testFragement);
		Fragement returnedFragement = testStoryController.currentFragement();
		assertTrue(returnedFragement.equals(testFragement));
	}
	
	public void testAddPreviousFragement(){
		testStoryController.addPreviousFragement(testFragement);
		// Add and Remove Fragement
		
		Fragement returnedFragement = testStoryController.lastFragement();
		assertTrue(returnedFragement.equals(testFragement));
		
		// Remove Last Fragement List Should be empty
		testStoryController.popPreviousFragement();
		
		// See if it is null.
		assertNull(testStoryController.lastFragement());
	}
	
	public void testPreviousFragementList(){
		testStoryController.addPreviousFragement(testFragement);
		ArrayList<Fragement> fragementList = testStoryController.previousFragementList();
		assertTrue(fragementList.contains(testFragement));
	}
	
	public void testSetStories() {
		ArrayList<Story> stories = new ArrayList<Story>();
		Story story1 = new Story();
		story1.setTitle("Title 1");
		Story story2 = new Story();
		story2.setTitle("Title 2");
		stories.add(story1);
		stories.add(story2);
		
		testStoryController.setStories(stories);
		ArrayList<Story> returnedStories = testStoryController.stories();
		
		assertTrue(returnedStories.equals(stories));
	}
	
} // class end
