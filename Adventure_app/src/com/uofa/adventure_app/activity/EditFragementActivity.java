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
import com.uofa.adventure_app.controller.LocalStorageController;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Story;

public class EditFragementActivity extends AdventureActivity {
	View currentView;
	Uri imageFileUri;
	Bundle extras;
	String title;
	String user;
	String body;
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
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	// We want to create a context Menu when the user long click on an item

	public void openChoices(View v) {
		choice = true;
		registerForContextMenu(v);
		openContextMenu(v);

	}

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
		default:
			return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}

	public void openMediaContext(View v) {

		registerForContextMenu(v);
		openContextMenu(v);
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
		if (choice == false) {
			// Open Menu
			inflater.inflate(R.menu.annotatemenu, menu);
		} else {
			inflater.inflate(R.menu.createchoice, menu);
			choice = false;
		}
	}
	/**
	 * Takes the photo with the camera
	 */
	public void takeAPhoto() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		String folder = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/tmp";
		File folderF = new File(folder);
		if (!folderF.exists()) {
			folderF.mkdir();
		}

		String imageFilePath = folder + "/"
				+ String.valueOf(System.currentTimeMillis()) + "jpg";
		File imageFile = new File(imageFilePath);
		imageFileUri = Uri.fromFile(imageFile);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			// TextView tv = (TextView) findViewById(R.id.status);
			if (resultCode == RESULT_OK) {
				System.out.println("Photo OK!");
				ImageView annotation = (ImageView) findViewById(R.id.annotation);
				annotation.setImageDrawable(Drawable
						.createFromPath(imageFileUri.getPath()));
			} else if (resultCode == RESULT_CANCELED) {
				System.out.println("Photo canceled");
			} else {
				System.out.println("Not sure what happened!" + resultCode);
			}
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (choice == false){
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
		}else{
			switch (item.getItemId()) {
			case R.id.newchoice:
				EditText newtitle = (EditText) findViewById(R.id.newtitle);
				EditText newauthor = (EditText) findViewById(R.id.newauthor);
				EditText newbody = (EditText) findViewById(R.id.newbody);
				title = newtitle.getText().toString();
				body = newauthor.getText().toString();
				user = newbody.getText().toString();
				LocalStorageController localStorageController = new LocalStorageController(this);
				int old_frag;
				System.out.println(extras);
				if (extras != null){
					old_frag = extras.getInt("frag_id");
					if (old_frag == 0){
						int s_id = localStorageController.setStory(title, user);
						int frag_id = localStorageController.setFragment(s_id, title, user, body, old_frag);
						Intent myIntent = new Intent(this, EditFragementActivity.class);
						myIntent.putExtra("frag_id", frag_id);
						this.startActivity(myIntent);
					}else{

						int s_id = localStorageController.setStory(title, user);
						int frag_id = localStorageController.setFragment(s_id, title, user, body, old_frag);
						Intent myIntent = new Intent(this, EditFragementActivity.class);
						myIntent.putExtra("frag_id", frag_id);
						this.startActivity(myIntent);

					}
				}

				break;
			default:
				currentView.getRootView().dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
				return super.onContextItemSelected(item);
			}
		}

		return super.onContextItemSelected(item);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
 
	@Override
    public void onBackPressed() {
        super.onBackPressed();   
        //    finish();

    }

}
