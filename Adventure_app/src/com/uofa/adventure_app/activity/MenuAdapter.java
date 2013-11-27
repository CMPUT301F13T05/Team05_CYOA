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
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.uofa.adventure_app.R;

public class MenuAdapter extends BaseAdapter {

	private ArrayList<MenuItem> menuItems;
	private Context context;
	private LayoutInflater mInflater;

	
	public MenuAdapter(Context context, int resource, ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
		mInflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public int getCount() {
		return this.menuItems.size();
	}

	@Override
	public MenuItem getItem(int position) {

		return this.menuItems.get(position);

	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Setup the multi line items in a listview
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.drawer_item, parent,
					false);
		}
		// Get the views
		ImageView imageIcon = (ImageView) convertView.findViewById(R.id.buttonIcon);
		System.out.println(this.menuItems);
		Drawable draw = this.menuItems.get(position).getIcon();
		imageIcon.setImageDrawable(draw);
		return convertView;
	}


	
}
