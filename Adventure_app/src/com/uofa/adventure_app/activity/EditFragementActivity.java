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
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ImageView;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Choice;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;

public class EditFragementActivity extends AdventureActivity {
	View currentView;
	Uri imageFileUri;
	Bundle extras;
	String title;
	String user;
	String body;
	Story s;
	String s_id;
	String old_frag;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	boolean choice = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_fragement);
		Fragement currentFragement = AdventureApplication.getStoryController().currentFragement();
		currentView = this.findViewById(android.R.id.content);
		EditText newauthor = (EditText) findViewById(R.id.newauthor);
		newauthor.setText(getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("username", null));
		EditText newTitle = (EditText) findViewById(R.id.newtitle);
		newTitle.setText(currentFragement.getTitle());
		EditText newBody = (EditText) findViewById(R.id.newbody);
		newBody.setText(currentFragement.body());
		
		// Generic Watcher Title
		newTitle.addTextChangedListener(new GenericTextWatcher(newTitle));
		newBody.addTextChangedListener(new GenericTextWatcher(newBody));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	// We want to create a context Menu when the user long click on an item
	/**
	 * Opens the menu of possible choices to add to the Fragment.
	 * @param View v
	 */
	public void openChoices(View v) {
		choice = true;
		registerForContextMenu(v);
		openContextMenu(v);

	}
	/**
	 * Updates the view.
	 */
	public void updateView() {
		
	}

	@Override
	public void dataReturn(ArrayList<Story> result, String method) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.addmedia:
			openMediaContext(currentView);
			break;
		case R.id.save:
			save();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * Method that is called to open the context view to 
	 * allow the user to open the camera or choose an existing
	 * piece of media.
	 * 
	 * @param View v
	 */
	public void openMediaContext(View v) {
		choice = false;
		registerForContextMenu(v);
		openContextMenu(v);
	}

	// We want to create a context Menu when the user long click on an item
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;
		menu.clearHeader();
		menu.clear();
		// Style our context menu
		menu.setHeaderIcon(android.R.drawable.ic_input_get);
		menu.setHeaderTitle("Options");
		MenuInflater inflater = getMenuInflater();
		if (choice == false) {
			// Open Menu
			inflater.inflate(R.menu.annotatemenu, menu);
		} else {
			menu.clearHeader();
			menu.clear();
			//menu.removeGroup(R.id.annotategroup);
			// Style our context menu
			menu.setHeaderIcon(android.R.drawable.ic_input_get);
			menu.setHeaderTitle("Add a Choice");
			int counter = 0;
			for(int j = 0; j<AdventureApplication.getStoryController().currentStory().getFragements().size(); j++) {
				menu.add(0, counter, 0, "Add: " + AdventureApplication.getStoryController().currentStory().getFragements().get(j).getTitle());
				counter++;
			}
			inflater.inflate(R.menu.createchoice, menu);
			//choice = false;
		}
	}
	/**
	 * Takes the photo with the camera
	 */
	public void takeAPhoto() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Adventure_App/Images";
		File folderF = new File(folder);
		if (!folderF.exists()) {
			folderF.mkdir();
		}

		String imageFilePath = folder + "/" + "Adventure_App" + String.valueOf(System.currentTimeMillis()) + "jpg";
		File imageFile = new File(imageFilePath);
		imageFileUri = Uri.fromFile(imageFile);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			// TextView tv = (TextView) findViewById(R.id.status);
			if (resultCode == RESULT_OK) {
				//System.out.println("Photo OK!");
				ImageView annotation = (ImageView) findViewById(R.id.annotation);
				annotation.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));

			} else if (resultCode == RESULT_CANCELED) {
				System.out.println("Photo canceled");
			} else {
				System.out.println("Not sure what happened!" + resultCode);
			}
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if(item.getGroupId() == 0 && item.getItemId() != R.id.cancel && item.getItemId() != R.id.takepic && item.getItemId() != R.id.choosemedia && item.getItemId() != R.id.takepic && item.getItemId() != R.id.newchoice && item.getItemId() != R.id.randomchoice) {
			// TODO: This needs to be refactored....
			Choice choice = new Choice(AdventureApplication.getStoryController().currentStory().getFragements().get(item.getItemId()));
			AdventureApplication.getStoryController().currentFragement().addChoice(choice);
			
		} else {	
		
		switch (item.getItemId()) {
			case R.id.takepic:
				takeAPhoto();
				currentView.getRootView().dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
				break;
			case R.id.choosemedia:
				currentView.getRootView().dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
				break;
			case R.id.newchoice:
				save();
				newChoice();
				break;
			case R.id.randomchoice:
				AdventureApplication.getStoryController().currentFragement().setRandomFlag(true);

				break;
			default:
				currentView.getRootView().dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
				return super.onContextItemSelected(item);
			
		}
		}
		return super.onContextItemSelected(item);

	}

	private void newChoice() {
		Fragement currentFragement = AdventureApplication.getStoryController().currentFragement();
		Fragement newFragement = new Fragement();
		Choice newChoice = new Choice(newFragement);
		AdventureApplication.getStoryController().currentFragement().addChoice(newChoice);
		AdventureApplication.getStoryController().addPreviousFragement(currentFragement);
		AdventureApplication.getStoryController().setCurrentFragement(newFragement);
		
		EditText newTitle = (EditText) findViewById(R.id.newtitle);
		newTitle.setText("");
		EditText newBody = (EditText) findViewById(R.id.newbody);
		newBody.setText("");
	}
	
	public void save(){
		EditText newTitle = (EditText) findViewById(R.id.newtitle);
		//EditText newAuthor = (EditText) findViewById(R.id.newauthor);
		EditText newBody = (EditText) findViewById(R.id.newbody);
		
		// Update the current window fragement
		// We should setup a text listner, and do this automatically, this is clunky.
		Fragement currentFragement = AdventureApplication.getStoryController().currentFragement();
		currentFragement.setBody(newBody.getText().toString());
		currentFragement.setTitle(newTitle.getText().toString());
		
		
		AdventureApplication.getStoryController().saveStories();
		
	}
	
	   protected void openLastFragement() {
		   Fragement currentFragement = AdventureApplication.getStoryController().lastFragement();
		   AdventureApplication.getStoryController().setCurrentFragement(currentFragement);
		   AdventureApplication.getStoryController().popPreviousFragement();
		   EditText newTitle = (EditText) findViewById(R.id.newtitle);
			//EditText newAuthor = (EditText) findViewById(R.id.newauthor);
			EditText newBody = (EditText) findViewById(R.id.newbody);
		   newTitle.setText(currentFragement.getTitle());
		   newBody.setText(currentFragement.body());
	    }
	   
		protected void saveTextForView(View v, String text) {
			
			switch(v.getId()) {
				case R.id.newtitle: 
					AdventureApplication.getStoryController().currentFragement().setTitle(text);
					break;
				case R.id.newbody:
					AdventureApplication.getStoryController().currentFragement().setBody(text);
					break;
				default:
					break;
			}
			

			AdventureApplication.getActivityController().update();
			

		}
}
