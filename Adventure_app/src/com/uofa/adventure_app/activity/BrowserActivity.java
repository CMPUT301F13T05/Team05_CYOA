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
package com.uofa.adventure_app.activity;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.GridView;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.controller.http.HttpObjectStory;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Choice;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

public class BrowserActivity extends AdventureActivity {
	private ArrayAdapter<String> storyGridAdapter;
	ArrayList<String> List;
	GridView grid;

	User username;
	View v;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_browser);
		v = this.findViewById(android.R.id.content);
		username = new User();
//	    boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
//	    if (firstrun){
//	    	System.out.println(v);
//	    	//openFirstContext(v);
//		    System.out.println("here");
//	    // Save the state
//	    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
//	        .edit()
//	        .putBoolean("firstrun", false)
//	        .commit();
//	    }
	    setContentView(R.layout.activity_browser);
		 //TESTING
		Story tStory = new Story();
		tStory.addUser(new User("Chris"));
		tStory.setTitle("This is A TITLE");
		tStory.addFragement(new Fragement());
		Fragement tFrag = new Fragement();
		tFrag.addChoice(new Choice(new Fragement()));
		tFrag.addChoice(new Choice(new Fragement()));
		tFrag.addChoice(new Choice(new Fragement()));
		tFrag.addChoice(new Choice(new Fragement()));
		tFrag.addChoice(new Choice(new Fragement()));
		tStory.addFragement(tFrag);
		
		// Search Example See Log of output.
		
		//AdventureApplication.getWebServiceController().publish(tStory);
		//AdventureApplication.getWebServiceController().publish(tStory);
		HttpObjectStory httpStory = new HttpObjectStory();
		
		// This method will get all, and call the all method in dataReturn()..
		
		
		// This method will call the get method in dataReturn() when done loading...
		this.httpRequest(httpStory.searchObject("Chris"), GET_METHOD);

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.new_story:
			newStory();
			break;
		case R.id.refresh:
			System.out.println("Here before call");
			HttpObjectStory httpStory = new HttpObjectStory();
			this.httpRequest(httpStory.fetchAll(), GET_ALL_METHOD);
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}
	public void newStory() {

		Intent myIntent = new Intent(this, EditFragementActivity.class);
		this.startActivity(myIntent);
		//Testing
		//LocalStorageController localStorageController = new LocalStorageController(this);
		//Toast.makeText(this, localStorageController.getStory(1).get(3).get(0), 2).show();
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
		*/

	}
	
	public void viewStory(View v) {
		
		Intent myIntent = new Intent(this, StoryActivity.class);
		this.startActivity(myIntent);
	}
	
	public void updateView(){
		
	}

	@Override
	public void dataReturn(ArrayList<Story> result, String method) {
		if(method.equals(GET_ALL_METHOD)) {
			System.out.println("We got some data here!");
			// Need to parse the Data, or Maybe I will change this to an array always..?
			ArrayList<String> strings = new ArrayList<String>();
			for(Story s: result) {
				if(s != null) {
				strings.add(s.title());
				}
			}
			for(int i = 0; i <strings.size(); i++) {
				if(strings.get(i).equals("")) {
					strings.remove(i);
				}else 
					if (strings.get(i).equals(" ")){
						strings.remove(i);
					}

				
			}
			System.out.println("0: " + strings.get(0)+"/");
			


			

			grid = (GridView) findViewById(R.id.gridView1);


			strings.add("" + strings.size());
			storyGridAdapter = new ArrayAdapter<String>(this,
					R.layout.list_item, strings);
			grid = (GridView) findViewById(R.id.gridView1);
			grid.setAdapter(storyGridAdapter);
			grid.setOnItemClickListener(new 
			GridView.OnItemClickListener() {
			       // @Override
			        public void onItemClick(AdapterView<?> a, View v, int i, long l) {
			         
			        	viewStory(v);
			          
			        }
			    });
			 
			System.out.println(result);

		}
		if(method.equals(GET_METHOD)) {
			System.out.println("We got some data here!");
			// Need to parse the Data, or Maybe I will change this to an array always..?
			for(Story s: result) {
				System.out.println(s.title());
			}
		}
	}
	// We want to create a context Menu when the user long click on an item
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
			AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;

		
			// Style our context menu
			menu.setHeaderIcon(android.R.drawable.ic_input_get);
			menu.setHeaderTitle("Please enter your Name:");
			MenuInflater inflater1 = getMenuInflater();

			// Open Menu
			inflater1.inflate(R.menu.firstcontext, menu);

		
	}
	public void openFirstContext(View v) {
		
		registerForContextMenu( v );
        openContextMenu( v );  
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.ok:
			
			break;

		default:
			return super.onContextItemSelected(item);
	}
	return super.onContextItemSelected(item);
		
	}
	
}
