package com.uofa.adventure_app.controller.test;

import org.junit.Before;
import org.junit.Test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;

import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.LocalStorageController;
import com.uofa.adventure_app.enums.DbHelper;
import com.uofa.adventure_app.model.Story;


public class LocalStorageControllerTest extends 
					ActivityInstrumentationTestCase2<BrowserActivity> {

	LocalStorageController testLocalStorageController = null;
	private static Context context;
	private static DbHelper myDbHelper;
	private SQLiteDatabase testDb;
	
	public LocalStorageControllerTest() {
		super(BrowserActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		// get a DbHelper
		myDbHelper = new DbHelper(context);
		// clear the database
		//testDb.delete(myDbHelper.DATABASE_NAME, null, null);
		
		testLocalStorageController = new LocalStorageController(context);
	}
	
	// set a new story
	// public int setStory(String title, String user, UUID story)
	
	public void testSetStory()
	{
		Story testStory = new Story();
		testStory.setTitle("The Grumpy Goose");
		
		testDb = myDbHelper.getWritableDatabase();
		
		// get a writeable database?
		testLocalStorageController = testLocalStorageController.openForWrite();
		// write to the database?
<<<<<<< HEAD
		testLocalStorageController.setStory("Man in the middle", "Papa Joel");
=======
		testLocalStorageController.setStory("1","Man in the middle", "Papa Joel");
>>>>>>> AppTest
		// test if the database has a storyID
		
		// close the connection to the database?
		testLocalStorageController.close();
	}
	
	//Testing


	//localStorageController.getStory(1);
	//HashMap<Integer, List<String>> newMap =localStorageController.getBrowserViewInfo();
	//newMap.get(4).get(0);
	//Toast.makeText(this, localStorageController.getChoices(1).get(2)+"", 2).show();
	//localStorageController.openForWrite();
	//localStorageController.insertIntoStoriesTable(4, "test", "This is test");
	//localStorageController.insertIntoUsersTable(2, "Chris", 2);
	//localStorageController.insertIntoUsersTable(3, "Kevin", 3);
	//localStorageController.insertIntoUsersTable(4, "Joel", 2);
	//localStorageController.insertIntoUsersTable(5, "Ulvi", 2);
	//localStorageController.insertIntoUsersTable(6, "Ulvi", 4);
	//localStorageController.insertIntoChoicesTable(1, 1);
	//localStorageController.insertIntoChoicesTable(3, 1);
	//localStorageController.insertIntoFragmentsTable(1, "test fragment", 1);
	//localStorageController.insertIntoImagesTable(3, "pointer1", false, 3);
	//localStorageController.close();
	//
	//Cursor c=localStorageController.openForRead().db.rawQuery("select * from images where is_annotation=1", null);
	//c.moveToFirst();		
	//mydb.rawQuery("select DISTINCT tbl_name from sqlite_master", null);
	
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
//	public void testGetDbHelper()
//	{
//		//fail("getDbHelper not implemented yet");
//		//DbHelper myDbHelper = DbHelper.getDbHelper(this.getActivity());
//	}
//		
 * 
 */
}
