package com.uofa.adventure_app.model;

import junit.framework.TestCase;

public class StoryTest extends TestCase {

	public static void testSetup()
	{
		
	}
	public static void testCleanup()
	{
		
	}
	public void testStory()
	{
		Story testStory = new Story();
		// make sure a story was made
		assertNotNull(testStory);
		// make sure it has all of it's components inisalized
		assertEquals("", testStory.title());
		assertNotNull(testStory.users());
		assertNotNull(testStory.id());
		assertNotNull(testStory.getFragements());
		
		
		
	}
	
	
}
