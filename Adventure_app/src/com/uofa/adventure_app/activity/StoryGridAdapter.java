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
package com.uofa.adventure_app.activity;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.controller.StoryParser;
import com.uofa.adventure_app.controller.WebServiceController;
import com.uofa.adventure_app.controller.http.HttpObject;
import com.uofa.adventure_app.controller.http.HttpObjectStory;
import com.uofa.adventure_app.interfaces.DataReturn;
import com.uofa.adventure_app.interfaces.PerformHttp;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

public class StoryGridAdapter extends BaseAdapter {

	private ArrayList<Story> stories;
	private ArrayList<Story> storiesClone;
	private Context context;
	private LayoutInflater mInflater;
	private String query;

	public StoryGridAdapter(Context context, ArrayList<Story> stories) {
		this.context = context;
		mInflater = LayoutInflater.from(this.context);
		this.stories = stories;
		this.storiesClone = new ArrayList<Story>();
		this.storiesClone.addAll(stories);
		this.query = "";
	}

	public void filter(String query) {
		if (query != null) {
			this.query = query;
			this.stories.clear();
			for (Story s : storiesClone) {
				String searchString = s.title().concat(s.users().toString());
				if (searchString.matches("(?i)(.*)" + query + "(.*)")) {
					this.stories.add(s);
				}
			}
			this.notifyDataSetChanged();
		} else {
			this.stories.clear();
			this.stories.addAll(this.storiesClone);
		}
	}

	@Override
	public int getCount() {
		return this.stories.size();
	}

	@Override
	public Object getItem(int position) {

		return this.stories.get(position);

	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Setup the multi line items in a listview
		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.browser_item, parent,
					false);
		}
		// Get the views
		TextView title = (TextView) convertView.findViewById(R.id.line_a);
		TextView sub = (TextView) convertView.findViewById(R.id.line_b);

		Story story = (Story) this.getItem(position);
		if (story != null) {

			if (!story.isLocal()) {
				convertView.setBackgroundResource(R.drawable.gridbgdown);
				convertView.setPadding(25, 20, 20, 25);
			} else {
				convertView.setBackgroundResource(R.drawable.gridbg);
				convertView.setPadding(25, 20, 20, 25);
			}

				title.setText(story.title());

			String authors = new String();
			// TODO: Format this better!
			for (User u : story.users()) {
				authors += "By: " + u.getName();
			}
			sub.setText(authors);
		}

		return convertView;
	}


	
}
