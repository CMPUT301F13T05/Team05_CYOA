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
package com.uofa.adventure_app.activity.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import com.uofa.adventure_app.activity.BrowserActivity;
import com.uofa.adventure_app.controller.http.HttpObjectStory;

/**
 * @author Joel
 * See documentation for BrowserActivity
 */
public class BrowserActivityTest extends
		ActivityInstrumentationTestCase2<BrowserActivity>
{

	private BrowserActivity browserActivity;
	private View browseView;

	public BrowserActivityTest()
	{
		super(BrowserActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		browserActivity = getActivity();
		browseView = browserActivity.findViewById(android.R.id.content);
		// HERE
		HttpObjectStory httpStory = new HttpObjectStory();
		
	}
	
	public void testStory()
	{
		browserActivity.newStory();
	}
	
}// class end
