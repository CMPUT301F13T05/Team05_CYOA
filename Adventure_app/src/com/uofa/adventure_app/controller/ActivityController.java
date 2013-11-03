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
public class ActivityController implements Handler.Callback {
	
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
	
	public Handler handler() {
		return this.handler;
	}
	
	public HandlerThread handlerThread() {
		return this.handlerThread;
	}
	
	/* (non-Javadoc)
	 * @see android.os.Handler.Callback#handleMessage(android.os.Message)
	 */
	@Override
	public boolean handleMessage(Message msg) {
		if(!activities.isEmpty()) {
			for(AdventureActivity a: activities) {
				a.updateView();
			}
		}
		return false;
	}

	
	
}
