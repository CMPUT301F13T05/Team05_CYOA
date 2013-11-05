package com.uofa.adventure_app.model.test;

import java.util.ArrayList;

import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

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
		//assertEquals("", testStory.title());
		assertNotNull(testStory.title());
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
		
		User testUser4 = new User("Tommy");
		// testing if adding an individual user works
		testStory.addUser(testUser4);
		assertTrue(testStory.users().contains(testUser4));
		
		// TODO
		// testing if setFragments works
		Story testStory2 = new Story();
		
		// testing if setId works
		testStory.setId(testStory2.id());
		assertEquals("Two different stories with the same ID --BAD", testStory.id(), testStory2.id());
	
	}
	
	
}
