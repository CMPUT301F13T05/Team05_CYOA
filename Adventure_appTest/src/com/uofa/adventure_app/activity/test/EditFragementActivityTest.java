package com.uofa.adventure_app.activity.test;

import com.uofa.adventure_app.activity.EditFragementActivity;

import android.test.ActivityInstrumentationTestCase2;

public class EditFragementActivityTest extends
		ActivityInstrumentationTestCase2<EditFragementActivity> {

	public EditFragementActivityTest() {
		super(EditFragementActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		EditFragementActivity editFragementActivity = getActivity();
	}
}
