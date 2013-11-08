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

import java.util.ArrayList;
import java.util.Timer;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.controller.ActivityController;
import com.uofa.adventure_app.controller.StoryParser;
import com.uofa.adventure_app.controller.WebServiceController;
import com.uofa.adventure_app.controller.http.HttpObject;
import com.uofa.adventure_app.model.Story;


public abstract class AdventureActivity extends Activity {
	
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
	

/**
 * Used for Threading of Web Service Controller
 * @author chris
 *
 */
	public class PerformHttp extends AsyncTask<HttpObject, Void, ArrayList<Story>> {


		AdventureActivity activity = null;
		String method = null;
		public PerformHttp(AdventureActivity activity, String method) {
			this.activity = activity;
			this.method = method;
		}
		
		protected ArrayList<Story> doInBackground(HttpObject... httpObj) {
			StoryParser parser = new StoryParser();
			ArrayList<Story> stories = new ArrayList<Story>();
			if(httpObj[0] != null)
				return parser.parseStory(webServiceController.httpWithType(httpObj[0]));
			else
				return stories;
			
		}

		protected void onPostExecute(ArrayList<Story> result) {
			
			activity.dataReturn(result,method); 
		}

	}

	/**
	 * When using threading data will be returned here, data will 
	 * need to be checked for the right type.
	 * @param results
	 */
	
	public abstract void dataReturn(ArrayList<Story> result, String method);
	
	protected boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	protected void httpRequest(HttpObject httpObject, String method) {
		if(this.isNetworkAvailable()) {
		new PerformHttp(this, method).execute(httpObject);
		} else {
			// do nothing...
		}
	}

}
