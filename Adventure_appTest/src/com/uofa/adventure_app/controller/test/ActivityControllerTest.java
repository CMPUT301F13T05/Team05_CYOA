package com.uofa.adventure_app.controller.test;

import junit.framework.TestCase;

import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.ActivityController;

public class ActivityControllerTest extends TestCase {
	private ActivityController testActivityController;
	
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
		BrowserActivity browser = new BrowserActivity();
		testActivityController.addActivity(browser);
		testActivityController.update();
	}
	
	/**
	 * Add activity and remove it should work, no real way to test...
	 */
	public void testRemoveActivtiy() {
		BrowserActivity browser = new BrowserActivity();
		testActivityController.addActivity(browser);
		testActivityController.removeActivitiy(browser);
}
}
