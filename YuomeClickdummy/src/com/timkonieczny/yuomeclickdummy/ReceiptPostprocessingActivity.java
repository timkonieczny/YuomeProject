/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.timkonieczny.yuomeclickdummy;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
//import com.timkonieczny.yuomeclickdummy.R;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ReceiptPostprocessingActivity extends ListActivity {
    ArrayAdapter<String> mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        
        final SimpleAdapter mAdapter;
        
    	final ArrayList<HashMap<String,String>> depts_list = new ArrayList<HashMap<String,String>>();
        setTitle("Artikel");
        
    	        // Set up ListView example
    	        String[] groups = new String[]{
    	        		"Käse",
    	        		"Schinken",
    	        		"Brot",
    	        		"Eier",
    	        		"Astra",
    	        		"Pizza",
    	        		"Milch"};
    	        
    	        Double[] balance = new Double[]{
    	        		1.95,
    	        		2.49,
    	        		1.49,
    	        		1.45,
    	        		4.99,
    	        		1.99,
    	        		0.59};
    	        
    	        double balance_value = 0.0;
    	        for(Double value : balance){
    	        	balance_value = balance_value + value;
    	        }
    	        
    	        TextView text = (TextView) findViewById(R.id.text4);
    	        text.setText(String.valueOf(balance_value) + "€   ");
    	        
    	        for(int index = 0; index < groups.length; index++){
    	        	HashMap<String, String> depts = new HashMap<String, String>();
    	        	depts.put("group", "   " + groups[index]);
    	        	depts.put("balance", balance[index].toString() + "€   ");
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
                                	HashMap<String, String> test = new HashMap<String, String>();
                                    test = (HashMap<String, String>) mAdapter.getItem(position);
                                    depts_list.remove(test);
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
    protected double calculateValue(ArrayList<HashMap<String, Double>> depts_list){
		return 0;
    	
    }
}
