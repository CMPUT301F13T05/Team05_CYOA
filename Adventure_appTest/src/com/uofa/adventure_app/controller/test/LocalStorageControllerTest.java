/*
Adventure App - Allows you to create an Adventure Book, or Download
	books from other authors.
Copyright (C) Fall 2013 Team 5 CMPUT 301 University of Alberta

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.uofa.adventure_app.controller.test;

import android.test.ActivityInstrumentationTestCase2;
import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.LocalStorageController;
import com.uofa.adventure_app.model.Story;

/**
 * @author Joel
 * See documentation for LocalStorageController
 */
public class LocalStorageControllerTest extends 
					ActivityInstrumentationTestCase2<BrowserActivity>
{
	private LocalStorageController testLocalStorageController;
	
	public LocalStorageControllerTest()
	{
		super(BrowserActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		// get a DbHelper
		//testLocalStorageController.DBHelper = new DbHelper(this.getActivity());
		// get a LocalStorageController within browserActivity
		testLocalStorageController = new LocalStorageController(this.getActivity());
	}
	
	// set a new story
	// public int setStory(String title, String user, UUID story)
	public void testSetStory()
	{
		// get a writeable database?
		testLocalStorageController = testLocalStorageController.openForWrite();
		
		// write to the database?
		testLocalStorageController.setStory("1","Man in the middle", "4","Joel");
		
		// close the connection to the database
		testLocalStorageController.close();
	}
	
	public void testGetStory() 
	{
		String storyTitle;
		testLocalStorageController = testLocalStorageController.openForRead();
		
		storyTitle = testLocalStorageController.getStory("1").get(0).get(0);
		assertNotNull(storyTitle);
		
		// assert that this is the same as what was put there!
		assertEquals("Man in the middle", storyTitle);
		
		testLocalStorageController.close();
	}
	
	// also tests getTitle
	public void testInsertIntoStoriesTable()
	{
		testLocalStorageController = testLocalStorageController.openForWrite();
		testLocalStorageController.insertIntoStoriesTable("7", "John Handcock");
		testLocalStorageController.close();
		
		testLocalStorageController = testLocalStorageController.openForRead();
		assertEquals("John Handcock", testLocalStorageController.getTitle("7"));
		testLocalStorageController.close();
	}

	// also tests getUsers
	public void testInsertIntoUsersTable()
	{
		testLocalStorageController = testLocalStorageController.openForWrite();
		testLocalStorageController.insertIntoUsersTable("4", "Joel", "7");
		testLocalStorageController.close();
		
		testLocalStorageController = testLocalStorageController.openForRead();
		//getUsers(story_id,"s");
		//String userName = testLocalStorageController.getUsers("7", "s").get(1);
		assertEquals("Joel", testLocalStorageController.getUsers("7", "s").get(0)); // 0 or 1 for get?
		testLocalStorageController.close();
	}
	
	// also tests getFragment
	public void testInsertIntoFragmentsTable()
	{
		testLocalStorageController = testLocalStorageController.openForWrite();
		// insertIntoFragmentsTable(String fragmentId, String text, String story_id, String title, Integer flag)
		testLocalStorageController.insertIntoFragmentsTable("1", "Melons are yummy fruits","7", "Melons", 1);
		testLocalStorageController.close();
		
		testLocalStorageController = testLocalStorageController.openForRead();
		//String userName = testLocalStorageController.getUsers("7", "s").get(1); // 0 or 1 for get?
		// I'm not sure if these methods are working, it's all rather confusing.
		// assertEquals("Melons are a yummy fruits",testLocalStorageController.getStory("7").get(3).get(0));
		assertEquals("Melons are yummy fruits", testLocalStorageController.getFragment("1").get(2).get(0));
		testLocalStorageController.close();
	}
	
	// also tests getMedia
	public void testInsertIntoMediaTable()
	{
		testLocalStorageController = testLocalStorageController.openForWrite();
		// (String mediaId, String pointer, boolean isAnnotation, int fragment_id)
		testLocalStorageController.insertIntoMediaTable("1", "pointer1", false, 1);
		testLocalStorageController.close();
		
		testLocalStorageController = testLocalStorageController.openForRead();
		//String userName = testLocalStorageController.getUsers("16", "s").get(1);
		fail("there is nothing to get images out of database table.");
		//assertTrue(testLocalStorageController.getUsers("16", "testStory").contains("Melon"));
		testLocalStorageController.close();
	}
	
	// choices
	public void testInsertIntoChoicesTable()
	{
		testLocalStorageController = testLocalStorageController.openForWrite();
		// there is no way to set fragment id's manually?!
		// insertIntoChoicesTable(String fragment_id,String choice_id)
		testLocalStorageController.insertIntoChoicesTable("1", "1"); // setting recursive fragment choice!
		testLocalStorageController.close();
		
		testLocalStorageController = testLocalStorageController.openForRead();
		//String userName = testLocalStorageController.getUsers("16", "s").get(1);
									// gets an array of choice id's, get gets the first one, getFragment gets the fragment arraylist, get gets the firs
		assertEquals("Melons", testLocalStorageController.getFragment(testLocalStorageController.getChoices("1").get(0)).get(0).get(0));
		testLocalStorageController.close();
	}
	
	public void testExtras()
	{
		Story testStory = new Story();
		testLocalStorageController = testLocalStorageController.openForWrite();
		testLocalStorageController.cacheStory(testStory);
	}

} // class close

//Testing layout


//testLocalStorageController.getStory(1);
//HashMap<String, List<String>> newMap = testLocalStorageController.getBrowserViewInfo();
//newMap.get(4).get(0);
////Toast.makeText(this, localStorageController.getChoices(1).get(2)+"", 2).show();
//testLocalStorageController.openForWrite();
//testLocalStorageController.insertIntoStoriesTable();
//testLocalStorageController.insertIntoUsersTable(name, storyId, 1, test);
//testLocalStorageController.insertIntoUsersTable(3, "Kevin", 3);
//testLocalStorageController.insertIntoUsersTable(4, "Joel", 2);
//testLocalStorageController.insertIntoUsersTable(5, "Ulvi", 2);
//testLocalStorageController.insertIntoUsersTable(6, "Ulvi", 4);
//testLocalStorageController.insertIntoChoicesTable(1, 1);
//testLocalStorageController.insertIntoChoicesTable(3, 1);
//testLocalStorageController.insertIntoFragmentsTable(1, "test fragment", 1);
//testLocalStorageController.insertIntoImagesTable(3, "pointer1", false, 3);
//testLocalStorageController.close();
//
//Cursor c=localStorageController.openForRead().db.rawQuery("select * from images where is_annotation=1", null);
//c.moveToFirst();		
//mydb.rawQuery("select DISTINCT tbl_name from sqlite_master", null);
//
/*if (c != null ) {
	if  (c.moveToFirst()) {
		do {

			String one = c.getString(0);
			Toast.makeText(this, one, 2).show();
			String two = c.getString(1);
			Toast.makeText(this, two, 2).show();
			String three = c.getString(2);
			Toast.makeText(this, three, 2).show();
			String four = c.getString(3);
			Toast.makeText(this, four, 2).show();
		}while (c.moveToNext());
	}
}  
c.close();
//String title = c.getString(0);
localStorageController.close();


//public void testGetStory()
//@Test
//public void testGetDbHelper()
//{
//	//fail("getDbHelper not implemented yet");
//	//DbHelper myDbHelper = DbHelper.getDbHelper(this.getActivity());
//}
//	
* 
*/
