package com.uofa.adventure_app.application;

import android.app.Application;

import com.uofa.adventure_app.controller.StoryController;
import com.uofa.adventure_app.controller.WebServiceController;

public class AdventureApplication extends Application {
	
	transient private static StoryController storyController;
	transient private static WebServiceController webServiceController;
	
	// Usable controller - singelton for all classes
	public static StoryController getStoryController() {
		if(storyController == null) {
			storyController = new StoryController();
		}
		return storyController;	
	}
	
	// Usable controller - singelton for all classes
	public static WebServiceController getWebServiceController() {
		if(webServiceController == null) {
			webServiceController = new WebServiceController();
		}
		return webServiceController;	
	}
	

}
