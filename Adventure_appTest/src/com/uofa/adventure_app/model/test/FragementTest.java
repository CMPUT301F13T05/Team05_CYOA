package com.uofa.adventure_app.model.test;

import java.util.ArrayList;

import com.uofa.adventure_app.model.Fragement;

import junit.framework.TestCase;

public class FragementTest extends TestCase {

	public static void testSetup()
	{
		
	}
	
	public static void testCleanup()
	{
		
	}
	
	public void testFragement()
	{
//		Media testMedia = new Media();
//		User testUser = new User("Whoppledinger");
//		
//		Annotation testAnnotation = new Annotation(testUser);
//		Annotation testAnnotation2 = new Annotation(testUser);
//		ArrayList<Annotation> annotationList = new ArrayList<Annotation>();
//		annotationList.add(testAnnotation);
//		annotationList.add(testAnnotation2);
//		
//		Fragement testFrag = new Fragement();
//		Fragement testFrag2 = new Fragement();
//		Choice testChoice = new Choice(testFrag);
//		Choice testChoice2 = new Choice(testFrag2);
//		ArrayList<Choice> choiceList = new ArrayList<Choice>();
//		choiceList.add(testChoice);
//		choiceList.add(testChoice2);
		
		Fragement testFragement = new Fragement();
		// test if a user was made.
		assertNotNull(testFragement);
		
		// test if media was defined
		//assertNotNull(testFragement.getMedia());
		// test if annotations were defined
		//assertNotNull(testFragement.getAnnotations());
		// test if choices were defined
		//assertNotNull(testFragement.getChoices();
		
		// test if setting/getting media works
		//testFragement.setMedia();
		//assertEquals(, testFragement.getMedia());
		
		// test if setting/getting of a annotations works
		// testFragement.setAnnotations();
		// assertEquals(, testFragement.getAnnotaions());
		
		// test if adding a choice works
		// testFragement.addChoice(testChoice);
		// assertTrue(testFragement.getChoices.contains(testChoice));
		
	}
	
	
}
