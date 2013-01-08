package acv2server.apps.autoexpreso.fragments;

import acv2server.apps.autoexpreso.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 
 * @author joseacevedo
 *
 */
public class MainWindow extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState)
	{	
		//Ojo: attachToRoot tiene que ser false si no tira un error ?????
		return inflater.inflate(R.layout.online_view, container, false);
	}	
}
