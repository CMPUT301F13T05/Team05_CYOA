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

package com.uofa.adventure_app.enums;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
/**
 * 
 * @author ulvi, Joel Malina
 *
 */
public class DbHelper extends SQLiteOpenHelper {
	
	/**
	 * Stores strings to create tables
	 */
	static final String CreateStoryTable ="create table if not exists stories "+
										  "(story_id VARCHAR primary key,title VARCHAR not null);";
	static final String CreateUsersTable="create table if not exists users "+
										  "(user_id VARCHAR primary key,name VARCHAR not null,story_id VARCHAR,"+
										  "FOREIGN KEY(story_id) REFERENCES stories(story_id));";
	static final String CreateFragmentsTable="create table if not exists fragments "+
										  "(fragment_id VARCHAR primary key,text VARCHAR,story_id VARCHAR,title VARCHAR,firstflag integer,"+
										  "FOREIGN KEY(story_id) REFERENCES stories(story_id));";
	static final String CreateMediaTable = "create table if not exists media "+
										  "(media_id VARCHAR primary key,pointer VARCHAR,is_annotation boolean,fragment_id VARCHAR,"+
										  "FOREIGN KEY(fragment_id) REFERENCES fragments(fragment_id));";
	static final String CreateChoicesTable= "create table if not exists choices "+
										  "(fragment_id VARCHAR,choice_id VARCHAR,"+
										  "FOREIGN KEY(choice_id) REFERENCES fragments(fragments_id));";
	
	static final String CreateImagesTable = "create table if not exists images " + 
										  "(image_id VARCHAR primary key not null, path VARCHAR not null, is_annotation boolean not null, fragment_id VARCHAR not null,"
										  + "FOREIGN KEY(fragment_id) REFERENCES fragments(fragment_id));";
	
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LocalStorage.db";
/**
 * 
 * @param context
 */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
   /**
    * Creates tables
    */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateStoryTable);
        db.execSQL(CreateUsersTable);
        db.execSQL(CreateFragmentsTable);
        db.execSQL(CreateMediaTable);
        db.execSQL(CreateChoicesTable);
        db.execSQL(CreateImagesTable);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
}