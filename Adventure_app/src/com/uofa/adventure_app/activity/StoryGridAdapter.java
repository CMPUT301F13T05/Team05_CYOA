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

import android.R;
import android.content.Context;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

public class StoryGridAdapter extends BaseAdapter {

	private ArrayList<Story> stories;
	private Context context;
	private LayoutInflater mInflater;
	
	public StoryGridAdapter(Context context, ArrayList<Story> stories) {
		this.context = context;
        mInflater = LayoutInflater.from(this.context);
		this.stories = new ArrayList<Story>(stories);

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
	            convertView = mInflater.inflate(android.R.layout.two_line_list_item, parent, false);
	        }
	       // Get the views
	        TextView title = (TextView) convertView.findViewById(R.id.text1);
	        TextView sub = (TextView) convertView.findViewById(R.id.text2);
	 
	        //Set the text
	        Story story = this.stories.get(position);
	        if(story != null) {
	        	title.setText(story.title());
	        	
	        	String authors = new String();
	        	// TODO: Format this better!
	        	for(User u: story.users()) {
	        		authors += "By: " + u.getName();
	        	}
	        	sub.setText(authors);
	        	
	        } 
	        return convertView;
	}

}
