package com.timkonieczny.yuomeclickdummy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.timkonieczny.yuomeclickdummy.R;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
        // set a custom shadow that overlays the main content when the drawer opens
        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mMainMenuItems));
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
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {		//erstenScreen auswählen #######################################
            selectItem(1);
        }
        mDrawerLayout.openDrawer(mDrawerList);	//Beim Erstellen der Activity wird der Drawer geöffnet
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.splashscreen, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    /* Called whenever we call invalidateOptionsMenu() */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//         // The action bar home/up action should open or close the drawer.
//         // ActionBarDrawerToggle will take care of this.
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        // Handle action buttons
//        switch(item.getItemId()) {
////        case R.id.action_websearch:
////            // create intent to perform web search for this planet
////            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
////            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
////            // catch event that there's no activity to handle intent
////            if (intent.resolveActivity(getPackageManager()) != null) {
////                startActivity(intent);
////            } else {
////                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
////            }
////            return true;
//        default:
//            return super.onOptionsItemSelected(item);
//        }
//    }
    
    @Override
    public void onBackPressed() {
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {								//Auswahl der Fragments passend zum ListItem ###################
        if(position==0){													//Kamera wird geöffnet (neue Activity)
        	Intent intent = new Intent(this, ScanReceiptActivity.class);
        	startActivity(intent);
        }
        if(position==1){													//Schuldenübersicht
	    	// update the main content by replacing fragments
	        Fragment fragment = new OverviewFragment();
	
	        FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
	        
	        
	                		    
	        // update selected item and title, then close the drawer
	        mDrawerList.setItemChecked(position, true);
	        setTitle(mMainMenuItems[position]);
	        mDrawerLayout.closeDrawer(mDrawerList);
	     
        }
    	if(position==2){													//Meine Kassenzettel
	    	// update the main content by replacing fragments
	        Fragment fragment = new MyReceiptsFragment();
	        
	        FragmentManager fragmentManager = getFragmentManager();
		    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		    
		       
	        // update selected item and title, then close the drawer
	        mDrawerList.setItemChecked(position, true);
	        setTitle(mMainMenuItems[position]);
	        mDrawerLayout.closeDrawer(mDrawerList);
        }
        if (position==3){													//Kontoeinstellungen
        	Fragment fragment = new MyAccountFragment();
	        FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
	
	        // update selected item and title, then close the drawer
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

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class MyReceiptsFragment extends Fragment {					//jeweils eine Klasse für jedes Fragment erstellen #########################

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
            
            mAdapter = new SimpleAdapter(getActivity(),
            		buy_list,
            		 R.layout.fragment_my_receipts_item,
                     new String[] {"date", "place", "contacts", "value"},
                     new int[] {R.id.date, R.id.place, R.id.contacts, R.id.value});
            
            ListView myList = (ListView) rootView.findViewById(android.R.id.list);
	        myList.setAdapter(mAdapter);	
            
            return rootView;
        }
    }
    
    public static class OverviewFragment extends Fragment implements OnItemClickListener, OnClickListener {					//jeweils eine Klasse für jedes Fragment erstellen #########################
    	
    	private PopupWindow popupMessage;
    	private View rootView;
    	
        public OverviewFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.activity_overview, container, false);
            SimpleAdapter mAdapter;
            
        	ArrayList<HashMap<String,String>> depts_list = new ArrayList<HashMap<String,String>>();
            getActivity().setTitle("Übersicht");
            
        	        // Set up ListView example
        	        String[] groups = new String[]{
        	        		"Erik Harbeck",
        	        		"Nicolas Schwartau",
        	        		"Tim Konieczny"};
        	        
        	        Double[] balance = new Double[]{
        	        		3.99,
        	        		3.99,
        	        		3.99,};
        	        
        	        double balance_value = 0.0;
        	        for(Double value : balance){
        	        	balance_value = balance_value + value;
        	        }
        	        balance_value = Math.round(balance_value * 100) / 100.;
        	        
        	        TextView text = (TextView) rootView.findViewById(R.id.text4);
        	        text.setText(String.valueOf(balance_value) + "€   ");
        	        
        	        for(int index = 0; index < groups.length; index++){
        	        	HashMap<String, String> depts = new HashMap<String, String>();
        	        	depts.put("group", "   " + groups[index]);
        	        	depts.put("balance", balance[index].toString() + "€   ");
        	        	depts_list.add(depts);
        	        }
        	        
        	        mAdapter = new SimpleAdapter(getActivity(),
        	        		depts_list,
        	        		 R.layout.row_overview,
                             new String[] {"group", "balance"},
                             new int[] {R.id.text1,
                                     R.id.text2});
        	        
            ListView myList = (ListView) rootView.findViewById(android.R.id.list);
	        myList.setAdapter(mAdapter);
	        myList.setOnItemClickListener(this);
	        
	        LinearLayout layoutOfPopup = new LinearLayout(getActivity());
	        
	        popupMessage = new PopupWindow(layoutOfPopup, LayoutParams.WRAP_CONTENT,
	                LayoutParams.WRAP_CONTENT);
	        View popupLayout = inflater.inflate(R.layout.popup_overview, container, false);
	        
	        popupMessage.setContentView(popupLayout);
	        
	        Button button = (Button) popupLayout.findViewById(R.id.popup_button);
	        button.setOnClickListener(this);
	        
            return rootView;
        	}

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			if(popupMessage.isShowing()){
				popupMessage.dismiss();
			}
			popupMessage.showAsDropDown(arg1);
			
			}

			@Override
			public void onClick(View v) {
				RadioButton radio_button1 = (RadioButton) popupMessage.getContentView().findViewById(R.id.radioButton1);
		        radio_button1.setChecked(false);
		        RadioButton radio_button2 = (RadioButton) popupMessage.getContentView().findViewById(R.id.radioButton2);
		        radio_button2.setChecked(false);
				popupMessage.dismiss();
				// TODO Auto-generated method stub
				
			}
		
    }
    
    public static class MyAccountFragment extends Fragment implements OnItemClickListener {
    	
    	private ListView settings;
    	
        public MyAccountFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            
        	View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
            getActivity().setTitle("Mein Konto");
            settings = (ListView)rootView.findViewById(R.id.settings);
            ArrayAdapter<String> listAdapter ;
            
            String[] settingItems = {
            		"Username ändern",
            		"Passwort ändern",
            		"Abmelden",
            		"Konto löschen"	
            };
            ArrayList<String> settingsList = new ArrayList<String>();  
            settingsList.addAll( Arrays.asList(settingItems) );  
              
            // Create ArrayAdapter using the planet list.  
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.settings_list_view_item, settingsList);  
            settings.setAdapter( listAdapter );
            settings.setOnItemClickListener(this);
            return rootView;
        }

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			new AlertDialog.Builder(getActivity())
			.setTitle("Abmelden")
			.setMessage("Wirklich abmelden?")
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

			    public void onClick(DialogInterface dialog, int whichButton) {
			    	URL server;
					try {
						server = new URL("http://andibar.dyndns.org:5678/Yuome/log_off.php");
						URLConnection log_off = server.openConnection();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
			        Toast.makeText(getActivity(), "Erfolgreich abgemeldet.", Toast.LENGTH_SHORT).show();
			        Intent intent = new Intent(getActivity(), LoginActivity.class);
	                startActivity(intent);
			    }})
			 .setNegativeButton(android.R.string.no, null).show();
		
		}
    }
}