package com.uofa.adventure_app.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.test.ActivityInstrumentationTestCase2;

import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.LocalStorageController;
import com.uofa.adventure_app.model.Story;


public class LocalStorageControllerTest extends 
					ActivityInstrumentationTestCase2<BrowserActivity> {

	private LocalStorageController testLocalStorageController;
	//private static Context context;
	//private static DbHelper myDbHelper;
	//private SQLiteDatabase testDb;
	
	public LocalStorageControllerTest() {
		super(BrowserActivity.class);
	}

	protected void setUp() throws Exception {
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
		Story testStory = new Story();
		testStory.setTitle("The Grumpy Goose");
		
		// get a writeable database?
		testLocalStorageController = testLocalStorageController.openForWrite();
		
		// write to the database?
		testLocalStorageController.setStory("1","Man in the middle", "Joel");
		
		// close the connection to the database
		testLocalStorageController.close();
	}
	
	public void testGetStory() {
		//Story testStory = new Story();
		LocalStorageController tlc = new LocalStorageController(this.getActivity());;
		List<List<String>> storyTime = new ArrayList<List<String>>();
		String storyTitle;
		// get a writeable database?
		tlc = tlc.openForRead();
		
		storyTime = tlc.getStory("1");
		
		// not working?
		storyTitle = tlc.getStory("1").get(0).get(0);
		
		assertNotNull(storyTitle);
		// assert that this is the same as what was put there!
		// testLocalStorageController.setStory("1","Man in the middle", "Joel");
		assertEquals("Man in the middle", storyTitle);
		
		// close the connection to the database
		tlc.close();
	}
	
	public void testInsertIntoStoriesTable() {
		
		testLocalStorageController = testLocalStorageController.openForWrite();
		
		testLocalStorageController.insertIntoStoriesTable("7", "John Handcock");
		
		testLocalStorageController.close();
		
		testLocalStorageController = testLocalStorageController.openForRead();
		
		assertEquals("John Handcock", testLocalStorageController.getTitle("7"));
		
		testLocalStorageController.close();
	}
	
	public void testInsertIntoUsersTable(){
		
		testLocalStorageController = testLocalStorageController.openForWrite();
		
		testLocalStorageController.insertIntoUsersTable("Melon", "7", 1, "testStory");
		
		testLocalStorageController.close();
		
		testLocalStorageController = testLocalStorageController.openForRead();
		
		//String userName = testLocalStorageController.getUsers("16", "s").get(1);
		
		//assertTrue(testLocalStorageController.getUsers("7", "testStory").contains("Melon"));
	}
	
	//fragments
	public void testInsertIntoFragmentsTable(){
		
		testLocalStorageController = testLocalStorageController.openForWrite();
		
		testLocalStorageController.insertIntoFragmentsTable("Melon's are a yummy fruit", "7", "Fruit");
		
		testLocalStorageController.close();
		
		testLocalStorageController = testLocalStorageController.openForRead();
		
		//String userName = testLocalStorageController.getUsers("16", "s").get(1);
		
		// I'm not sure if these methods are working, it's all rather confusing.
		assertEquals("Melon's are a yummy fruit",testLocalStorageController.getStory("7").get(3).get(0));
	}
	
	// images
	public void testInsertIntoImagesTable(){
		
		testLocalStorageController = testLocalStorageController.openForWrite();
		
		testLocalStorageController.insertIntoImagesTable(1, "pointer1", false, 1);
		
		testLocalStorageController.close();
		
		testLocalStorageController = testLocalStorageController.openForRead();
		
		//String userName = testLocalStorageController.getUsers("16", "s").get(1);
		fail("there is nothing to get images out of database table.");
		//assertTrue(testLocalStorageController.getUsers("16", "testStory").contains("Melon"));
	}
	
	// choices
	public void testInsertIntoChoicesTable(){
		
		testLocalStorageController = testLocalStorageController.openForWrite();
		
		// there is no way to set fragment id's manually?!
		testLocalStorageController.insertIntoChoicesTable(1, 1);
		
		testLocalStorageController.close();
		
		testLocalStorageController = testLocalStorageController.openForRead();
		
		//String userName = testLocalStorageController.getUsers("16", "s").get(1);
		
		assertSame(1,testLocalStorageController.getChoices(1).get(0));
	}
	
	
	
	//Testing


//	testLocalStorageController.getStory(1);
//	HashMap<String, List<String>> newMap = testLocalStorageController.getBrowserViewInfo();
//	newMap.get(4).get(0);
//	//Toast.makeText(this, localStorageController.getChoices(1).get(2)+"", 2).show();
//	testLocalStorageController.openForWrite();
//	testLocalStorageController.insertIntoStoriesTable();
//	testLocalStorageController.insertIntoUsersTable(name, storyId, 1, test);
//	testLocalStorageController.insertIntoUsersTable(3, "Kevin", 3);
//	testLocalStorageController.insertIntoUsersTable(4, "Joel", 2);
//	testLocalStorageController.insertIntoUsersTable(5, "Ulvi", 2);
//	testLocalStorageController.insertIntoUsersTable(6, "Ulvi", 4);
//	testLocalStorageController.insertIntoChoicesTable(1, 1);
//	testLocalStorageController.insertIntoChoicesTable(3, 1);
//	testLocalStorageController.insertIntoFragmentsTable(1, "test fragment", 1);
//	testLocalStorageController.insertIntoImagesTable(3, "pointer1", false, 3);
//	testLocalStorageController.close();
//	
//	Cursor c=localStorageController.openForRead().db.rawQuery("select * from images where is_annotation=1", null);
//	c.moveToFirst();		
//	mydb.rawQuery("select DISTINCT tbl_name from sqlite_master", null);
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
//	public void testGetDbHelper()
//	{
//		//fail("getDbHelper not implemented yet");
//		//DbHelper myDbHelper = DbHelper.getDbHelper(this.getActivity());
//	}
//		
 * 
 */
}
