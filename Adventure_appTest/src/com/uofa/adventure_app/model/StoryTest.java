package com.uofa.adventure_app.model;

import java.util.ArrayList;

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
		// test if a story was made
		assertNotNull(testStory);
		// test if it has all of it's components initialized
		assertEquals("", testStory.title());
		assertNotNull(testStory.users());
		assertNotNull(testStory.id());
		assertNotNull(testStory.getFragements());
		
		// test if title setter/getter works
		testStory.setTitle("The Bandit in my Breeches");
		assertEquals("The Bandit in my Breeches", testStory.title());
		
		// building a testUser arrayList
		User testUser1 = new User();
		User testUser2 = new User();
		User testUSer3 = new User();
		ArrayList<User> testList = new ArrayList<User>();
		testList.add(testUser1);
		testList.add(testUser2);
		testList.add(testUSer3);
		
		// testing if adding a userArraylist works
		testStory.setUsers(testList);
		assertSame("Should be the same", testList, testStory.users());
		
		Story testStory2 = new Story();
		User testUser4 = new User("Tommy");
		// testing if adding an individual user works
		testStory2.addUser(testUser4);
		assertEquals("Tommy", testStory2.users());
		
	}
	
	
}
