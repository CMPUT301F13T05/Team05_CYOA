package com.uofa.adventure_app.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.application.AdventureApplication;
import com.uofa.adventure_app.controller.http.HttpObjectStory;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Annotation;
import com.uofa.adventure_app.model.Story;


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
			System.out.println("New Annotation");
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
