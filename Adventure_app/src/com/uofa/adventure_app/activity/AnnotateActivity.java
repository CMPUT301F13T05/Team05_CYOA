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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.TextView;


import com.uofa.adventure_app.R;
import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Annotation;
import com.uofa.adventure_app.model.Media;
import com.uofa.adventure_app.model.Story;

/**
 * This class takes in user input and processes it when the user is in the
 * Annotate/Comment screen. It interacts with the Annotation class in the model.
 * It also calls a camera and the internal storage to allow the user to add
 * pictures to their comment/annotation
 * 
 * @author Ulvi Ibrahimov, Chris Pavlicek, Kevin Lafond, Joel Malina
 * 
 */

public class AnnotateActivity extends AdventureActivity implements
		OnItemClickListener {

	ListView listView;
	ArrayList<Annotation> rowItems;
	Annotation newAnnotation = null;
	boolean isNewAnnotation = false;
	CustomListViewAdapter adapter;
	Uri chosenImageUri;
	Uri imageFileUri;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int PICK_IMAGE = 1111; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_annotate);

		rowItems = AdventureApplication.getStoryController().currentFragement().annotations();
		System.out.println(rowItems);
		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListViewAdapter(this, R.layout.list_item, rowItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

		newAnnotation = new Annotation(AdventureApplication.user());

		// alertBox();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.annotatemenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.newAnnotation:
			isNewAnnotation = true;
			alertBox();
			break;
		case R.id.choosemedia:
			chooseImage();			
			break;
		case R.id.takepic:
			takeAPhoto();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}

	public void alertBox() {

		final Dialog dialog = new Dialog(this, R.style.DialogTheme);
		LayoutInflater inflater = this.getLayoutInflater();
		dialog.setTitle("Create an Annotation");
		View alertView = inflater.inflate(R.layout.annotate_alert, null);

		TextView authorText = (TextView) alertView
				.findViewById(R.id.alertTitleAnnotation);
		authorText.setText("User: " + AdventureApplication.user().getName());

		EditText editTextView = (EditText) alertView
				.findViewById(R.id.editText);
		editTextView.setText(newAnnotation.annotationString());
		editTextView
				.addTextChangedListener(new GenericTextWatcher(editTextView));

		alertView.findViewById(R.id.cancelAnnotation).setOnClickListener(
				new Button.OnClickListener() {
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

		alertView.findViewById(R.id.okAnnotation).setOnClickListener(
				new Button.OnClickListener() {
					public void onClick(View v) {
						saveAnnotation();
						dialog.dismiss();
					}
				});

		alertView.findViewById(R.id.chooseMedia).setOnClickListener(
				new Button.OnClickListener() {
					public void onClick(View v) {
						chooseImage();
					}
				});

		alertView.findViewById(R.id.takePicture).setOnClickListener(
				new Button.OnClickListener() {
					public void onClick(View v) {
						takeAPhoto();
					}
				});

		dialog.setContentView(alertView);
		dialog.show();

	}



	public void saveAnnotation() {
		if(isNewAnnotation) {
		AdventureApplication.getStoryController().currentFragement()
				.addAnnotation(newAnnotation);
		} else {
			System.out.println("Replace");
			AdventureApplication.getStoryController().currentFragement()
			.replaceAnnotation(newAnnotation);
		}
		isNewAnnotation = false;
		AdventureApplication.getStoryController().saveStories();
		newAnnotation = new Annotation(AdventureApplication.user());
		this.updateView();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		newAnnotation = rowItems.get(position);
		if(newAnnotation.user().equals(AdventureApplication.user())) {
			alertBox();
		}
	}

	@Override
	public void dataReturn(ArrayList<Story> result, String method) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateView() {
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void openLastFragement() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void saveTextForView(View v, String text) {

		switch (v.getId()) {
		case R.id.editText:
			newAnnotation.setAnnotationString(text);
		default:
			break;
		}

		AdventureApplication.getActivityController().update();

	}
	/**
	 * Takes the photo with the camera
	 */
	public void takeAPhoto() {
		 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	        
	     String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Adventure_App";
	     File folderF = new File(folder);
	     
	     if (!folderF.exists()) {
	            folderF.mkdir();
	     }
	        
	     String imageFilePath = folder + "/" + "Adventure_App" + String.valueOf(System.currentTimeMillis()) + ".jpg";
	     File imageFile = new File(imageFilePath);
	     imageFileUri = Uri.fromFile(imageFile);
	       System.out.println(imageFileUri.toString());
	     intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			// TextView tv = (TextView) findViewById(R.id.status);
			if (resultCode == RESULT_OK) {
				System.out.println("Photo OK!");
                try {
                	
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(), imageFileUri);
                    Bitmap resizedBitmap = Media.resizeImage(bitmap);
                    String image = Media.encodeToBase64(resizedBitmap);
                    
                    // Set null just to make sure it's reset
                    newAnnotation.setAnnotationPic(null);
                    newAnnotation.setAnnotationPic(image);
                    saveAnnotation();
                } catch (FileNotFoundException e) {
                	e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
			} else if (resultCode == RESULT_CANCELED) {
				System.out.println("Photo canceled");
			} else {
				System.out.println("Not sure what happened!" + resultCode);
			}
		}
		 // handles selecting an image from app of users choice (usually gallery).
        if ((requestCode == PICK_IMAGE) && (resultCode == RESULT_OK) && (data != null))
        {
            chosenImageUri = data.getData();
            
            
            String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Adventure_App/";
            File folderF = new File(folder);
   	     
            if (!folderF.exists()) {
   	            folderF.mkdir();
            }
   	        


   	     	
   	     
            try {
            // copyfile from gallery location to our app!
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        this.getContentResolver(), chosenImageUri);
                Bitmap resizedBitmap = Media.resizeImage(bitmap);
                String image = Media.encodeToBase64(resizedBitmap);
                newAnnotation.setAnnotationPic(image);
                System.out.println(newAnnotation.media());
                saveAnnotation();
                
			} catch (IOException e) {
				e.printStackTrace();
			}

            
        }
	}
	/**
	 * called when the user chooses an existing photo and adds it to the fragement
	 * passes the chosen fragement to the onActivityResult method.
	 */
	  public void chooseImage()
	    {
	    	Intent pickImage = new Intent();
	    	pickImage.setType("image/*");
	    	pickImage.setAction(Intent.ACTION_GET_CONTENT);
	    	startActivityForResult(Intent.createChooser(pickImage, "Select Picture"), PICK_IMAGE);
	    }
}
