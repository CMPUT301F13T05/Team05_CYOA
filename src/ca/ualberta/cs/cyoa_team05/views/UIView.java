package ca.ualberta.cs.cyoa_team05.views;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;

public abstract class UIView extends Activity implements Observer{
	
	@Override
	public void update(Observable observable, Object data) {
		// MUST Override this method in derived classes
		
	}
}
