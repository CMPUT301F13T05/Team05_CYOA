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
	/**
	 * 
	 * @param ctx
	 */
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
	 * deletes database*/
	public int deleteAll(){
    	return db.delete(DbHelper.DATABASE_NAME, null, null);
    	}
	/**
	 * Inserts values to the Stories table
	 * tested, works*/
	public void insertIntoStoriesTable(String title){
		ContentValues values = new ContentValues();
		System.out.println(title);
	    values.put("title", title);
	    System.out.println(values);
	    db.insert("stories", null, values);
	}
	/**
	 * Inserts values into users table
	 * tested, works*/
	public void insertIntoUsersTable(String name, int story_id, String fOrS){
		ContentValues values = new ContentValues();	
	    values.put("name", name);
	    values.put("story_id", story_id);
	    values.put("f_or_s", fOrS);
	    db.insert("users", null, values);
	}
	/**
	 * Inserts values into fragments table
	 * tested, works*/
	public void insertIntoFragmentsTable(String text, int story_id,String title){
		ContentValues values = new ContentValues();	
	    values.put("text", text);
	    values.put("story_id", story_id);
	    values.put("title", title);
	    db.insert("fragments", null, values);
	}
	/**
	 * Inserts values into images table
	 * tested, works*/
	
	/**
	 * if isAnnotation==true, then photo is annotation, 1 is inserted into the is_annotaion column
	 * if isAnnotation==false, then photo is part of the fragment, 0 is inserted into the is_annotaion column*/
	public void insertIntoImagesTable(int image_id,String pointer, boolean isAnnotation, int fragment_id){
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
	public void insertIntoChoicesTable(int fragment_id,int choice_id){
		ContentValues values = new ContentValues();	
		values.put("fragment_id", fragment_id);
	    values.put("choice_id", choice_id);
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
	
	public List<Integer> getFragmentIDs(){
		String getIDs="select fragment_id from fragments";
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
 * returns a map with story_id as a key.The arrayList is a value part  of the map, which contains story title as a first element,
 * the rest of the elements are users associated with the given story id, tested, works
 * */
	public  HashMap<Integer, List<String>> getBrowserViewInfo(){
		HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		List<Integer> ids= this.getStoryIDs();
		String temp="select name from users where story_id=";
		String getUsers;
		int length=ids.size();
		this.openForRead();
		for (int i =0;i<length;i++){
			List<String> output = new ArrayList<String>();
			output.add(this.getTitle(ids.get(i)));
			getUsers=temp+Integer.toString(ids.get(i)) + " and f_or_s='s'";
			Cursor c=db.rawQuery(getUsers, null);
			c.moveToFirst();
			if (c != null ) {
				if  (c.moveToFirst()) {
					do {
						
						String user = c.getString(0);
						output.add(user);
					}while (c.moveToNext());
					map.put(ids.get(i), output);
				}
			}  
			c.close();
			
		}
		
		db.close();
		return map;
	}
	/**
	 * returns title of the story, story_id given, tested, works*/
	public String getTitle(int story_id){
		String getTitleString="select title from stories where story_id="+story_id;
		String title=null;
		this.openForRead();
		Cursor titlec = db.rawQuery(getTitleString, null);
		titlec.moveToFirst();
		if (titlec != null ) {
			if  (titlec.moveToFirst()) {
				do {
					title = titlec.getString(0);
				}while (titlec.moveToNext());
			}
		}  
		titlec.close();
		
		return title;
	}
	/**
	 * returns list of users, given story_id
	 * tested, works*/
	public List<String> getUsers(int story_id, String fOrs){
		String getUsersString="select name from users where story_id="+story_id+" and f_or_s="+fOrs;
		List<String> users = new ArrayList<String>();
		this.openForRead();
		Cursor userc=db.rawQuery(getUsersString, null);
		userc.moveToFirst();
		if (userc != null ) {
			if  (userc.moveToFirst()) {
				do {
					users.add(userc.getString(0));
				}while (userc.moveToNext());
			}
		}  
		userc.close();
		return users;
	}
	/**
	 * 
	 * @param fragment_id
	 * @return
	 */
	public List<String> getChoices(int fragment_id){
		String getChoicesString="select choice_id from choices where fragment_id="+fragment_id;
		List<String> choices = new ArrayList<String>();
		String choiceID;
		this.openForRead();
		Cursor choicec=db.rawQuery(getChoicesString, null);
		choicec.moveToFirst();
		if (choicec != null ) {
			if  (choicec.moveToFirst()) {
				do {
					choiceID=choicec.getString(0);
					choices.add(choiceID);
				}while (choicec.moveToNext());
			}
		}  
		choicec.close();
		return choices;
	}
	/**
	 * returns List of Lists (String)
	 * 1st element is Story title, has type of List<String>, use getStory(story_id).get(0).get(0) to get title as String
	 * 2nd element is Story users, has type of List<String>
	 * 3rd element is 1st fragments' choices, has type of List<String>
	 * 4th element is body of the 1st fragment, has type of List<string>, use getStory(story_id).get(3).get(0) to get body as String
	 * ATENTION!!!
	 * This method assumes that, fragments are ordered. That is, first fragment of the given story is above 
	 * all other fragments of that story
	 * */
	
	public List<List<String>> getStory(int story_id){
		List<List<String>> storyInfo =new ArrayList<List<String>>();
		List<String> storyTitle = new ArrayList<String>();
		storyTitle.add(this.getTitle(story_id));
		List<String> users = new ArrayList<String>();
		users=this.getUsers(story_id,"s");
		
		String firstFragment="select fragment_id from fragments where story_id="+story_id+" limit 1";
		int firstFragmentID=0;
		Cursor fragc=db.rawQuery(firstFragment, null);
		fragc.moveToFirst();
		if (fragc != null ) {
			if  (fragc.moveToFirst()) {
				do {
					firstFragmentID=Integer.parseInt(fragc.getString(0));
					//frags.add(choiceID);
				}while (fragc.moveToNext());
			}
		}  
		fragc.close();
		List<String> choices = new ArrayList<String>();
		choices=getChoices(firstFragmentID);
		List<String> fragmentBody = new ArrayList<String>();
		String getBody="select text from fragments where fragment_id="+firstFragmentID;
		Cursor bodyc=db.rawQuery(getBody, null);
		bodyc.moveToFirst();
		if (bodyc != null ) {
			if  (bodyc.moveToFirst()) {
				do {
					fragmentBody.add(bodyc.getString(0));
				}while (bodyc.moveToNext());
			}
		}  
		bodyc.close();
		storyInfo.add(storyTitle);
		storyInfo.add(users);
		storyInfo.add(choices);
		storyInfo.add(fragmentBody);
		return storyInfo;
	}
	/**
	 * 
	 * @param title
	 * @param user
	 * @return
	 */
	public int setStory(String title, String user){
		this.openForWrite();
		this.insertIntoStoriesTable(title);
		this.close();
		List<Integer> ids=this.getStoryIDs();
		int story_id=ids.get(ids.size()-1);
		this.openForWrite();
		this.insertIntoUsersTable(user, story_id, "s");
		this.close();
		return story_id;
	}
	/***/
	public int setFragment(int story_id,String fragmentTitle,String user,String fragmentBody,int prevFragmentId){
			this.openForWrite();
			this.insertIntoFragmentsTable(fragmentBody, story_id, fragmentTitle);
			this.close();
			List<Integer> ids=this.getFragmentIDs();
			int currentFragmentID=ids.get(ids.size()-1);
			if(prevFragmentId!=0){
				this.openForWrite();
				this.insertIntoChoicesTable(prevFragmentId, currentFragmentID);
				this.close();
			}
			return currentFragmentID;
	}
}
