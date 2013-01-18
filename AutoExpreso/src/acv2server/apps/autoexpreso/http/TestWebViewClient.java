package acv2server.apps.autoexpreso.http;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TestWebViewClient extends WebViewClient {

	private String username;
	private String password;
	private String loginUrl;
	public TestWebViewClient(String username, String password, String loginUrl)
	{
		super();
		this.loginUrl = loginUrl;
		this.username= username ;
		this.password = password;
	}

	@Override
	public void onPageFinished(WebView view, String url) 
	{      
		if(url.equals(loginUrl))
		{
			view.loadUrl("javascript:document.getElementById('Username').value = '"+username+"';");

			view.loadUrl("javascript:document.getElementById('Password').value = '"+password+"';");

			view.loadUrl("javascript:document.forms['myLoginForm'].submit()");
			Log.d("DEBUG", "onPageFinished");
		}
		Log.d("DEBUG", "Outside onPageFinished");

	}

}
