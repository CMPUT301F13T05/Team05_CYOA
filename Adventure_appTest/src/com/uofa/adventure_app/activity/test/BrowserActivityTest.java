/**
 * 
 */
package com.uofa.adventure_app.activity.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.http.HttpObjectStory;

/**
 * @author Joel
 *
 */
public class BrowserActivityTest extends
		ActivityInstrumentationTestCase2<BrowserActivity> {

	// for setUp()
	private BrowserActivity browserActivity;
	private View browseView;
	
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
		browserActivity = getActivity();
		browseView = browserActivity.findViewById(android.R.id.content);
		//.........CONTINUE HERE
		HttpObjectStory httpStory = new HttpObjectStory();
		//this.httpRequest(httpStory.fetchAll(), browserActivity.GET_ALL_METHOD);
	}
	
	public void testStory(){
		browserActivity.newStory();
	}
	
	
	
}
