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
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;

public class EditStoryActivity extends AdventureActivity {
	private ArrayAdapter<Fragement> adapter;
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_story);
		list = (ListView) findViewById(R.id.fragments);
		
		EditText titleEditText = (EditText) findViewById(R.id.titletext);
		titleEditText.setText(AdventureApplication.getStoryController().currentStory().title());
		
		TextView authorView = (TextView) findViewById(R.id.authortext);
		authorView.setText(AdventureApplication.getStoryController().currentStory().users().toString());
		
		adapter = new ArrayAdapter<Fragement>(this,
				R.layout.list_item, AdventureApplication.getStoryController().currentStory().getFragements());
		list.setAdapter(adapter);
		this.registerForContextMenu(list);
		list.setOnItemClickListener( new OnItemClickListener() {
			 
		       // @Override
		        public void onItemClick(AdapterView<?> a, View v, int i, long l) {
		        	Fragement currFrag = adapter.getItem(i);
		        	AdventureApplication.getStoryController().setCurrentFragement(currFrag);
		    		v.showContextMenu();
		        }
		 });
		titleEditText.addTextChangedListener(new GenericTextWatcher(titleEditText));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_story, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Handle presses on the action bar items
		switch (item.getItemId()) {
			case R.id.newfragment:
				newFragment();
				break;
			case R.id.help:
				String helpText = new String();
				helpText="Touch New fragement to add an empty fragement to the story\n\n";
				helpText=helpText+"Choose fragement you want to edit\n\n";
				Toast.makeText(this, helpText, Toast.LENGTH_LONG).show();
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
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
		
		// Open Menu
		inflater.inflate(R.menu.editstorymenu, menu);
		
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
			case R.id.editfrag:
				editFragment();
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}
	

	
	public void openContext(View v) {
		registerForContextMenu( v );
	}
	
	public void editFragment(){
		Intent myIntent = new Intent(this, EditFragementActivity.class);
		this.startActivity(myIntent);
	}
	
	public void newFragment(){
		Fragement frag = new Fragement();
		frag.setTitle("New Fragment");
		AdventureApplication.getStoryController().currentStory().addFragement(frag);

		adapter.notifyDataSetChanged();
		updateView();

	}	
	
	public void updateView(){
		adapter.notifyDataSetChanged();
	}

	@Override
	public void dataReturn(ArrayList<Story> result, String method) {
		// TODO Auto-generated method stub
		
	}
	
	 protected void openLastFragement() {
		 // TODO: Implement
	    }
	 
		protected void saveTextForView(View v, String text) {
			switch (v.getId()) {
			case R.id.titletext:
				AdventureApplication.getStoryController().currentStory()
						.setTitle(text);
				break;
			default:
				break;
			}

			AdventureApplication.getActivityController().update();

		}

		
		
}
