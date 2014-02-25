package com.timkonieczny.yuome;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class MyReceiptsFragment extends Fragment {					//jeweils eine Klasse für jedes Fragment erstellen #########################

    public MyReceiptsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_receipts, container, false);
        getActivity().setTitle("Meine Kassenzettel");
        
        ArrayList<HashMap<String, String>> buy_list = new ArrayList<HashMap<String,String>>();
        SimpleAdapter mAdapter;
        
        String[] date = new String[]{
        		"Einkauf vom 02.12.2013"};
        
        String[] place = new String[]{
		"Imagiär-Markt, Phantasieallee"};
        
        String[] contacts = new String[]{
				"mit Erik Harbeck, Nicolas Schwartau, Tim Konieczny"};
        
        String[] value = new String[]{
				"15.95"};
        
        for(int index = 0; index < contacts.length; index++){
        	HashMap<String, String> depts = new HashMap<String, String>();
        	depts.put("date", "  " + date[index]);
        	depts.put("place", "   " + place[index]);
        	depts.put("contacts", "   " + contacts[index]);
        	depts.put("value", value[index] + "   ");
        	buy_list.add(depts);
        }
        
//        mAdapter = new SimpleAdapter(getActivity(),
//        		buy_list,
//        		 R.layout.fragment_my_receipts_item,
//                 new String[] {"date", "place", "contacts", "value"},
//                 new int[] {R.id.date, R.id.place, R.id.contacts, R.id.value});
//        
//        ListView myList = (ListView) rootView.findViewById(android.R.id.list);
//        myList.setAdapter(mAdapter);	
//        
        return rootView;
    }
}
