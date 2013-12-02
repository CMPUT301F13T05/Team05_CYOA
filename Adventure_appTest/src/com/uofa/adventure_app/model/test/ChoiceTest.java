package com.uofa.adventure_app.model.test;

import com.uofa.adventure_app.model.Choice;
import com.uofa.adventure_app.model.Fragement;

import junit.framework.TestCase;

public class ChoiceTest extends TestCase {

	public static void testSetup()
	{
		
	}
	
	public static void testCleanup()
	{
		
	}
	
	public void testChoice()
	{
		Fragement testFrag = new Fragement();
		Choice testChoice = new Choice(testFrag);
		// test if a Choice was made.
		assertNotNull(testChoice);
		
		// test if a fragment was defined
		assertNotNull(testChoice.getChoiceId());
		
		Fragement testFrag2 = new Fragement();
		// test if setting/getting Choice works
		testChoice.setChoice(testFrag2);
		assertEquals(testFrag2, new Fragement(testChoice.getChoiceId()));
	}
	
	
}
