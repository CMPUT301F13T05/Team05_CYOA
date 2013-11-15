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
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.controller.LocalStorageController;
import com.uofa.adventure_app.controller.http.HttpObjectStory;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

public class BrowserActivity extends AdventureActivity {


	private StoryGridAdapter storyGridAdapter;
	ArrayList<String> List;
	LocalStorageController localStorageController;
	User username;
	View v;
	TextView search;
	String searchQuery = "";
	private ArrayList<Story> stories = AdventureApplication.getStoryController().getStories();
	SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser);
		v = this.findViewById(android.R.id.content);
		localStorageController = new LocalStorageController(this);
		username = new User();

		boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
		
		GridView grid = (GridView) findViewById(R.id.gridView1);
		grid.setAdapter(storyGridAdapter);
		
		grid.setOnItemClickListener(new 
				GridView.OnItemClickListener() {
			// @Override
			public void onItemClick(AdapterView<?> a, View v, int i, long l) {					
				viewStory(v, stories.get(i));
			}
		});
		
		if (firstrun){
			Intent myIntent = new Intent(this, FirstRunOnlyActivity.class);
			this.startActivity(myIntent);
			// Save the state
			getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("firstrun", false).commit();
		}
		HttpObjectStory httpStory = new HttpObjectStory();
		this.httpRequest(httpStory.fetchAll(), GET_ALL_METHOD);

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		searchView = (SearchView) menu.findItem(R.id.search).getActionView();
		final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
		    @Override
		    public boolean onQueryTextChange(String newText) {
		        // Do something
		    	storyGridAdapter.filter(newText);
		        return true;
		    }

		    @Override
		    public boolean onQueryTextSubmit(String query) {
		        // Do something
		    	storyGridAdapter.filter(query);
		        return true;
		    }
		};
		searchView.setOnQueryTextListener(queryTextListener);
		
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.new_story:
			this.newStory();
			break;
		case R.id.refresh:
			HttpObjectStory httpStory = new HttpObjectStory();
			this.httpRequest(httpStory.fetchAll(), GET_ALL_METHOD);
			break;

		case R.id.search:

			//searchQuery = search.getQuery().toString();
		default:
			return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * called when the user clicks Create a new story.
	 * Creates and calls the intent that calls the Edit Fragment screen.
	 */
	public void newStory() {
		Intent myIntent = new Intent(this, EditFragementActivity.class);
		int i = 0;
		myIntent.putExtra("frag_id", i);
		this.startActivity(myIntent);
		

	}
	/**
	 * This creates and calls an intent to open the Activity that allows you to view the stories.
	 * 
	 * @param View v
	 * @param Story s
	 */
	public void viewStory(View v, Story s) {
		if(!s.isLocal()) {
			fetchStory(s);
		} else {
			this.openStory(s);
		}
	}
	
	public void fetchStory(Story s) {
		HttpObjectStory httpStory = new HttpObjectStory();
		this.httpRequest(httpStory.fetch(s.id()), GET_METHOD);
	}
	
	public void openStory (Story s) {
		Intent myIntent = new Intent(this, StoryActivity.class);
		String id = s.id().toString();
		myIntent.putExtra("StoryID", id);
		this.startActivity(myIntent);
	}
	
	/**
	 * Updates the view
	 */
	public void updateView(){

	}

	@Override
	/**
	 * 
	 * This is called every time the code checks json for an update.
	 * 
	 * @param String method
	 * @param ArrayList<Story> result
	 */
	public void dataReturn(ArrayList<Story> result, String method) {

		
		for(int i = 0; i<result.size(); i++ ) {
			if(result.get(i).isLocal() == false) 
				stories.add(result.get(i));
		}
		
		if(method.equals(GET_ALL_METHOD)) {
			HashMap<String, List<String>> map = new HashMap<String, List<String>>();
			LocalStorageController localStorageController = new LocalStorageController(this);
			map = localStorageController.getBrowserViewInfo();
			ArrayList<String> keys = new ArrayList<String>();
			keys.addAll(map.keySet());
			for(int i = 0; i<keys.size(); i++){
				Story s = new Story(UUID.fromString(keys.get(i)));
				ArrayList<String> list = new ArrayList<String>(); 
				
				list.addAll(map.get(keys.get(i)));
				s.setTitle(list.get(0));
				ArrayList<User> users = new ArrayList<User>();
				for(int j = 1; j<map.get(keys.get(i)).size(); j++){
					User user = new User(map.get(keys.get(i)).get(j));
					users.add(user);
				}
				
				s.setUsers(users);
				s.setIsLocal(true);
				stories.add(s);
			}
			System.out.println(stories);
			GridView grid = (GridView) findViewById(R.id.gridView1);
			grid.setAdapter(storyGridAdapter);
		}
		if(method.equals(GET_METHOD)) {
			System.out.println(result);
			Story currentStory = result.get(0);
			if(currentStory != null) {
				AdventureApplication.getStoryController().getStories().remove(currentStory);
				currentStory.setIsLocal(true);
				// Add story to local database
				// Add the Local DB version to controller
				// This is temp code below
				AdventureApplication.getStoryController().addStory(currentStory);
				
				openStory(currentStory);
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

}

