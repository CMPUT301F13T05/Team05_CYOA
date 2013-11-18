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
import java.util.SortedMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.uofa.adventure_app.enums.DbHelper;
import com.uofa.adventure_app.model.Annotation;
import com.uofa.adventure_app.model.Choice;
import com.uofa.adventure_app.model.Fragement;
import com.uofa.adventure_app.model.Media;
import com.uofa.adventure_app.model.Story;
import com.uofa.adventure_app.model.User;

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
	 * open writable database
	 * @return current object
	 * @throws SQLException
	 */

	public LocalStorageController openForWrite() throws SQLException{
    	db=DBHelper.getWritableDatabase();
    	return this;
    }
	/**

	 * open database for read
	 * @return current object
	 * @throws SQLException
	 */
	public LocalStorageController openForRead() throws SQLException{	
    	db=DBHelper.getReadableDatabase();
    	return this;
    }
	/**

	 * close database
	 */
	public void close(){
    	DBHelper.close();
	}
	public void dropTable(String tableName){
		String dropString="DROP TABLE IF EXISTS "+ tableName;
		db.execSQL(dropString);
	}
	/**
	 * inserts values into stories table
	 * @param title
	 */
	public void insertIntoStoriesTable(String storyId,String title){
		ContentValues values = new ContentValues();
		values.put("story_id", storyId);
	    values.put("title", title);
	    db.insert("stories", null, values);

	}
	/**

	 * Inserts values into users table, If the user is author of story, insert 0 into fragment_id column, 
	 * If user is author of fragment, insert '0' into story_id column
	 * @param name
	 * @param story_id
	 * @param fragmentId
	 * @param fOrS
	 */
	public void insertIntoUsersTable(String userId,String name, String story_id){
		ContentValues values = new ContentValues();
		System.out.println(userId +", " + name + ", "+ story_id);
		values.put("user_id", userId);
	    values.put("name", name);
	    values.put("story_id", story_id);
	    db.insert("users", null, values);
	}
	
	/**
	 * Inserts values into fragments table
	 * @param text
	 * @param story_id
	 * @param title
	 */
	public void insertIntoFragmentsTable(String fragmentId,String text, String story_id,String title, Integer flag){
		ContentValues values = new ContentValues();	
		values.put("fragment_id", fragmentId);
	    values.put("text", text);
	    values.put("story_id", story_id);
	    values.put("title", title);
	    values.put("firstflag", flag);
	    db.insert("fragments", null, values);
	}
	/**
	 * Inserts values into images table
	 * if isAnnotation==true, then photo is annotation, 1 is inserted into the is_annotaion column
	 * if isAnnotation==false, then photo is part of the fragment, 0 is inserted into the is_annotaion column
	 * @param image_id
	 * @param pointer
	 * @param isAnnotation
	 * @param fragment_id
	 */
	public void insertIntoMediaTable(String mediaId,String pointer, boolean isAnnotation, int fragment_id){
		ContentValues values = new ContentValues();	
		values.put("media_id", mediaId);
		values.put("pointer", pointer);
	    values.put("is_annotation", isAnnotation);
	    values.put("fragment_id", fragment_id);
	    db.insert("images", null, values);
	}
	/**
	 * Inserts values into choices table
	 * @param fragment_id
	 * @param choice_id
	 */
	public void insertIntoChoicesTable(String fragment_id,String choice_id){
		ContentValues values = new ContentValues();	
		values.put("fragment_id", fragment_id);
	    values.put("choice_id", choice_id);
	    db.insert("choices", null, values);
	}
	/**
	 * returns story ids, tested,works
	 * @return List<Integer> ids

	 */
	public List<String> getStoryIDs(){
		String getIDs="select story_id from stories";
		Cursor c=openForRead().db.rawQuery(getIDs, null);
		List<String> ids = new ArrayList<String>();
		c.moveToFirst();
		if (c != null ) {
			if  (c.moveToFirst()) {
				do {
					String id = c.getString(0);
					ids.add(id);
				}while (c.moveToNext());
			}
		}  
		c.close();
		db.close();
		return ids;
	}
	
	/**
	 * returns all fragment ids stored locally
	 * @return List<Integer> ids
	 */
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
 * the rest of the elements are users associated with the given story id
 * @return HashMap<Integer, List<String>> map
 */
	public  HashMap<String, List<String>> getBrowserViewInfo(){
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> ids= this.getStoryIDs();
		String temp="select name from users where story_id=";
		List<String> users = new ArrayList<String>();
		int length=ids.size();
		this.openForRead();
		for (int i =0;i<length;i++){
			List<String> output = new ArrayList<String>();
			output.add(this.getTitle(ids.get(i)));
			users=this.getUsers(ids.get(i), "s");
			output.addAll(users);
			map.put(ids.get(i), output);	
		}
		
		db.close();
		//System.out.println(map);
		return map;
	}
	/**
	 * returns title of the story, story_id given
	 * @param story_id
	 * @return String title
	 */
	public String getTitle(String story_id){
		//System.out.println(story_id);
		String getTitleString="select title from stories where story_id="+"'"+story_id+"'";
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
		//System.out.println("title: " + title);
		return title;
	}
	/**
	 * id is either story_id or fragment_id, according to the value of fOrs method returns list of users 
	 * @param id
	 * @param fOrs
	 * @return List<String> users
	 */
	public List<String> getUsers(String id, String fOrs){
		String getUsersString = new String();
		if(fOrs=="s")
			getUsersString="select name from users where story_id='"+id+"'";
		else if(fOrs=="f"){
			int f_id=Integer.parseInt(id);
			getUsersString="select name from users where fragment_id="+f_id+" and f_or_s='"+fOrs+"'";
		}
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
	 * returns choice ids for a given fragment
	 * @param fragment_id
	 * @return List<String> choices
	 */
	public List<String> getChoices(String fragment_id){
		String getChoicesString="select choice_id from choices where fragment_id='"+fragment_id+"'";
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
	 * * returns List of Lists (String)
	 * 1st element is Story title, has type of List<String>, use getStory(story_id).get(0).get(0) to get title as String
	 * 2nd element is Story users, has type of List<String>
	 * 3rd element is 1st fragments' choices, has type of List<String>
	 * 4th element is body of the 1st fragment, has type of List<string>, use getStory(story_id).get(3).get(0) to get body as String
	 * ATENTION!!!
	 * This method assumes that, fragments are ordered. That is, first fragment of the given story is above 
	 * all other fragments of that story
	 * @param story_id
	 * @return List<List<String>> storyInfo
	 */
	
	public List<List<String>> getStory(String story_id){
		List<List<String>> storyInfo =new ArrayList<List<String>>();
		List<String> storyTitle = new ArrayList<String>();
		storyTitle.add(this.getTitle(story_id));
		//System.out.println(storyTitle);
		List<String> users = new ArrayList<String>();
		users=this.getUsers(story_id,"s");
		
		String firstFragment="select fragment_id from fragments where story_id='"+story_id+"' AND firstflag = 1";
		String firstFragmentID=null;
		Cursor fragc=db.rawQuery(firstFragment, null);
		fragc.moveToFirst();
		if (fragc != null ) {
			if  (fragc.moveToFirst()) {
				do {
					firstFragmentID=fragc.getString(0);
					//frags.add(choiceID);
				}while (fragc.moveToNext());
			}
		}  
		fragc.close();
		System.out.println("fragmentid: "+firstFragmentID+"");
		List<String> choices = new ArrayList<String>();
		choices=getChoices(firstFragmentID);
		List<String> fragmentBody = new ArrayList<String>();
		String getBody="select text from fragments where fragment_id='"+firstFragmentID+"'";
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
	 * Stores title, story_id into stories table
	 * Stores user to stories table. Third argument is 0, which identifies that author of story is inserted
	 * @param title
	 * @param userStory
	 * @return story_id
	 */
	public void setStory(String storyId, String title,String userId, String user){
		//System.out.println("in setstory story id: "+storyId);
		this.openForWrite();
		this.insertIntoStoriesTable(storyId,title);
		this.close();
		this.openForWrite();
		this.insertIntoUsersTable(userId,user, storyId);
		this.close();
	}
	/**
	 * Inserts fragment body, story id, fragment title into fragments table
	 * Inserts user to users table. 
	 * If previous fragment id is not 0, inserts current fragment as a choice for the previous fragment 
	 * @param story_id
	 * @param fragmentTitle
	 * @param user
	 * @param fragmentBody
	 * @param prevFragmentId
	 * @return current Fragment id
	 */
	public void setFragment(String story_id,String currentFragmentId,String fragmentTitle,String fragmentBody,String prevFragmentId, Integer first){
			this.openForWrite();
			this.insertIntoFragmentsTable(currentFragmentId,fragmentBody, story_id, fragmentTitle, first);
			this.close();
			if(prevFragmentId != null){
				this.openForWrite();
				this.insertIntoChoicesTable(prevFragmentId, currentFragmentId);
				this.close();
			}

	}

	
	/**
	 * 1st element is Fragment title, has type of List<String>, use getFragment(fragmentId).get(0).get(0) to get title as String
	 * 3rd element is fragments' choices, has type of List<String>
	 * 4th element is body of the fragment, has type of List<string>, use getFragment(fragment_id).get(3).get(0) to get body as String
	 * @param fragmentId
	 * @return List<List<String>> fragment
	 */
	public List<List<String>> getFragment(String fragmentId){
		List<List<String>> fragment =new ArrayList<List<String>>();
		List<String> fragmentTitle = new ArrayList<String>();
		List<String> fragmentBody = new ArrayList<String>();
		List<String> fragmentChoices = new ArrayList<String>();
		String getFragmentSql="select * from fragments where fragment_id='"+fragmentId+"'";
		this.openForRead();
		Cursor fc = db.rawQuery(getFragmentSql, null);
		fc.moveToFirst();
		if (fc != null ) {
			if  (fc.moveToFirst()) {
				do {
					fragmentBody.add(fc.getString(1));
					fragmentTitle.add(fc.getString(3));
				}while (fc.moveToNext());
			}
		}  
		fragmentChoices=this.getChoices(fragmentId);
		fragment.add(fragmentTitle);
		fragment.add(fragmentChoices);
		fragment.add(fragmentBody);
		fc.close();
		
		return fragment;
	}
	/**
	 * This method will return the fragment is of the first fragment of a story
	 * @param String s_id
	 * @return Integer
	 */
	public Integer getFirstFragment(String s_id){
		Integer frag_id = null;
		String getFragmentSql="select fragment_id from fragments where story_id='"+s_id+"'";
		this.openForRead();
		Cursor c = db.rawQuery(getFragmentSql, null);
		List<Integer> ids = new ArrayList<Integer>();
		c.moveToFirst();
		if (c != null ) {
			if  (c.moveToFirst()) {
				do {
					frag_id = c.getInt(0);
					ids.add(frag_id);
				}while (c.moveToNext());
			}
		} 
		c.close();
		return ids.get(0);
	}
	public int getLastId(String tableName,String columnName ){
		int lastId=0;
		String getLastIdSql="SELECT LAST('"+columnName+"') FROM '"+tableName+"'";
		Cursor c=openForRead().db.rawQuery(getLastIdSql, null);
		c.moveToFirst();
		if (c != null ) {
			if  (c.moveToFirst()) {
				do {
					lastId = c.getInt(0);
				}while (c.moveToNext());
			}
		}  
		c.close();
		db.close();
		return lastId;
	}

	
	public void cacheStory(Story s){
		String storyId=s.id().toString();
		String storyTitle = s.title();
		ArrayList<User> storyUsers;
		storyUsers=s.users();
		ArrayList<Fragement> storyFragments;
		ArrayList<Choice> fragmentChoices;
		ArrayList<Annotation> fragmentAnnotations;
		SortedMap<Integer, Media> fragmentMedia;
		storyFragments=s.getFragements();
		String userName;
		String userId;
		String fragmentBody;
		String fragmentId;
		String choiceId;
		Integer flag;
		String fragTitle;
		this.openForWrite();
		//adding story to stories table
		this.insertIntoStoriesTable(storyId,storyTitle);
		for (int i=0;i<storyUsers.size();i++){
			userName=storyUsers.get(i).getName();
			userId=storyUsers.get(i).uid().toString();
			//adding users into users table
			this.insertIntoUsersTable(userId, userName, storyId);
		}
		//int fragLastId=this.getLastId("fragments", "fragment_id");
		for (int i=0;i<storyFragments.size();i++){
			fragmentBody=storyFragments.get(i).body();
			fragmentId=storyFragments.get(i).uid().toString();
			flag = storyFragments.get(i).getflag();
			fragTitle = storyFragments.get(i).getTitle();
			this.insertIntoFragmentsTable(fragmentId,fragmentBody, storyId, fragTitle, flag);
			fragmentChoices=storyFragments.get(i).choices();
			for(int j=0;j<fragmentChoices.size();j++){
				choiceId=fragmentChoices.get(j).getChoice().uid().toString();
				this.insertIntoChoicesTable(fragmentId, choiceId);
				//choices added for each fragment
			}
			
		}
		this.close();
		
		

	}
}
