package com.uofa.adventure_app.interfaces;

import java.util.ArrayList;

import android.os.AsyncTask;

import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.controller.WebServiceController;
import com.uofa.adventure_app.controller.http.HttpObject;

/**
 * Used for Threading of Web Service Controller
 * @author chris
 *
 */
	public class PerformHttp<T> extends AsyncTask<HttpObject, Integer, ArrayList<T>> {


		DataReturn<T> activity = null;
		String method = null;
		Parser<T> parser = null;
		WebServiceController webServiceController;
		public PerformHttp(DataReturn<T> activity, String method, WebServiceController webService, Parser<T> parser) {
			this.parser = parser;
			this.webServiceController = webService;
			this.activity = activity;
			this.method = method;
		}
		

		protected ArrayList<T> doInBackground(HttpObject... httpObj) {
			ArrayList<T> stories = new ArrayList<T>();
			stories.clear();
			
			if(httpObj[0] != null) {
				publishProgress(0);
				return parser.parse(webServiceController.httpWithType(httpObj[0]));
			} else {
				publishProgress(0);
				return stories;
			}
			
		}

		 protected void onProgressUpdate(Integer... progress) {
	         for(AdventureActivity a : AdventureApplication.getActivityController().openActivities()) {
	        	 a.loadingWindow();
	         }
	     }
		
		protected void onPostExecute(ArrayList<T> result) {
	         for(AdventureActivity a : AdventureApplication.getActivityController().openActivities()) {
	        	 a.closeLoadingWindow();
	         }
			activity.dataReturn(result,method); 
		}

}
