package com.uofa.adventure_app.controller.test;

import com.uofa.adventure_app.activity.BrowserActivity;

import android.test.ActivityInstrumentationTestCase2;

/**
 * @author Joel
 *
 */
public class WebServiceControllerTest extends
		ActivityInstrumentationTestCase2<BrowserActivity> {

	/**
	 * @param name
	 */
	public WebServiceControllerTest(String name) {
		super(BrowserActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
	}

}
