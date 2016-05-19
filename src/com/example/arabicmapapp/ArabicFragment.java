package com.example.arabicmapapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ArabicFragment extends Fragment {
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	 
	        // Retrieving the currently selected item number
	        int position = getArguments().getInt("position");
	 
	        // List of rivers
	        String[] arabicContents = getResources().getStringArray(R.array.arabic_utilities);
	 
	        // Creating view correspoding to the fragment
	        View v = inflater.inflate(R.layout.fragment, container, false);
	 
	        // Getting reference to the TextView of the Fragment
	        TextView tv = (TextView) v.findViewById(R.id.tv_content);
	 
	        // Setting currently selected river name in the TextView
	        tv.setText(arabicContents[position]);
	 
	        return v;
	    }
	
	

}
