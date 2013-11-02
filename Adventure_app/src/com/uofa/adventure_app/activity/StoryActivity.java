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
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;


import com.uofa.adventure_app.R;

public class StoryActivity extends Activity implements AdventureActivity {
	TextView testtitle;
	TextView testAuthor;
	TextView testBody;
	boolean choice;
	View currentView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story);
		testtitle = (TextView) findViewById(R.id.titleview);
		testAuthor = (TextView) findViewById(R.id.authorview);
		testBody = (TextView) findViewById(R.id.storyview);
		testBody.setText("This story isn't very good.");
		testAuthor.setText("Author 1 edited by User 1");
		testtitle.setText("Story 1");
		currentView = this.findViewById(android.R.id.content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.story, menu);
		return true;
	}

	// We want to create a context Menu when the user long click on an item
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;

		
		// Style our context menu
		menu.setHeaderIcon(android.R.drawable.ic_input_get);
		menu.setHeaderTitle("Options");
		MenuInflater inflater = getMenuInflater();
		if (choice == false){
			// Open Menu
			inflater.inflate(R.menu.annotatemenu, menu);
		}else{
			inflater.inflate(R.menu.choices, menu);
			choice = false;
		}
		
	}
	public void openAnnotateContext(View v) {
		
		registerForContextMenu( v );
        openContextMenu( v );  
	}
	
	public void openChoices(View v) {
		choice = true;
		registerForContextMenu( v );
        openContextMenu( v );  
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
			case R.id.editstory:
				editStory();
				break;
			case R.id.annotatem:			
				openAnnotateContext(currentView);
				break;
			case R.id.editfragment:
				editFragment();
				break;
			case R.id.quit:
				browseView();
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void editStory() {
		Intent myIntent = new Intent(this, EditStoryActivity.class);
		this.startActivity(myIntent);
	}
	
	public void editFragment(){
		Intent myIntent = new Intent(this, EditFragementActivity.class);
		this.startActivity(myIntent);
	}
	
	public void browseView(){
		Intent myIntent = new Intent(this, BrowserActivity.class);
		this.startActivity(myIntent);
	}

}
