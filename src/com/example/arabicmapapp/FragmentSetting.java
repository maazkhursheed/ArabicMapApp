package com.example.arabicmapapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

public class FragmentSetting extends DrawerScreen{
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState)  {
//		 getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
//	    	getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
	    	super.onCreate(savedInstanceState);
	    	
	    	  ViewGroup content=(ViewGroup) findViewById( R.id.frame_container);
	          getLayoutInflater().inflate(R.layout.fragment_setting,content,true);
	          initializeDrawer();
	          
	          android.support.v7.app.ActionBar ab =  this.getSupportActionBar() ;
	          setActionBarStyling("اعدادات");
	          ab.setTitle( "اعدادات"); 
	 }
	 


	
	 
	 
	 
//	 @Override
//		protected void onResume() {
//			getWindow().getDecorView().findViewById(android.R.id.content).invalidate();
//			super.onResume();
//		}


}
