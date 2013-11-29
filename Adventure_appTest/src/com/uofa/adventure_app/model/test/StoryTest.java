package com.uofa.adventure_app.model.test;

import java.util.ArrayList;
import java.util.UUID;

import junit.framework.TestCase;

import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

public class StoryTest extends TestCase {

	public static void testSetup()
	{
		
	}
	public static void testCleanup()
	{
		
	}
	
	public void testStoryTitle() {
		Story s = new Story();
		s.setTitle("Title");
		assertEquals(s.title(),"Title");
	}
	
	public void testStorySetUsers() {
		Story s = new Story();
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("Name"));
		users.add(new User("Name2"));
		users.add(new User("Nam3"));
		
		s.setUsers(users);
		assertEquals(s.users(),users);
	}
	
	public void testStoryAddUser() {
		Story s = new Story();
		User u = new User("Username");
		s.addUser(u);
		assertTrue(s.users().contains(u));
	}
	
	public void testStorySetFragement() {
		Story story = new Story();
		ArrayList<Fragement> fragements = new ArrayList<Fragement>();
		fragements.add(new Fragement());
		fragements.add(new Fragement());
		fragements.add(new Fragement());
		fragements.add(new Fragement());
		
		story.setFragements(fragements);
		assertEquals(story.getFragements(),fragements);
	}
	
	public void testAddFragement() {
		Story s = new Story();
		Fragement f = new Fragement();
		s.addFragement(f);
		assertTrue(s.getFragements().contains(f));
	}
	
	public void testIsLocal() {
		Story s = new Story();
		s.setIsLocal(true);
		assertTrue(s.isLocal());
	}
	
	public void testSetId() {
		Story s = new Story();
		UUID newId = UUID.randomUUID();
		s.setId(newId);
		assertEquals(s.id(),newId);
	}
	
	public void testSetStartFragement() {
		Story s = new Story();
		Fragement f = new Fragement();
		s.addFragement(f);
		s.setStartFragement(f);
		assertEquals(s.startFragement(),f);
	}
	
	public void testLocalCopy() {
		Story s = new Story();
		s.addUser(new User());
		Fragement f = new Fragement();
		f.setTitle("title");
		s.addFragement(f);
		s.setStartFragement(f);
		Story copy = s.localCopy();
		assertNotSame(copy,s);
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
