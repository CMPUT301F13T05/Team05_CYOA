package com.uofa.adventure_app.model.test;

import java.util.UUID;

import com.uofa.adventure_app.model.User;

import junit.framework.TestCase;

public class UserTest extends TestCase {

	public static void testSetup()
	{
		
	}
	
	public static void testCleanup()
	{
		
	}
	
	public void testUser()
	{
		
		User testUser = new User();
		// test if a user was made.
		assertNotNull(testUser);
		// test if a Uid was made
		assertNotNull(testUser.uid());
		
		// test if setting/getting a User name works
		testUser.setName("Joel");
		assertEquals("Joel", testUser.getName());
		
		User testUserName = new User("Joel");
		// test if testUserName was made
		assertNotNull(testUserName);
		
		// test if testUserName is the same user as testUser
		assertNotSame(testUser, testUserName);
		
		//test if they have the same Uid's
		assertNotSame(testUser.uid(), testUserName.uid());
	}
	
}
