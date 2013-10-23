package ca.ualberta.cs.cyoa_team05.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class UIWebView extends WebView implements UIWebViewInterface, UIWebViewJSInterface {
	
	private Context viewContext;
	private WebView webview;
	private WebSettings websettings;
	

	public UIWebView(Context context) {
		super(context);
		this.viewContext = context;
		
		this.webview = new WebView(viewContext);
		this.websettings = webview.getSettings();
		
		initialize();
	}

	@SuppressLint("SetJavaScriptEnabled")
	public void initialize() {
		
		this.websettings.setJavaScriptEnabled(true);
		this.websettings.setBuiltInZoomControls(true);
		
	}

	@Override
	public void loadURL(String url) {
		// Performs check on the url string for validity/security, perhaps like so:
		//
		// String assetFolder = "file:///android_asset/";
		// String extension   = ".html";
		// if(!url.contains(assetFolder) || !url.contains(extension)) {
		//		whatever...
		// }
		//
		// and then calls the WebView.loadUrl(String url) method
		
		this.webview.loadUrl(url);
		
	}
}
