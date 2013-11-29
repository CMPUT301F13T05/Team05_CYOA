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
package com.uofa.adventure_app.controller.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.ActivityController;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Story;

/**
 * @author Joel
 * See documentation for ActivityController
 */

// Since this activity should not be called, it shouldn't require testing either

public class ActivityControllerTest extends
		ActivityInstrumentationTestCase2<BrowserActivity> {

	private ActivityController testActivityController;
	
	/**
	 * @param name
	 */
	public ActivityControllerTest(String name) {
		super(BrowserActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		testActivityController = new ActivityController();
		
	}
	
	/**
	 * Should not crash
	 */
	public void testAddActivity() {
		testActivityController.addActivity(this.getActivity());
		testActivityController.update();
	}
	
	/**
	 * Add activity and remove it should work, no real way to test...
	 */
	public void testRemoveActivtiy() {
		testActivityController.addActivity(this.getActivity());
		testActivityController.removeActivitiy(this.getActivity());
	}

}
