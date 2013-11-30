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

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Annotation;
import com.uofa.adventure_app.model.Story;
/**
 * This class takes in user input and processes it when the user
 * is in the Annotate/Comment screen.  It interacts with the Annotation
 * class in the model. It also calls a camera and the internal storage
 * to allow the user to add pictures to their comment/annotation  
 * 
 * @author Ulvi Ibrahimov, Chris Pavlicek
 *
 */

public class AnnotateActivity extends AdventureActivity implements
       OnItemClickListener {

   ListView listView;
   ArrayList<Annotation> rowItems;

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_annotate);

       rowItems = new ArrayList<Annotation>();
       rowItems = AdventureApplication.getStoryController().currentFragement().annotations();

       listView = (ListView) findViewById(R.id.list);
       CustomListViewAdapter adapter = new CustomListViewAdapter(this,
               R.layout.list_item, rowItems);
       listView.setAdapter(adapter);
       listView.setOnItemClickListener(this);
       alertBox();
   }

   @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.annotatemenu, menu);
		return super.onCreateOptionsMenu(menu);
   }
   
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.newAnnotation:
			alertBox();
			break;
		case R.id.choosemedia:
			System.out.println("Choose Media");
			break;
		case R.id.takepic:
			System.out.println("Take Pic");
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}
   
	@SuppressLint("NewApi")
	public void alertBox() {
		AlertDialog alert = null;
		 AlertDialog.Builder adb = new AlertDialog.Builder(this);
		 LayoutInflater inflater = this.getLayoutInflater();
		 adb.setIcon(android.R.drawable.ic_input_get);
		 adb.setInverseBackgroundForced(true);
		 adb.setTitle("Create a New Annotation");
		 View alertView = inflater.inflate(R.layout.annotate_alert, null);
        adb.setView(alertView);
        TextView authorText = (TextView)alertView.findViewById(R.id.authorText);
        EditText editText = (EditText)alertView.findViewById(R.id.editText);
        editText.setText("Enter your Annotation Here");
        authorText.setText("User: " + AdventureApplication.user().getName());
        alert = adb.create();
        View select = alert.findViewById(android.R.layout.select_dialog_multichoice);
        select.findViewById(android.R.id.button1).setPadding(0, 0, 0, 0);
      
        alert.show();
	}
	
   @Override
   public void onItemClick(AdapterView<?> parent, View view, int position,
           long id) {
       Toast toast = Toast.makeText(getApplicationContext(),
           "Item " + (position + 1) + ": " + rowItems.get(position),
           Toast.LENGTH_SHORT);
       toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
       toast.show();
   }

@Override
public void dataReturn(ArrayList<Story> result, String method) {
	// TODO Auto-generated method stub
	
}

@Override
public void updateView() {
	// TODO Auto-generated method stub
	
}

@Override
protected void openLastFragement() {
	// TODO Auto-generated method stub
	
}

@Override
protected void saveTextForView(View v, String text) {
	// TODO Auto-generated method stub
	
}
}
