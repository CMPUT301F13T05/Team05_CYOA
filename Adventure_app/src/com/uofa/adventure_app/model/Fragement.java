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
package com.uofa.adventure_app.model;

import java.util.ArrayList;
import java.util.SortedMap;

import com.uofa.adventure_app.interfaces.UniqueId;

public class Fragement extends UniqueId  {
        
        private SortedMap<Integer, Media> media; // Key will be a line number
        private ArrayList<Annotation> annotations;
        private ArrayList<Choice> choices;
        private String body;
        private Integer flag;
        private String title;
        
        public Fragement()
        {
            super();
            this.annotations = new ArrayList<Annotation>();
            this.choices = new ArrayList<Choice>(); 
            this.media = null;
            this.flag = 0;
            this.title = null;
            this.body = null;
        }      

        public Fragement(Integer flag)
        {
                super();
                this.annotations = new ArrayList<Annotation>();
                this.choices = new ArrayList<Choice>(); 
                this.media = null;
                this.flag = flag;
        }        
        
        public Fragement(String body, Integer flag)
        {
                this();
                this.setBody(body);
                this.flag = flag;
        }        
        
        /**
         * adds a choice to the fragment
         * @param choice
         */
        public void addChoice(Choice choice)
        {
                this.choices.add(choice);
        }

        /**
         * @return the body
         */
        public String body()
        {
                return body;
        }

        /**
         * @param body the body to set
         */
        public void setBody(String body)
        {
                this.body = body;
        }
        
        public ArrayList<Choice> choices()
        {
        	return this.choices;
        }
        
        public void setFlag(Integer flag)
        {
        	this.flag = flag;
        }
        
        public Integer getflag()
        {
        	return this.flag;
        }
        
        public void setTitle(String Title)
        {
        	this.title = title;
        }
        
        public String getTitle()
        {
        	return this.title;
        }

}