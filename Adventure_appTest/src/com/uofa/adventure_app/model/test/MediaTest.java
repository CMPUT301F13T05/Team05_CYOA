package com.uofa.adventure_app.model.test;

import com.uofa.adventure_app.model.Media;

import junit.framework.TestCase;

public class MediaTest extends TestCase {

	public static void testSetup()
	{
		
	}
	
	public static void testCleanup()
	{
		
	}
	
	public void testMedia()
	{
		Media testMedia = new Media();
		// test if Media was made.
		assertNotNull(testMedia);
	}
	
	public void testGetMediaType()
	{
		fail("getter for mediatype within Media not implemented");
		// test if a mediatype was defined
		//assertNotNull(testMedia.getMediaType());
	}
	
	public void testGetMediaPath()
	{
		fail("getter for path within Media not implemented");
		// test if a path was defined
		//assertNotNull(testMedia.getPath());
	}
			
	public void testSetMediaType()
	{
		fail("setter for mediaType within Media not implemented");
		// test if setting/getting Media works
		//testMedia.setMediaType();
		//assertEquals(, testMedia.getMediaType());
	}
			
	public void testSetMediaPath()
	{
		fail("setter for path within Media not implemented");
		// test if setting/getting of a Path works
		// testMedia.setPath();
		// assertEquals(, testMedia.getPath());
	}			
}
