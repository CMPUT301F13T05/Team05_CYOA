/**
 * 
 */
package com.uofa.adventure_app.activity.test;

import com.uofa.adventure_app.activity.BrowserActivity;

import android.test.ActivityInstrumentationTestCase2;

/**
 * @author Joel
 *
 */
public class BrowserActivityTest extends
		ActivityInstrumentationTestCase2<BrowserActivity> {

	/**
	 * @param name
	 */
	public BrowserActivityTest() {
		super(BrowserActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		BrowserActivity browserActivity = getActivity();
	}

}
