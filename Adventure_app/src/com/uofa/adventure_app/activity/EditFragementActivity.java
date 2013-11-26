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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.controller.LocalStorageController;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

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
		currentView = this.findViewById(android.R.id.content);
		extras = getIntent().getExtras();
		EditText newauthor = (EditText) findViewById(R.id.newauthor);
		newauthor.setText(getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("username", null));
		old_frag = null;
		if (extras != null)
			old_frag = extras.getString("frag_id");
		
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
				break;
			default:
				currentView.getRootView().dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
				return super.onContextItemSelected(item);
			
		}

		return super.onContextItemSelected(item);

	}

	public void save(){
		EditText newtitle = (EditText) findViewById(R.id.newtitle);
		EditText newauthor = (EditText) findViewById(R.id.newauthor);
		EditText newbody = (EditText) findViewById(R.id.newbody);
		title = newtitle.getText().toString();
		user = newauthor.getText().toString();
		body = newbody.getText().toString();
		LocalStorageController localStorageController = new LocalStorageController(this);
		if (extras != null){
			if (old_frag == null){
				s = new Story();
				s_id = s.id().toString();
				s.setIsLocal(true);
				User curUser = new User(user);
				Integer flag = 1;
				ArrayList<User> users = new ArrayList<User>();
				users.add(curUser);
				UUID userId = curUser.uid();
				s.setUsers(users);
				Fragement frag = new Fragement(body, flag);
				frag.setTitle(title);
				s.setStartFragement(frag);
				s.addFragement(frag);
				
				UUID fragId = frag.uid();
				old_frag = fragId.toString();
				AdventureApplication.getStoryController().addStory(s);
				localStorageController.setStory(s_id, title, userId.toString(), user);
				localStorageController.setFragment(s_id, fragId.toString(), title, body, old_frag, flag);
				newtitle.setText("");
				newbody.setText("");
				if(imageFileUri != null){
					//saving image
					String imageId = UUID.randomUUID().toString();
					localStorageController.insertImage( imageId, imageFileUri.getPath().toString(), 0, frag.uid().toString());
				}
			} else {
				
				s_id = s.id().toString();
				User curUser = new User(user);
				ArrayList<User> users = new ArrayList<User>();
				users.add(curUser);
				UUID userId = curUser.uid();
				Integer flag = 0;
				s.setUsers(users);
				Fragement frag = new Fragement(body, flag);
				frag.setTitle(title);
				s.addFragement(frag);
				
				UUID fragId = frag.uid();
				//AdventureApplication.getStoryController().replaceStory(s);
				System.out.println(AdventureApplication.getStoryController().stories());
				localStorageController.setFragment(s_id, fragId.toString(), title, body, old_frag, flag);
				newtitle.setText("");
				newbody.setText("");
				
				if (imageFileUri != null){
					// saving image
					String imageId = UUID.randomUUID().toString();
					localStorageController.insertImage( imageId, imageFileUri.getPath().toString(), 0, frag.uid().toString());
				}
			}
		}
	}
}
