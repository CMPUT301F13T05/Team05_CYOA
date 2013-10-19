package ca.ualberta.cs.cyoa_team05.views;

import java.util.Observable;

import ca.ualberta.cs.cyoa_team05.R;

import android.os.Bundle;

public class SettingsView extends UIView {
	UIWebView browserInstance;
	String settingsFolder;
	
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
