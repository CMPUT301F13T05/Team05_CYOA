package com.uofa.adventure_app.activity;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.R.layout;
import com.uofa.adventure_app.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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
	public void okClicked(View v){
		EditText usern = (EditText) findViewById(R.id.usern);
		String username = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("username", null);
		username = usern.getText().toString();
		if (username.equals("")){
			usern.setText("Please Enter your name here!");
		}else{
			getSharedPreferences("PREFERENCE", MODE_PRIVATE)
			.edit()
			.putString("username", username)
			.commit();
			onBackPressed();
		}
			
	}

}
