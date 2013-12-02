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

import java.util.UUID;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
/**
 * This activity is only ran if the user logs out of the applciation or on first run after install 
 * to allow us to have a user for the applciation.
 * @author Kevin Lafond
 *
 */
public class FirstRunOnlyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_run_only);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_run_only, menu);
		return true;
	}
	@Override
    public void onBackPressed() {
        super.onBackPressed();   
        //    finish();

    }
	/**
	 * Called to set the user name to the input information.
	 * @param View v
	 */
	public void okClicked(View v){
		EditText usern = (EditText) findViewById(R.id.usern);
		TextView error = (TextView) findViewById(R.id.textView2);
		String username = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("username", null);
		username = usern.getText().toString();
		UUID id = null;
		if (username.equals("")){
			error.setText("Please Enter your name here!");
		}else 
			if (username.length() > 15){
				error.setText("The user name you entered is too long");
			}else {
				for (int i = 0; i<AdventureApplication.getStoryController().stories().size(); i++){
					for(int j = 0; j <AdventureApplication.getStoryController().stories().get(i).users().size(); j++){
						if (AdventureApplication.getStoryController().stories().get(i).users().get(j).getName().equals(username)){
							id = AdventureApplication.getStoryController().stories().get(i).users().get(j).uid();
						}
					}
				}
				if (id == null){
						id = new User(username).uid();	
				}
			getSharedPreferences("PREFERENCE", MODE_PRIVATE)
			.edit()
			.putString("username", username)
			.putString("uid", id.toString())
			.commit();
			
			String usernameString = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("username", null);
			UUID uid = UUID.fromString(getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("uid", null));
			AdventureApplication.setUser(new User(usernameString,uid));
			onBackPressed();
		}
			
	}

}
