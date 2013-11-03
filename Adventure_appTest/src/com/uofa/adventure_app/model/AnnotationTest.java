package com.uofa.adventure_app.model;

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
		
		// test if a Annotationtype was defined
		//assertNotNull(testAnnotation.getAnnotationType());
		// test if a path was defined
		//assertNotNull(testAnnotation.getPath());
		
		// test if setting/getting Annotation works
		//testAnnotation.setAnnotationType();
		//assertEquals(, testAnnotation.getAnnotationType());
		
		// test if setting/getting of a Path works
		// testAnnotation.setPath();
		// assertEquals(, testAnnotation.getPath());
		
	}
}
