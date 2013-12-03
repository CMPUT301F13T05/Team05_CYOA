package com.uofa.adventure_app.model.test;

import java.net.URL;

import junit.framework.TestCase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.uofa.adventure_app.model.Media;

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
	public void testSetMedia(){
		String testString = new String("Kevin");
		
		Media testMedia = new Media();
		testMedia.setMedia(testString);
		assertEquals(testMedia.getMedia(), testString);
		
		
	}
	public void testImage(){
		Bitmap bm = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
		String encodedImage = Media.encodeToBase64(bm);
		Bitmap bmDec = Media.decodeBase64(encodedImage);
		assertEquals(bm.getHeight(), bmDec.getHeight());
		
	}
	public void resizeTest(){
		Bitmap bm = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
		bm = Media.resizeImage(bm);
		assertEquals(bm.getHeight(), Media.IMAGE_MAX);
	}
	
	
}
