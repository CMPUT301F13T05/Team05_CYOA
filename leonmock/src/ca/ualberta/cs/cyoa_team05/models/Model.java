package ca.ualberta.cs.cyoa_team05.models;

import java.util.ArrayList;
import java.util.Observable;

import ca.ualberta.cs.cyoa_team05.views.UIView;

public abstract class Model extends Observable {
	
	protected ArrayList<UIView> dependents;
	
	 /* Once message (data) has changed, the Model notifies all Observers that the
	  * state has changed by calling the update method of each observer 
	  
	  EXAMPLE: 
	  
	  public void setMessage( String message ) {
	     this.message = message;
	     notifyViews(); // calls setChanged(), then calls notifyObservers()
	 }
	 */
	 
	 public void addView( UIView view ) {
	        if (! this.dependents.contains( view )) {
	            this.dependents.add( view );
	        }
	    }

	 public void deleteView( UIView view ) {
		 this.dependents.remove( view );
		 }
	    
	 // This method notifies each of the Views that
	public void notifyViews() {
		    	
		for (UIView view : this.dependents) {
			
	       view.update((Observable) this, ""); // Should return null object or something rather than empty string object??
	    	setChanged();
	    	notifyObservers(); // clears changed flag
		}
	        
	}
}