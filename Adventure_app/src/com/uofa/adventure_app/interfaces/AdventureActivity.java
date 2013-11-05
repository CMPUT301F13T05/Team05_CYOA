package com.uofa.adventure_app.interfaces;

import java.util.ArrayList;

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
			System.out.println("Here perform async");
			this.activity = activity;
			this.method = method;
		}
		
		protected ArrayList<Story> doInBackground(HttpObject... httpObj) {
			System.out.println("Here in back");
			StoryParser parser = new StoryParser();
			return parser.parseStory(webServiceController.httpWithType(httpObj[0]));
			
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
	
	
<<<<<<< Updated upstream
	
=======

>>>>>>> Stashed changes

	
}
