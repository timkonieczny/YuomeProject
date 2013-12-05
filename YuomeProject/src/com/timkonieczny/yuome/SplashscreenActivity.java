package com.timkonieczny.yuome;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.timkonieczny.yuome.R;

public class SplashscreenActivity extends Activity {
    
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mMainMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mMainMenuItems = getResources().getStringArray(R.array.main_menu);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, mMainMenuItems));	//Füllen der Liste im NavigationDrawer
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {		//ersten Screen auswählen
            selectItem(1);
        }
        mDrawerLayout.openDrawer(mDrawerList);	//Beim Erstellen der Activity wird der Drawer geöffnet
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {	//Up-Button zum Öffnen des Drawers
        if(item.getItemId()==android.R.id.home){
            mDrawerLayout.openDrawer(mDrawerList);
            return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
    
    @Override
    public void onBackPressed() {
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {								//Auswahl der Fragments passend zum ListItem
        Fragment fragment;
        FragmentManager fragmentManager;
    	switch(position){
    	
	        case 0:	
	        	Intent intent = new Intent(this, ScanReceiptActivity.class);	//Kamera
	    		startActivity(intent);
	    		
	        case 1:
	        	fragment = new OverviewFragment();								//Übersicht
		        fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		        
		        mDrawerList.setItemChecked(position, true);						//TODO: noch item farbig markieren
		        setTitle(mMainMenuItems[position]);
		        mDrawerLayout.closeDrawer(mDrawerList);
		        
	        case 2:
	        	fragment = new MyReceiptsFragment();							//Meine Kassenzettel
		        fragmentManager = getFragmentManager();
			    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			    
		        mDrawerList.setItemChecked(position, true);
		        setTitle(mMainMenuItems[position]);
		        mDrawerLayout.closeDrawer(mDrawerList);
		        
	        case 3:
	        	fragment = new MyAccountFragment();								//Mein Konto
		        fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
	
		        mDrawerList.setItemChecked(position, true);
		        setTitle(mMainMenuItems[position]);
		        mDrawerLayout.closeDrawer(mDrawerList);        	
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}