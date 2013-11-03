package com.uofa.adventure_app.model;

import java.util.UUID;

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
		// make sure a user was made.
		assertNotNull(testUser);
		// make sure a Uid was made
		assertNotNull(testUser.getUid());
		
		// make sure setting/getting a User name works
		testUser.setName("Joel");
		assertEquals("Joel", testUser.getName());
		
		User testUserName = new User("Joel");
		// make sure testUserName was made
		assertNotNull(testUserName);
		
		// make sure it is not the same user as testUser
		assertNotSame(testUser, testUserName);
		
		// make sure they don't have the same Uid's
		assertNotSame(testUser.getUid(), testUserName.getUid());
	}
	
}
