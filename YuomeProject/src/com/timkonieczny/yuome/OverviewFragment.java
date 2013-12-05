package com.timkonieczny.yuome;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class OverviewFragment extends Fragment implements OnItemClickListener, OnClickListener {					//jeweils eine Klasse für jedes Fragment erstellen #########################
	
	private PopupWindow popupMessage;
	private View rootView;
	
    public OverviewFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        SimpleAdapter mAdapter;
        
//    	ArrayList<HashMap<String,String>> depts_list = new ArrayList<HashMap<String,String>>();
//        getActivity().setTitle("Übersicht");
//        
//    	        // Set up ListView example
//    	        String[] groups = new String[]{
//    	        		"Erik Harbeck",
//    	        		"Nicolas Schwartau",
//    	        		"Tim Konieczny"};
//    	        
//    	        Double[] balance = new Double[]{
//    	        		3.99,
//    	        		3.99,
//    	        		3.99,};
//    	        
//    	        double balance_value = 0.0;
//    	        for(Double value : balance){
//    	        	balance_value = balance_value + value;
//    	        }
//    	        balance_value = Math.round(balance_value * 100) / 100.;
//    	        
//    	        TextView text = (TextView) rootView.findViewById(R.id.text4);
//    	        text.setText(String.valueOf(balance_value) + "€   ");
//    	        
//    	        for(int index = 0; index < groups.length; index++){
//    	        	HashMap<String, String> depts = new HashMap<String, String>();
//    	        	depts.put("group", "   " + groups[index]);
//    	        	depts.put("balance", balance[index].toString() + "€   ");
//    	        	depts_list.add(depts);
//    	        }
//    	        
//    	        mAdapter = new SimpleAdapter(getActivity(),
//    	        		depts_list,
//    	        		 R.layout.row_overview,
//                         new String[] {"group", "balance"},
//                         new int[] {R.id.text1,
//                                 R.id.text2});
//    	        
//        ListView myList = (ListView) rootView.findViewById(android.R.id.list);
//        myList.setAdapter(mAdapter);
//        myList.setOnItemClickListener(this);
//        
//        LinearLayout layoutOfPopup = new LinearLayout(getActivity());
//        
//        popupMessage = new PopupWindow(layoutOfPopup, LayoutParams.WRAP_CONTENT,
//                LayoutParams.WRAP_CONTENT);
//        View popupLayout = inflater.inflate(R.layout.popup_overview, container, false);
//        
//        popupMessage.setContentView(popupLayout);
//        
//        Button button = (Button) popupLayout.findViewById(R.id.popup_button);
//        button.setOnClickListener(this);
        
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
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}

//		@Override
//		public void onClick(View v) {
//			RadioButton radio_button1 = (RadioButton) popupMessage.getContentView().findViewById(R.id.radioButton1);
//	        radio_button1.setChecked(false);
//	        RadioButton radio_button2 = (RadioButton) popupMessage.getContentView().findViewById(R.id.radioButton2);
//	        radio_button2.setChecked(false);
//			popupMessage.dismiss();
//			// TODO Auto-generated method stub
//			
//		}
	
}