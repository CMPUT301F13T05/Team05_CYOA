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
import android.content.Context;

import com.uofa.adventure_app.controller.ActivityController;
import com.uofa.adventure_app.controller.LocalStorageController;
import com.uofa.adventure_app.controller.StoryController;
import com.uofa.adventure_app.controller.WebServiceController;
import com.uofa.adventure_app.model.User;

public class AdventureApplication extends Application {
	
	transient private static StoryController storyController;
	transient private static WebServiceController webServiceController;

	transient private static LocalStorageController localStorageController;

	transient private static ActivityController activityController;
	
	private static Context context;
	private static User currentUser;

    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context context() {
        return context;
    }
	
    public static void setUser(User user) {
        currentUser = user;
    }
    
    public static User user() {
       return currentUser;
    }

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
	

	public  LocalStorageController getLocalStorageController(){
		if(localStorageController == null) {
			localStorageController = new LocalStorageController(this);
		}
		return localStorageController;	
	}

	public static ActivityController getActivityController() {
		if(activityController == null) {
			activityController = new ActivityController();
		}
		return activityController;	
	}
		
	}



