package acv2server.apps.autoexpreso.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class HttpTask extends AsyncTask<String, Integer, String> {

	
	@Override
	protected String doInBackground(String... params) {
		// TODO Load the webpage with the credentials given by the user
		// params is an array that contains the url of AutoExpreso in [0] , username in [1] and password in [2] 
		
		String urlS = params[0];
		//String username = params[1];
		//String password = params[2];

		try{
			URL url = new URL(urlS);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			readStream(connection.getInputStream());
		}
		catch(Exception e)
		{
			Log.d("DEBUG", "Exploto");
		}
		
		return null;
	}

	
	@Override
	protected void onPostExecute(String result) {
		//TODO Update the UI 
		super.onPostExecute(result);
	}
	
	
	private void readStream(InputStream in) {
		  BufferedReader reader = null;
		  try {
		    reader = new BufferedReader(new InputStreamReader(in));
		    String line = "";
		    while ((line = reader.readLine()) != null) {
		    	Log.d("DEBUG", line);
		    }
		  } catch (IOException e) {
		    e.printStackTrace();
		  } finally {
		    if (reader != null) {
		      try {
		        reader.close();
		      } catch (IOException e) {
		        e.printStackTrace();
		        }
		    }
		  }
		} 
	

}
