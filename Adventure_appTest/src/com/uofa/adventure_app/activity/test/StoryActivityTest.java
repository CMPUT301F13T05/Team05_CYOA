/**
 * 
 */
package com.uofa.adventure_app.activity.test;

import com.uofa.adventure_app.activity.StoryActivity;

import android.test.ActivityInstrumentationTestCase2;

/**
 * @author Joel
 *
 */
public class StoryActivityTest extends
		ActivityInstrumentationTestCase2<StoryActivity> {

	/**
	 * @param name
	 */
	public StoryActivityTest() {
		super(StoryActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		StoryActivity storyActivity = getActivity();
	}

}
