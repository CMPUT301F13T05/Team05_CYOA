package com.uofa.adventure_app.model.test;

import com.uofa.adventure_app.model.Annotation;
import com.uofa.adventure_app.model.User;

import junit.framework.TestCase;

public class AnnotationTest extends TestCase {
	
	public static void testSetup()
	{
		
	}
	
	public static void testCleanup()
	{
		
	}
	
	public void testAnnotation()
	{
		User testUser = new User("Whoppledinger");
		Annotation testAnnotation = new Annotation(testUser);
		
		// test if an Annotation was made.
		assertNotNull(testAnnotation);
	}
	
	public void testGetAnnotationType()
	{
		fail("No getter implemented for getAnnotationType");
		
		// test if a Annotationtype was defined
		//assertNotNull(testAnnotation.getAnnotationType());
	}
	
	public void testGetAnnotationPath()
	{
		fail("No getter implemented for getAnnotationPath");
		
		// test if a path was defined
		//assertNotNull(testAnnotation.getPath());
	}
	
	public void testSetAnnotation()
	{
		fail("No setter implemented for setAnnotation");
		
		// test if setting/getting Annotation works
		//testAnnotation.setAnnotationType();
		//assertEquals(, testAnnotation.getAnnotationType());
	}
	
	public void testSetAnnotationPath()
	{
		fail("No setter implemented for setAnnotationPath");
		
		// test if setting/getting of a Path works
		// testAnnotation.setPath();
		// assertEquals(, testAnnotation.getPath());
	}			

}// end of AnnotationTest
