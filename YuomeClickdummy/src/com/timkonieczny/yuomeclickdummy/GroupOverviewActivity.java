package com.timkonieczny.yuomeclickdummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class GroupOverviewActivity extends ListActivity {
	    SimpleAdapter mAdapter;
	    ArrayList<HashMap<String,String>> depts_list = new ArrayList<HashMap<String,String>>();

	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_overview);

	        // Set up ListView example
	        String[] groups = new String[]{
	        		"Wohngemeinschaft",
	        		"Studentenverbindung",
	        		"Freunde",
	        		"Familie",
	        		"Schulklasse"};
	        
	        Double[] balance = new Double[]{
	        		4.0,
	        		4.5,
	        		5.6,
	        		5.6,
	        		4.4,};
	        
	        
	        for(int index = 0; index < groups.length; index++){
	        	HashMap<String, String> depts = new HashMap<String, String>();
	        	depts.put("group", groups[index]);
	        	depts.put("balance", balance[index].toString());
	        	depts_list.add(depts);
	        }
	        
	        mAdapter = new SimpleAdapter(this,
	        		depts_list,
	        		 R.layout.row_overview,
                     new String[] {"group", "balance"},
                     new int[] {R.id.text1,
                             R.id.text2});
	        setListAdapter(mAdapter);

	        ListView listView = getListView();
	        // Create a ListView-specific touch listener. ListViews are given special treatment because
	        // by default they handle touches for their list items... i.e. they're in charge of drawing
	        // the pressed state (the list selector), handling list item clicks, etc.
	        SwipeDismissListViewTouchListener touchListener =
	                new SwipeDismissListViewTouchListener(
	                        listView,
	                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
	                            @Override
	                            public boolean canDismiss(int position) {
	                                return true;
	                            }

	                            @Override
	                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
	                                for (int position : reverseSortedPositions) {
	                                   // mAdapter.remove(mAdapter.getItem(position));
	                                }
	                                mAdapter.notifyDataSetChanged();
	                            }
	                        });
	        listView.setOnTouchListener(touchListener);
	        // Setting this scroll listener is required to ensure that during ListView scrolling,
	        // we don't look for swipes.
	        listView.setOnScrollListener(touchListener.makeScrollListener());
	    }

	    @Override
	    protected void onListItemClick(ListView listView, View view, int position, long id) {
	        Toast.makeText(this,
	                "Clicked " + getListAdapter().getItem(position).toString(),
	                Toast.LENGTH_SHORT).show();
	    }
}
