package com.timkonieczny.yuomeclickdummy;


import android.app.ListActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class OverviewActivity extends ListActivity {
	ListView listView;
	SimpleAdapter mAdapter;
	
	public OverviewActivity(View rootView, final SimpleAdapter mAdapter) {
        this.listView = (ListView) rootView.findViewById(android.R.id.list);
        this.mAdapter = mAdapter;
        
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
    // Create a ListView-specific touch listener. ListViews are given special treatment because
    // by default they handle touches for their list items... i.e. they're in charge of drawing
    // the pressed state (the list selector), handling list item clicks, etc.
	

	
}