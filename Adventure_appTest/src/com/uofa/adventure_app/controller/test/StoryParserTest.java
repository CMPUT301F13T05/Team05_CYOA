package com.uofa.adventure_app.controller.test;

import java.util.ArrayList;

import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.StoryParser;
import com.uofa.adventure_app.model.Story;

import android.test.ActivityInstrumentationTestCase2;

/**
 * @author Joel
 *
 */

// A few questions for this method.
// In which activity is it normally called?
// can you give an example of what it would be called upon.

public class StoryParserTest extends
		ActivityInstrumentationTestCase2<BrowserActivity> {

	/**
	 * @param name
	 */
	public StoryParserTest() {
		super(BrowserActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
	}
	
	public void testStoryParse(){
		
		StoryParser p = new StoryParser();
		ArrayList<Story> stories = new ArrayList<Story>();
		
		// need example of a story to parse!
		//stories = p.parseStory(null);
		//assertNotNull(stories);
		
		// NEED AN EXAMPLE FOR THIS
		//stories = p.parseStory("This is my example of a story that has been parsed?!");
		assertNotNull(stories);
	}

}
