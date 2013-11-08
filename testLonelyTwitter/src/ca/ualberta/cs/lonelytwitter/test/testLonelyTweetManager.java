package ca.ualberta.cs.lonelytwitter.test;

import ca.ualberta.cs.lonelytwitter.LonelyTweetManager;
import ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity;
import android.test.ActivityInstrumentationTestCase2;

public class testLonelyTweetManager extends
		ActivityInstrumentationTestCase2<LonelyTwitterActivity> {

	public testLonelyTweetManager() {
		super(LonelyTwitterActivity.class);
		// TODO Auto-generated constructor stub
	}
	public void testSave(){
		LonelyTweetManager ltm = new LonelyTweetManager(this.getActivity());
		ltm.save();
		
	}
	
	

}
