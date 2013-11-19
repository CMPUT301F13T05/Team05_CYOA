package com.uofa.adventure_app.interfaces;

import java.util.ArrayList;

public interface Parser<T> {
	
	public ArrayList<T> parse(String string);

}
