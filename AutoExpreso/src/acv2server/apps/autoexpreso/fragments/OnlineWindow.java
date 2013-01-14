package acv2server.apps.autoexpreso.fragments;

import acv2server.apps.autoexpreso.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled") public class OnlineWindow extends Fragment {

	private WebView webView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState)
	{	
		//Ojo: attachToRoot tiene que ser false si no tira un error ?????
		View root =inflater.inflate(R.layout.normal_online_view, container, false);

		if(this.isInternetAvailable(container.getContext()))
		{
			webView = (WebView) root.findViewById(R.id.wv);

			WebSettings wb = webView.getSettings();
			wb.setJavaScriptEnabled(true);

			webView.setWebViewClient(new WebViewClient());

			Log.d("DEBUG", "WebView init");

			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(container.getContext());
			String website = prefs.getString("website_url", "https://m.autoexpreso.com/Login/Login");
			Log.d("DEBUG", "Autoexpreso URL: "+website );

			webView.loadUrl(website);
		}
		else
		{
			Toast t = Toast.makeText(container.getContext(), "No connection available", Toast.LENGTH_LONG);
			t.show();
		}

		return root;
	}

	/**
	 * 
	 * @param context the context of this ??? 
	 * @return true if it's connected, false otherwise.
	 */
	private boolean isInternetAvailable(Context context)
	{
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}
}
