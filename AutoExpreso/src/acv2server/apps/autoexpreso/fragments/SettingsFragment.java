package acv2server.apps.autoexpreso.fragments;

import acv2server.apps.autoexpreso.R;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.addPreferencesFromResource(R.xml.settings);
		
	}
	
	

}
