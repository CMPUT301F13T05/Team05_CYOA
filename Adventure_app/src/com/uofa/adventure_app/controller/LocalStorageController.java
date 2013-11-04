/**
 * 
 */
package com.uofa.adventure_app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.uofa.adventure_app.enums.DbHelper;

/**
 * @author ulvi
 *
 */
public class LocalStorageController {
	
	private Context context;
	public static DbHelper DBHelper;
	public SQLiteDatabase db;
	
	public LocalStorageController(Context ctx){
    	this.context=ctx;
    	DBHelper=new DbHelper(context);
    }

	/**
	 * Opens writable database*/
	public LocalStorageController openForWrite() throws SQLException{
    	db=DBHelper.getWritableDatabase();
    	return this;
    }
	
	/**
	 * opens readable database*/
	public LocalStorageController openForRead() throws SQLException{	
    	db=DBHelper.getReadableDatabase();
    	return this;
    }
	/**
	 * closes database*/
	public void close(){
    	DBHelper.close();
	}
	/**
	 * Inserts values to the Stories table
	 * tested, works*/
	public void insertToStoriesTable(int story_id,String title, String text){
		ContentValues values = new ContentValues();	
		values.put("story_id", story_id);
	    values.put("title", title);
	    values.put("text", text);
	    db.insert("stories", null, values);
	}
	/**
	 * Inserts values into authors table
	 * tested, works*/
	public void insertToAuthorsTable(int author_id,String name, int story_id){
		ContentValues values = new ContentValues();	
		values.put("author_id", author_id);
	    values.put("name", name);
	    values.put("story_id", story_id);
	    db.insert("authors", null, values);
	}
	/**
	 * Inserts values into fragments table
	 * tested, works*/
	public void insertToFragmentsTable(int fragment_id,String text, int story_id){
		ContentValues values = new ContentValues();	
		values.put("fragment_id", fragment_id);
	    values.put("text", text);
	    values.put("story_id", story_id);
	    db.insert("fragments", null, values);
	}
	/**
	 * Inserts values into images table
	 * tested, works*/
	
	/**
	 * if isAnnotation==true, then photo is annotation, 1 is inserted into the is_annotaion column
	 * if isAnnotation==false, then photo is part of the fragment, 0 is inserted into the is_annotaion column*/
	public void insertToImagesTable(int image_id,String pointer, boolean isAnnotation, int fragment_id){
		ContentValues values = new ContentValues();	
		values.put("image_id", image_id);
		values.put("pointer", pointer);
	    values.put("is_annotation", isAnnotation);
	    values.put("fragment_id", fragment_id);
	    db.insert("images", null, values);
	}
	/**
	 * Inserts values into choices table
	 * tested, works*/
	public void insertToChoicesTable(int choice_id,int fragment_id){
		ContentValues values = new ContentValues();	
		values.put("choice_id", choice_id);
	    values.put("fragment_id", fragment_id);
	    db.insert("choices", null, values);
	}
	/**
	 * returns story ids, tested,works*/
	public List<Integer> getStoryIDs(){
		String getIDs="select story_id from stories";
		Cursor c=openForRead().db.rawQuery(getIDs, null);
		List<Integer> ids = new ArrayList<Integer>();
		c.moveToFirst();
		if (c != null ) {
			if  (c.moveToFirst()) {
				do {
					int id = c.getInt(0);
					ids.add(id);
				}while (c.moveToNext());
			}
		}  
		c.close();
		db.close();
		return ids;
	}
/**
 * return a map with story_id as a key, arrayList of authors as value, tested, works
 * */
	public  HashMap<Integer, List<String>> getBrowserViewInfo(){
		HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		List<Integer> ids= this.getStoryIDs();
		String temp="select name from authors where story_id=";
		String getAuthors;
		int length=ids.size();
		this.openForRead();
		for (int i =0;i<length;i++){
			List<String> authors = new ArrayList<String>();
			getAuthors=temp+Integer.toString(ids.get(i));
			Cursor c=db.rawQuery(getAuthors, null);
			c.moveToFirst();
			if (c != null ) {
				if  (c.moveToFirst()) {
					do {
						
						String author = c.getString(0);
						authors.add(author);
					}while (c.moveToNext());
					map.put(ids.get(i), authors);
				}
			}  
			c.close();
			
		}
		
		db.close();
		return map;
	}
}
