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
package com.uofa.adventure_app.controller;
import java.util.ArrayList;

import android.os.Handler;
import android.os.HandlerThread;


import com.uofa.adventure_app.interfaces.AdventureActivity;

/**
 * Keeps track of the current activity and contains
 * an ArrayList of the Activities.
 * @author Chris Pavlicek
 */
public class ActivityController {
	// Every activity should be added to here.
	private final ArrayList<AdventureActivity> activities = new ArrayList<AdventureActivity>();
	
	// Holds the universal handler thread
	private HandlerThread handlerThread = null;
	
	// Holds the universal handler
	private Handler handler = null;

	public ActivityController() {
	     this.handlerThread = new HandlerThread("Message Thread");
         // Start the thread that will handle messages
         this.handlerThread.start();
         this.handler = new Handler(handlerThread.getLooper());
	}
	/**
	 * Adds an activity to the list of activities.
	 * @param AdventureActivity
	 */
	public void addActivity(AdventureActivity activity) {
		activities.add(activity);
	}
	/**
	 * Removes an activity to the list of activities.
	 * @param AdventureActivity
	 */
	public void removeActivitiy(AdventureActivity activity) {
		activities.remove(activity);
	}
	/**
	 * returns a list of AdventureActivity
	 * @return ArrayList<AdventureActivty>
	 */
	public ArrayList<AdventureActivity> openActivities() {
		return this.activities;
	}
	/**
	 * Updates the referenced view.
	 */
	public void update() {
		for(AdventureActivity a: activities) {
			a.updateView();
		}
	}
	
	
	
	
	
}
