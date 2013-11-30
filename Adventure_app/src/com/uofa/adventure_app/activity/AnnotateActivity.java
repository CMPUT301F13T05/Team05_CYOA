package com.uofa.adventure_app.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.uofa.adventure_app.R;
import com.uofa.adventure_app.interfaces.AdventureActivity;
import com.uofa.adventure_app.model.Story;


public class AnnotateActivity extends AdventureActivity implements
       OnItemClickListener {

   public static final String[] titles = new String[] { "Strawberry",
           "Banana", "Orange", "Mixed" };

   public static final String[] descriptions = new String[] {
           "It is an aggregate accessory fruit",
           "It is the largest herbaceous flowering plant", "Citrus Fruit",
           "Mixed Fruits" };

   public static final Integer[] images = { R.drawable.straw,
           R.drawable.banana, R.drawable.orange, R.drawable.mixed };

   ListView listView;
   List<RowItem> rowItems;

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_annotate);

       rowItems = new ArrayList<RowItem>();
       for (int i = 0; i < titles.length; i++) {
           RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
           rowItems.add(item);
       }

       listView = (ListView) findViewById(R.id.list);
       CustomListViewAdapter adapter = new CustomListViewAdapter(this,
               R.layout.list_item, rowItems);
       listView.setAdapter(adapter);
       listView.setOnItemClickListener(this);
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
