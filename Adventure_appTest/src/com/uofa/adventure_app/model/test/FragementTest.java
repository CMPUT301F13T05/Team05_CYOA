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
	}// testFragment end
	
	public void testGetFragmentMedia()
	{
		fail("No getter implemented for getFragment");
		// test if media was defined
		//assertNotNull(testFragement.getMedia());
	}

	public void testFragmentGetAnnotation()
	{
		fail("No getter for annotation list for Fragment");
		// test if annotations were defined
		//assertNotNull(testFragement.getAnnotations());
	}
	
	public void testFragmentChoices()
	{
		fail("No getter for choice list for Fragment");
		// test if choices were defined
		//assertNotNull(testFragement.getChoices();
	}

	public void testFragmentSetMedia()
	{
		fail("No setter for media within Fragment");
		// test if setting/getting media works
		//testFragement.setMedia();
		//assertEquals(, testFragement.getMedia());
	}

	public void testFragmentSetAnnotation(){
		fail("No setter for annotations within Fragment");
		// test if setting/getting of a annotations works
		// testFragement.setAnnotations();
		// assertEquals(, testFragement.getAnnotaions());
	}
	
	public void testFragmentSetChoice(){
		fail("No setter for choices within Fragment");
		// test if adding a choice works
		// testFragement.addChoice(testChoice);
		// assertTrue(testFragement.getChoices.contains(testChoice));
	}
}
