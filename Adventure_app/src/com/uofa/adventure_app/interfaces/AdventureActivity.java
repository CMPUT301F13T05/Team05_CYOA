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
package com.uofa.adventure_app.interfaces;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.controller.ActivityController;
import com.uofa.adventure_app.controller.StoryParser;
import com.uofa.adventure_app.controller.WebServiceController;
import com.uofa.adventure_app.controller.http.HttpObject;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;


public abstract class AdventureActivity extends Activity implements DataReturn<Story> {
	
	WebServiceController  webServiceController = AdventureApplication.getWebServiceController();
	
	protected static final String GET_ALL_METHOD = "GET_ALL_METHOD";
	protected static final String GET_METHOD = "GET_METHOD";
	protected static final String POST_METHOD = "POST_METHOD";
	
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
	
	
	protected boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	protected void httpRequest(HttpObject httpObject, String method) {
		if(this.isNetworkAvailable()) {
			WebServiceController wsc = AdventureApplication.getWebServiceController();
		new PerformHttp<Story>(this, method, wsc ,new StoryParser()).execute(httpObject);
		} else {
			// do nothing...
		}
	}

	protected abstract void openLastFragement();
	
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

	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	
        	Fragement prevFrag = AdventureApplication.getStoryController().lastFragement();
             if(prevFrag != null) {
            	 //ystem.out.println("Button Clicked");
            	 openLastFragement();
            	 
            	 return true;
        	} else {
        		return super.onKeyDown(keyCode, event);
        	}
        } else {
        	return super.onKeyDown(keyCode, event);
        }
    }

}
