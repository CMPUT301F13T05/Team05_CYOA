package com.uofa.adventure_app.model.test;

import java.util.ArrayList;

import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.StoryTitle;
import com.uofa.adventure_app.model.User;

import junit.framework.TestCase;

public class StoryTitleTest extends TestCase {
	public static void testSetup()
	{
		
	}
	public static void testCleanup()
	{
		
	}
	public void testStoryTitle()
	{
		StoryTitle testStoryTitle = new StoryTitle();
		// test if a storyTitle was made
		assertNotNull(testStoryTitle);
		// test if it has all of it's components initialized
		//assertEquals("", testStoryTitle.title());
		assertNotNull(testStoryTitle.title());
		assertNotNull(testStoryTitle.users());
		assertNotNull(testStoryTitle.id());
		
		// test if title setter/getter works
		testStoryTitle.setTitle("The Burito in my Breeches");
		assertEquals("The Burito in my Breeches", testStoryTitle.title());
		
		// building a testUser arrayList
		User testUser1 = new User();
		User testUser2 = new User();
		User testUSer3 = new User();
		ArrayList<User> testList = new ArrayList<User>();
		testList.add(testUser1);
		testList.add(testUser2);
		testList.add(testUSer3);
		
		// testing if adding a userArraylist works
		testStoryTitle.setUsers(testList);
		assertSame("Should be the same", testList, testStoryTitle.users());
		
		User testUser4 = new User("Tommy");
		// testing if adding an individual user works
		testStoryTitle.addUser(testUser4);
		assertTrue(testStoryTitle.users().contains(testUser4));
		
		Story testStoryTitle2 = new Story();
		// testing if setId works
		testStoryTitle.setId(testStoryTitle2.id());
		assertEquals("Two different stories with the same ID -- BAD!", testStoryTitle.id(), testStoryTitle2.id());
	
	}
}
