package ca.ualberta.cs.cyoa_team05.views;

import java.util.Observable;

import android.R;
import android.os.Bundle;

public class BrowserView extends UIView {
	
	UIWebView browserInstance;
	String htmlFolder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser_view);
		
		browserInstance = (UIWebView) findViewById(R.id.webview);
	}

	@Override
	public void update(Observable observable, Object data) {
		// This method receives a copy of the observable object (the model)
		// and some data in the form of an Object
		//
		// cast the variable 'observable' to a Model
		//
		// cast the variable to String, is probably easiest but more complex
		// data structures could be passed too. Perhaps even JSON object.
		
	}
	
	@Override
	public void onBackPressed () {
		/*
		 * EXAMPLE
		 * 
		 
		if(!browserInstance.getUrl().equalsIgnoreCase("file:///android_asset/index.html"))
		{
			changeURL("file:///android_asset/index.html");
		}
		
		else
		{
			finish();
		}
		*/
	}

}
