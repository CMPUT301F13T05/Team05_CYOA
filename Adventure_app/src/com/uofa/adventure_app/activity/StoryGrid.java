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

public class StoryGrid extends BaseAdapter {

	private ArrayList<Story> stories;
	private Context context;
	private LayoutInflater mInflater;
	
	public StoryGrid(Context context, ArrayList<Story> stories) {
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
	        		authors += u.getName();
	        	}
	        	sub.setText(authors);
	        	
	        } 
	        return convertView;
	}

}
