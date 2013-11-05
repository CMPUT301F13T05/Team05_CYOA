/**
 * 
 */
package com.uofa.adventure_app.activity.test;

import com.uofa.adventure_app.activity.EditStoryActivity;

import android.test.ActivityInstrumentationTestCase2;

/**
 * @author Joel
 *
 */
public class EditStoryActivityTest extends
		ActivityInstrumentationTestCase2<EditStoryActivity> {

	/**
	 * @param name
	 */
	public EditStoryActivityTest() {
		super(EditStoryActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		EditStoryActivity editStoryActivity = getActivity();
	}

}
