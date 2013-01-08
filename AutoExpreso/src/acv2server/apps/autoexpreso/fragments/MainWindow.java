package acv2server.apps.autoexpreso.fragments;

import acv2server.apps.autoexpreso.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * 
 * @author joseacevedo
 *
 */
public class MainWindow extends Fragment {


	public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState)
	{	
		//Ojo: attachToRoot tiene que ser false si no tira un error ?????
		View root =inflater.inflate(R.layout.online_view, container, false);
		
		Button b = (Button) root.findViewById(R.id.bLogin);
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		return root;
	}
}
