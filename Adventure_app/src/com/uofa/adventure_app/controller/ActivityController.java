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
import android.os.Message;

import com.uofa.adventure_app.interfaces.AdventureActivity;

/**
 * This class should not be called...
 * @author chris
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
	
	public void addActivity(AdventureActivity activity) {
		activities.add(activity);
	}
	
	public void removeActivitiy(AdventureActivity activity) {
		activities.remove(activity);
	}
	public ArrayList<AdventureActivity> getActivityStack(){
		return this.activities;
	}
	public void setActivityStack(ArrayList<AdventureActivity> list){
		this.activities.clear();
		this.activities.addAll(list);
	}
	public void update() {
		for(AdventureActivity a: activities) {
			a.updateView();
		}
	}
	
	
	
	
	
}
