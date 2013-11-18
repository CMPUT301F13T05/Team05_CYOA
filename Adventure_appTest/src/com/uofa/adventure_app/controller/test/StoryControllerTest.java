package com.uofa.adventure_app.controller.test;

import java.util.ArrayList;

import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.StoryController;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

import android.test.ActivityInstrumentationTestCase2;

/**
 * @author Joel
 *
 */
public class StoryControllerTest extends
		ActivityInstrumentationTestCase2<BrowserActivity> {

	private StoryController testStoryController;
	private Story testStory = new Story();
	ArrayList<Story> testStories = null;
	
	/**
	 * @param name
	 */
	public StoryControllerTest() {
		super(BrowserActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
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
	
	
}
