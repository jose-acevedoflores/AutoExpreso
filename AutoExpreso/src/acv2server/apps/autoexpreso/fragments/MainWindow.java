package acv2server.apps.autoexpreso.fragments;

import acv2server.apps.autoexpreso.R;
import acv2server.apps.autoexpreso.http.HttpTask;
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
import android.widget.Button;
import android.widget.Toast;


/**
 * 
 * @author joseacevedo
 *
 */
public class MainWindow extends Fragment {

	public View onCreateView(LayoutInflater inflater, final ViewGroup container , Bundle savedInstanceState)
	{	
		//Ojo: attachToRoot tiene que ser false si no tira un error ?????
		View root =inflater.inflate(R.layout.mod_online_view, container, false);

		Button b = (Button) root.findViewById(R.id.bLogin);

		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("DEBUG", "Clicked");
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(container.getContext());
				String username = prefs.getString("username_preference", "");
				String password = prefs.getString("password_preference", "");
				String url = prefs.getString("website_url", "https://m.autoexpreso.com/Login/Login");

				if(username.equals("") || password.equals(""))
				{
					Toast t = Toast.makeText(container.getContext(), "Can't leave username or password empty", Toast.LENGTH_LONG);
					t.show();
				}
				else{
					//TODO Check if connection is available
					if(isInternetAvailable(container.getContext()) )
						new HttpTask().execute(url, username, password);
					else
					{
						Toast t = Toast.makeText(container.getContext(), "No connection available", Toast.LENGTH_LONG);
						t.show();
					}
				}
			}
		});

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
