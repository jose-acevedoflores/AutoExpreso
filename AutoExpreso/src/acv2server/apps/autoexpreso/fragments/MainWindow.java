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
import android.widget.EditText;
import android.widget.Toast;


/**
 * 
 * @author joseacevedo
 *
 */
public class MainWindow extends Fragment {

	private EditText usernameET;
	private EditText passwordET;

	public View onCreateView(LayoutInflater inflater, final ViewGroup container , Bundle savedInstanceState)
	{	
		//Ojo: attachToRoot tiene que ser false si no tira un error ?????
		View root =inflater.inflate(R.layout.mod_online_view, container, false);

		Button b = (Button) root.findViewById(R.id.bLogin);
		usernameET = (EditText) root.findViewById(R.id.etUsername);
		passwordET = (EditText) root.findViewById(R.id.etPassword);

		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("DEBUG", "Clicked");
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(container.getContext());

				boolean keepMeLogged = prefs.getBoolean("keep_user_logged", false);
				String username;
				String password;
				//If the user wants to stay logged check settings values for username and password.
				if(keepMeLogged)
				{
					username = prefs.getString("username_preference", "");
					password = prefs.getString("password_preference", "");
					//If the user checked the keepmelooged in flag but didn't provided values for the username and password
					//fields try getting values from the EditText Views. 
					if(username.equals("") || password.equals(""))
					{
						Toast t = Toast.makeText(container.getContext(), "No username or password found in settings", Toast.LENGTH_SHORT);
						t.show();
						username = usernameET.getText().toString();
						password = passwordET.getText().toString(); 
					}
				}
				//If the user did not checked the keepmelooged in flag fields try getting values from the EditText Views. 
				else
				{
					username = usernameET.getText().toString();
					password = passwordET.getText().toString(); 
				}
				String url = prefs.getString("website_url", "https://m.autoexpreso.com/Login/Login");

				//Try to authenticate with the user given credentials.
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
