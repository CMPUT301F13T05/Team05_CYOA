package com.uofa.adventure_app.model.test;

import java.util.UUID;

import com.uofa.adventure_app.model.Annotation;
import com.uofa.adventure_app.model.Fragement;
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
	
	public void testSetAnnotation() {
		User testUser = new User("Whoppledinger");
		String imageString = new String("A string that represents a picture....");
		String annotationString = new String("TestTest String");
		Annotation testAnnotation = new Annotation(testUser);
		testAnnotation.setAnnotationPic(imageString);
		assertEquals(imageString,testAnnotation.media());
		
		testAnnotation.setAnnotationString(annotationString);
		assertEquals(annotationString, testAnnotation.annotationString());
		
		UUID uid = UUID.randomUUID();
		testAnnotation.setUid(uid);
		assertEquals(uid,testAnnotation.uid());
		
		assertEquals(testUser,testAnnotation.user());
		
		User newUser = new User("Another User");
		testAnnotation.setUser(newUser);
		assertEquals(newUser, testAnnotation.user());
		
	}
	
	public void testAnnotationUser() {
		
		// Test initial user
		User user1 = new User("Name");
		Annotation testAnnotation = new Annotation(user1);
		assertEquals(user1,testAnnotation.user());
		
		// test setting new user
		User newUser = new User("Another User");
		testAnnotation.setUser(newUser);
		assertEquals(newUser, testAnnotation.user());
	}
	
	public void testAnnotationImage() {
		Annotation testAnnotation = new Annotation(new User("Name"));
		String imageString = new String("A string that represents a picture....");
		testAnnotation.setAnnotationPic(imageString);
		assertEquals(imageString,testAnnotation.media());
	}
	
	public void testAnnotationString() {
		User testUser = new User("Whoppledinger");
		String annotationString = new String("TestTest String");
		Annotation testAnnotation = new Annotation(testUser);
		
		testAnnotation.setAnnotationString(annotationString);
		assertEquals(annotationString, testAnnotation.annotationString());
		
	}
	
}
