package com.timkonieczny.yuome;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyAccountFragment extends Fragment {
	
	private ListView settings;
	private String[] mSettingsMenuItems;
	
    public MyAccountFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
    	View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        getActivity().setTitle("Mein Konto");
        settings = (ListView)rootView.findViewById(R.id.settings);
        ArrayAdapter<String> listAdapter ;
        
        mSettingsMenuItems = getResources().getStringArray(R.array.settings_menu);
        
        ArrayList<String> settingsList = new ArrayList<String>();  
        settingsList.addAll( Arrays.asList(mSettingsMenuItems) );  
          
        listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.fragment_settings_list_item, settingsList);  
        settings.setAdapter( listAdapter );
        return rootView;
    }
}
