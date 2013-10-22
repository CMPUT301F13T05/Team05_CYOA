package com.example.adventure_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.new_story:
			newStory();

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	public void newStory() {
		Intent myIntent = new Intent(this, New_story.class);
		this.startActivity(myIntent);
	}
	
	public void viewStory(View v) {
		Intent myIntent = new Intent(this, Story.class);
		this.startActivity(myIntent);
	}

	
}
