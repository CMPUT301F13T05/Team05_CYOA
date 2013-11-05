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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.controller.http.HttpObjectStory;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Choice;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

public class BrowserActivity extends AdventureActivity {
	private StoryGrid adapter;
	ArrayList<String> List;
	GridView grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		this.httpRequest(httpStory.fetchAll(), GET_ALL_METHOD);
		
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

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	public void newStory() {

		Intent myIntent = new Intent(this, EditFragementActivity.class);
		this.startActivity(myIntent);
		//Testing
		//LocalStorageController localStorageController = new LocalStorageController(this);
		//localStorageController.openForWrite();
		//localStorageController.insertToStoriesTable(4, "test", "This is test");
		//localStorageController.insertToAuthorsTable(2, "Chris", 2);
		//localStorageController.insertToAuthorsTable(3, "Kevin", 3);
		//localStorageController.insertToAuthorsTable(4, "Joel", 2);
		//localStorageController.insertToAuthorsTable(5, "Ulvi", 2);
		//localStorageController.insertToChoicesTable(1, 1);
		//localStorageController.insertToFragmentsTable(1, "test fragment", 1);
		//localStorageController.insertToImagesTable(3, "pointer1", false, 3);
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
			

			
			adapter = new StoryGrid(this, result);
			grid = (GridView) findViewById(R.id.gridView1);
			grid.setAdapter(adapter);

		}
		if(method.equals(GET_METHOD)) {
			System.out.println("We got some data here!");
			// Need to parse the Data, or Maybe I will change this to an array always..?
			for(Story s: result) {
				System.out.println(s.title());
			}
		}
	}

	
}
