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
package com.uofa.adventure_app.application;

import android.app.Application;
import android.widget.Toast;


import com.uofa.adventure_app.controller.LocalStorageController;
import com.uofa.adventure_app.controller.ActivityController;

import com.uofa.adventure_app.controller.StoryController;
import com.uofa.adventure_app.controller.WebServiceController;

public class AdventureApplication extends Application {
	
	transient private static StoryController storyController;
	transient private static WebServiceController webServiceController;

	transient private static LocalStorageController localStorageController;

	transient private static ActivityController activityController;
	
	/**
	 * Returns the StoryController
	 * @return StoryController
	 */
	public static StoryController getStoryController() {
		if(storyController == null) {
			storyController = new StoryController();
		}
		return storyController;	
	}
	
	/**
	 * Returns the WebServiceController
	 * @return WebServiceController
	 */
	public static WebServiceController getWebServiceController() {
		if(webServiceController == null) {
			webServiceController = new WebServiceController();
		}
		return webServiceController;	
	}
	
/**
 * Returns the LocalStoryController
 * @return LocalStorageController
 */
	public  LocalStorageController getLocalStorageController(){
		if(localStorageController == null) {
			localStorageController = new LocalStorageController(this);
		}
		return localStorageController;	
	}

	/**
	 * Returns the ActivityController
	 * @return ActivityController
	 */
	public static ActivityController getActivityController() {
		if(activityController == null) {
			activityController = new ActivityController();
		}
		return activityController;	
	}
	


}
