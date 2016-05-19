package com.example.arabicmapapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentInfo extends DrawerScreen {
	
	 public boolean mUserLearnedDrawer;
	 private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
	 SharedPreferences sp;
	
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
    	super.onCreate(savedInstanceState); {
//        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_info, null);
        ViewGroup content=(ViewGroup) findViewById( R.id.frame_container);       
        getLayoutInflater().inflate(R.layout.fragment_info,content,true);
        initializeDrawer();
        
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        
        android.support.v7.app.ActionBar ab =  this.getSupportActionBar() ;
        setActionBarStyling("عن التطبيق");
        ab.setTitle( "عن التطبيق");
        
        
        
    }
	 
 }

	@Override
	public void onBackPressed() {
		
		super.onBackPressed();
		
		overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	}

	
	
	
//	@Override
//	protected void onResume() {
//		getWindow().getDecorView().findViewById(android.R.id.content).invalidate();
//		super.onResume();
//	}

}
