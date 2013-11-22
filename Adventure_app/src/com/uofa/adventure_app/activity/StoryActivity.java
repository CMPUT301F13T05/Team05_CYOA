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
import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.TextView;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.controller.LocalStorageController;
import com.uofa.adventure_app.controller.StoryController;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;

public class StoryActivity extends AdventureActivity {
	TextView testtitle;
	TextView testAuthor;
	TextView testBody;
	boolean choice;
	View currentView;
	Uri imageFileUri;
	Story currentStory;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID storyID = null;
		setContentView(R.layout.activity_story);
		testtitle = (TextView) findViewById(R.id.titleview);
		testAuthor = (TextView) findViewById(R.id.authorview);
		testBody = (TextView) findViewById(R.id.storyview);
		LocalStorageController localStorageController = new LocalStorageController(this);
		Bundle extras = getIntent().getExtras();
		List<List<String>> storyInfo =new ArrayList<List<String>>();
		if (extras != null){
			storyID = UUID.fromString(extras.getString("StoryID"));
			String s_id = storyID.toString();
			currentStory = new Story(UUID.fromString(extras.getString("StoryID")));
			ArrayList<Story> stories = AdventureApplication.getStoryController().getStories();
			currentStory = stories.get(stories.indexOf(currentStory));
			if (currentStory.isLocal()){ 
				//System.out.print(localStorageController.getStory(s_id).get(0).get(0) + "--------------------------------------------------------");
				testBody.setText(localStorageController.getStory(s_id).get(3).get(0));
				
			}else{
				testBody.setText(currentStory.getFragements().get(0).body());
				currentStory.setIsLocal(true);
				AdventureApplication.getStoryController().replaceStory(currentStory);
				AdventureApplication.getActivityController().update();
			}
			testAuthor.setText(currentStory.users().toString());
			testtitle.setText(currentStory.title());
			
		}else{
			testBody.setText("This story isn't very good.");
			testAuthor.setText("");
			testtitle.setText("Sorry the story Could not be loaded");
		}
		currentView = this.findViewById(android.R.id.content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.story, menu);
		return true;
	}

	// We want to create a context Menu when the user long clicks on an item
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		if (choice == false){
			AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;

		
			// Style our context menu
			menu.setHeaderIcon(android.R.drawable.ic_input_get);
			menu.setHeaderTitle("Annotate");
			MenuInflater inflater1 = getMenuInflater();

			// Open Menu
			inflater1.inflate(R.menu.annotatemenu, menu);
		}else{
			choice = false;
			AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;

			
			// Style our context menu
			menu.setHeaderIcon(android.R.drawable.ic_input_get);
			menu.setHeaderTitle("Choices");
			MenuInflater inflater2 = getMenuInflater();
			inflater2.inflate(R.menu.choices, menu);

		}
		
	}
	/**
	 * Called to open the context view that allows the user to open
	 * the camera or browse their own pictures.
	 * 
	 * @param View v
	 */
	public void openAnnotateContext(View v) {
		
		registerForContextMenu( v );
        openContextMenu( v );  
	}
	/**
	 *  Opens a context view with a list of choices for the user to 
	 *  move on in the story.
	 * @param View v
	 */
	public void openChoices(View v) {
		currentView.getRootView().dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
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
				currentView.getRootView().dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
				openAnnotateContext(currentView);
				//takeAPhoto();
				break;
			case R.id.editfragment:
				editFragment();
				break;
			case R.id.copy:
				Story newStory = new Story(UUID.randomUUID());
				newStory.setIsLocal(true);
				LocalStorageController localStoryController = new LocalStorageController(this);
				newStory.setTitle(currentStory.title());
				localStoryController.setStory(newStory.id().toString(), newStory.title(), currentStory.users().get(0).uid().toString(), currentStory.users().get(0).toString());
				for(int i = 0; i < currentStory.users().size(); i++)
					newStory.addUser(currentStory.users().get(i));
				for(int j = 0; j < currentStory.getFragements().size(); j++){
					newStory.addFragement(currentStory.getFragements().get(j));
					for(int w = 0; w<currentStory.getFragements().get(j).choices().size(); w++)
						newStory.getFragements().get(j).addChoice(currentStory.getFragements().get(j).choices().get(w));
				}
				AdventureApplication.getStoryController().addStory(newStory);
				AdventureApplication.getActivityController().update();
				break;
			case R.id.quit:
				browseView();
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.takepic:
			takeAPhoto();
			currentView.getRootView().dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
			break;
		case R.id.choosemedia:
			currentView.getRootView().dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
			break;
		default:
			currentView.getRootView().dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
			return super.onContextItemSelected(item);
	}
	return super.onContextItemSelected(item);
		
	}
	/**
	 * changes the screen to the Edit Story Screen
	 */
	public void editStory() {
		Intent myIntent = new Intent(this, EditStoryActivity.class);
		this.startActivity(myIntent);
	}
	/**
	 * Changes the screen to the Edit Fragment Screen.
	 */
	public void editFragment(){
		Intent myIntent = new Intent(this, EditFragementActivity.class);
		this.startActivity(myIntent);
	}
	/**
	 * CHanges the screen to the Browse View
	 */
	public void browseView(){
		Intent myIntent = new Intent(this, BrowserActivity.class);
		this.startActivity(myIntent);
	}

	 private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	    /**
	     * Opens the camera and allows the user to take a picture if they so choose
	     */
	    public void takeAPhoto() {
	        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	        
	        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp";
	        File folderF = new File(folder);
	        if (!folderF.exists()) {
	            folderF.mkdir();
	        }
	        
	        String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
	        File imageFile = new File(imageFilePath);
	        imageFileUri = Uri.fromFile(imageFile);
	        
	        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
	        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	    }
	    
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	           //TextView tv = (TextView) findViewById(R.id.status);
	            if (resultCode == RESULT_OK) {
	                System.out.println("Photo OK!");
	                ImageView annotation = (ImageView) findViewById(R.id.annotation);
	                annotation.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
	            } else if (resultCode == RESULT_CANCELED) {
	                System.out.println("Photo canceled");
	            } else {
	                System.out.println("Not sure what happened!" + resultCode);
	            }
	        }
	    }

	    /**
	     * updates the view
	     */
		public void updateView(){
		//AdventureApplication.getStoryController();
	}

		@Override
		public void dataReturn(ArrayList<Story> result, String method) {
			// TODO Auto-generated method stub
			
		}



}
