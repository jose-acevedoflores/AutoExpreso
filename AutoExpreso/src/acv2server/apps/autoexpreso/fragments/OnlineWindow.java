package acv2server.apps.autoexpreso.fragments;

import acv2server.apps.autoexpreso.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OnlineWindow extends Fragment {

	private WebView webView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState)
	{	
		//Ojo: attachToRoot tiene que ser false si no tira un error ?????
		View root =inflater.inflate(R.layout.normal_online_view, container, false);


		webView = (WebView) root.findViewById(R.id.wv);

		WebSettings wb = webView.getSettings();
		wb.setJavaScriptEnabled(true);

		webView.setWebViewClient(new WebViewClient());

		Log.d("DEBUG", "WebView init");


		webView.loadUrl("https://m.autoexpreso.com/Login/Login");

		return root;
	}

}
