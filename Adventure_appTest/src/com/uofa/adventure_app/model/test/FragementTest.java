package com.uofa.adventure_app.model.test;

import java.util.ArrayList;
import java.util.UUID;

import junit.framework.TestCase;

import com.uofa.adventure_app.model.Annotation;
import com.uofa.adventure_app.model.Choice;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Media;
import com.uofa.adventure_app.model.User;

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
	
	public void testFragment() {
		Fragement newFragement = new Fragement();
		assertNotNull(newFragement);
	}
	
	public void testFragmentFlag() {
		Fragement newFragement = new Fragement(true);
		assertEquals(true,newFragement.getRandomflag());
	}
	
	public void testFragementUuid() {
		UUID uid = UUID.randomUUID();
		Fragement newFragement = new Fragement(uid);
		assertEquals(uid,newFragement.uid());
	}
	
	public void testFragmentBodyFlag() {
		String s = new String("This is a String");
		Fragement newFragement = new Fragement(s,true);
		assertEquals(s,newFragement.body());
		assertEquals(true,newFragement.getRandomflag());
		
	}
	
	public void testFragmentTileBodyFlag() {
		String t = new String("Title");
		String b = new String("Body");
		boolean flag = true;
		Fragement newFragement = new Fragement(t,b,flag);
		assertEquals(t,newFragement.getTitle());
		assertEquals(b,newFragement.body());
		assertEquals(flag,newFragement.getRandomflag());
	}
	
	public void testAddChoice() {
		Fragement f = new Fragement();
		Choice c = new Choice(new Fragement());
		f.addChoice(c);
		assertTrue(f.choices().contains(c));
	}
	
	public void testBody() {
		Fragement f = new Fragement();
		String body = new String("Body text!");
		f.setBody(body);
		assertEquals(body,f.body());
	}
	
	public void testSetChoices() {
		Fragement f = new Fragement();
		
		Choice c1 = new Choice(new Fragement());
		Choice c2 = new Choice(new Fragement());
		Choice c3 = new Choice(new Fragement());
		
		ArrayList<Choice> choices = new ArrayList<Choice>();
		choices.add(c1);
		choices.add(c2);
		choices.add(c3);
		
		f.setChoices(choices);
		assertTrue(f.choices().containsAll(choices));
	}
	
	
	public void testSetRandomFlag() {
		Fragement f = new Fragement();
		f.setRandomFlag(true);
		assertTrue(f.getRandomflag());
	}
	
	public void testSetTitle() {
		Fragement f = new Fragement();
		String title = new String("A Title");
		f.setTitle(title);
		assertEquals(title,f.getTitle());
	}
	
	public void testAddMedia() {
		Fragement f = new Fragement();
		Media m = new Media();
		m.setMedia("String of media");
		
		f.addMedia(m);
		assertTrue(f.media().contains(m));
	}
	
	public void testSetMedia() {
		ArrayList<Media> media = new ArrayList<Media>();
		Media m1 = new Media();
		Media m2 = new Media();
		Media m3 = new Media();
		media.add(m1);
		media.add(m2);
		media.add(m3);
		
		Fragement f = new Fragement();
		f.setMedia(media);
		
		assertTrue(f.media().containsAll(media));
	}
	
	public void testLocalCopy() {
		Fragement f = new Fragement();
		Annotation a = new Annotation(new User());
		f.addAnnotation(a);
		f.addChoice(new Choice(new Fragement()));
		
		Fragement fcopy = f.localCopy();
		assertFalse(f.equals(fcopy));
	}
	
	public void testAddAnnotation() {
		Fragement f = new Fragement();
		Annotation a = new Annotation(new User());
		f.addAnnotation(a);
		assertTrue(f.annotations().contains(a));
	}
	
	public void testReplaceAnnotation() {
		Fragement f = new Fragement();
		Annotation a = new Annotation(new User());
		f.addAnnotation(a);
		Annotation newAnnotation = new Annotation(new User());
		newAnnotation.setUid(a.uid());
		
		f.replaceAnnotation(newAnnotation);
		assertTrue(f.annotations().contains(newAnnotation));
		assertTrue(f.annotations().contains(a));
	}
	public void testSetAnnotations() {
		Fragement f = new Fragement();
		Annotation a1 = new Annotation(new User());
		Annotation a2 = new Annotation(new User());
		Annotation a3= new Annotation(new User());
		ArrayList<Annotation> annotations = new ArrayList<Annotation>();
		annotations.add(a1);
		annotations.add(a2);
		annotations.add(a3);
		f.setAnnotations(annotations);
		assertTrue(f.annotations().containsAll(annotations));
	}
	
	public void testStripFragement() {
		Fragement f = new Fragement();
		Annotation a = new Annotation(new User());
		Media m = new Media();
		f.setBody("body");
		f.addMedia(m);
		f.addAnnotation(a);
		Fragement striped = f.stripFragement();
		assertNotSame(striped.body(),"body");
		assertFalse(striped.media().contains(m));
		assertFalse(striped.annotations().contains(a));
		assertTrue(striped.uid().equals(f.uid()));
		
	}
	
}
