/*
ttt	Adventure App - Allows you to create an Adventure Book, or Download
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
package com.uofa.adventure_app.interfaces;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.controller.ActivityController;
import com.uofa.adventure_app.controller.FragementParser;
import com.uofa.adventure_app.controller.StoryParser;
import com.uofa.adventure_app.controller.WebServiceController;
import com.uofa.adventure_app.controller.http.HttpObject;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;
/**
 * Interface for all of the Activities in the application.
 * @author Chris Pavlicek
 *
 */
public abstract class AdventureActivity extends Activity implements DataReturn<Story> {
	
	/* (non-Javadoc)
	 * @see com.uofa.adventure_app.interfaces.DataReturn#dataReturn(java.util.ArrayList, java.lang.String)
	 */
	@Override
	public void dataReturn(ArrayList<Story> result, String method) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		AdventureApplication.getStoryController().saveStories();
		super.onPause();
	}

	WebServiceController  webServiceController = AdventureApplication.getWebServiceController();
	
	protected static final String GET_ALL_METHOD = "GET_ALL_METHOD";
	protected static final String GET_METHOD = "GET_METHOD";
	protected static final String POST_METHOD = "POST_METHOD";
	protected static final String GET_FRAGEMENT = "GET_FRAGEMENT";
	
	ProgressDialog progDailog;
	
	/**
	 * Creates a New Activity and adds itself to the Activity Controller
	 */
	public AdventureActivity() {
		ActivityController activityController = AdventureApplication.getActivityController();
		activityController.addActivity(this);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	/**
	 * Make sure we remove ourself on destroy from activity list.
	 */
	@Override
	protected void onDestroy() {
		ActivityController activityController = AdventureApplication.getActivityController();
		activityController.removeActivitiy(this);
		super.onDestroy();
	}



	/**
	 * This should be overidden by a subclass, Do any updates that are required,
	 * when the data changes, or when something is done loading.
	 */
	public abstract void updateView();
	
	/**
	 * checks to see if there in a network connection.
	 * @return boolean
	 */
	protected boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	/**
	 * Used for Parsing a Story ONLY
	 * @param httpObject
	 * @param method
	 */
	protected void httpRequest(HttpObject httpObject, String method) {
		if(this.isNetworkAvailable()) {
			WebServiceController wsc = AdventureApplication.getWebServiceController();
		new PerformHttp<Story>(this, method, wsc ,new StoryParser()).execute(httpObject);
		} else {
			// do nothing...
		}
	}
	/**
	 * calls Fragement parser if there is a network connection.
	 * @param HttpObject httpObject
	 * @param String method
	 */
	protected void httpRequestFragement(HttpObject httpObject, String method) {
		if(this.isNetworkAvailable()) {
			WebServiceController wsc = AdventureApplication.getWebServiceController();
		new PerformHttp<Story>(this, method, wsc ,new FragementParser()).execute(httpObject);
		} else {
			// do nothing...
		}
	}

	protected abstract void openLastFragement();
	/**
	 * Saves the text as a user types.
	 * @param View v
	 * @param String text
	 */
	protected abstract void saveTextForView(View v, String text);
	
	// Inline Class to Watch our text editing
		// Code Taken From:
		// http://stackoverflow.com/questions/5702771/how-to-use-single-textwatcher-for-multiple-edittexts
		// On Monday Septemeber 23, 2013
		// Modified for my Use
		protected class GenericTextWatcher implements TextWatcher {
			// View that is Being Edited
			private EditText view;

			public GenericTextWatcher(EditText view) {
				this.view = view;
			}

			public void beforeTextChanged(CharSequence charSequence, int i, int i1,
					int i2) {
			}

			public void onTextChanged(CharSequence charSequence, int i, int i1,
					int i2) {
			}

			// When Text is changed this is called.
			public void afterTextChanged(Editable editable) {
				// get string
				String text = editable.toString();
				saveTextForView(this.view,text);
			}
		}

		/**
		 * Helper method for getting authors into a string
		 * Refactored due to JDeodarant 
		 * @return String
		 */
		protected String authorString() {
			
			Story currentStory = AdventureApplication.getStoryController().currentStory();
			
			String authors = "Author: " + currentStory.users().get(0).toString();
			if (currentStory.users().size() > 1){
				authors += "\nEdited by: ";
			}
			for(int i = 1; i<currentStory.users().size(); i++){
				authors +=  currentStory.users().get(i);
				if (i != currentStory.users().size()-1 ){
					authors  += ", ";
				}
			}
			return authors;
		}
		
	/**
	 * Overides the back button for fragements
	 */
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	
        	Fragement prevFrag = AdventureApplication.getStoryController().lastFragement();
             if(prevFrag != null) {
            	 //System.out.println("Button Clicked");
            	 openLastFragement();
            	 
            	 return true;
        	} else {
        		return super.onKeyDown(keyCode, event);
        	}
        } else {
        	return super.onKeyDown(keyCode, event);
        }
    }
	/**
	 * shows the loading window
	 */
	public void loadingWindow() {
		progDailog = ProgressDialog.show(this, "Loading...", "");
	}
	/**
	 * shows the closing window.
	 */
	public void closeLoadingWindow() {
		if(progDailog != null) {
			progDailog.cancel();
		}
	}

}
