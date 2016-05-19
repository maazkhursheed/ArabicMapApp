package com.example.arabicmapapp;

import java.util.ArrayList;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DrawerScreen extends AppCompatActivity{
	
	
	 

	int[] mArabiImages = new int[]{
			 R.drawable.adab_arabi,			 
			 R.drawable.fav_icon,
			 R.drawable.search_icon,
			 R.drawable.about_icon,
			 R.drawable.contact_us,
			 R.drawable.site_store
			 };
	
	protected DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence drawerTitle;
    private CharSequence mTitle;
    private String[] navMenuTitles;
    protected NavDrawerAdapter adapter;
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_screen_layout);
		//setActionBarStyling("xyz");
		initializeDrawer();
		
		
	}


//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		
//		return super.onCreateOptionsMenu(menu);
//	}


//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		if (drawerToggle.onOptionsItemSelected(item)) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}


	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		
		return super.onPrepareOptionsMenu(menu);
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}

	protected void initializeDrawer() {
		navMenuTitles=getResources().getStringArray(R.array.arabic_utilities);
		drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		drawerList=(ListView)findViewById(R.id.list_slidermenu);
		
		mTitle = drawerTitle = getTitle();

		adapter = new NavDrawerAdapter(getApplicationContext(), navMenuTitles ,mArabiImages);
		drawerList.setAdapter(adapter);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
            //    getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
             //   getActionBar().setTitle(drawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
		
        
        drawerList.setOnItemClickListener(new SlideMenuClickListener());
	}
    
	private class SlideMenuClickListener implements
	ListView.OnItemClickListener {
	
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
		// display view for selected nav drawer item
		displayView(position);
		
		}

		
}
	
	private void displayView(int position) {
		Intent intent;
		SharedPreferences sp;
		switch (position) {
		
		
//		case 1:
//		intent = new Intent(this, FragmentSetting.class);
//		startActivity(intent);
//		Toast.makeText(getApplicationContext(), "اعدادات", Toast.LENGTH_LONG).show();
//		//setActionBarStyling( "اعدادات");
//		drawerLayout.closeDrawers();
//		
//		break;
		
		case 0:
			
		    sp = PreferenceManager.getDefaultSharedPreferences(this);
		    sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);
	        
			intent = new Intent(this,MainActivity.class);
			
			
			if(this instanceof MainActivity ){
				drawerLayout.closeDrawers();
			}
			
			else{
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);	
			}

			break;
					
		case 1:	
				    
			intent = new Intent(this, FragmentFavourite.class);
			
			if(this instanceof FragmentFavourite){
				drawerLayout.closeDrawers();
			}
			
			else{
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				drawerLayout.closeDrawers();
			}
	
			break;
		
		case 2:
			
			intent = new Intent(this, FragmentSearch.class);
			
			if( this instanceof FragmentSearch){
				drawerLayout.closeDrawers();
			}
			
			else{
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				drawerLayout.closeDrawers();
			}

			break;
			
		case 3:
		    
			intent = new Intent(this, FragmentInfo.class);
			
			if(this instanceof FragmentInfo){
				
				drawerLayout.closeDrawers();
			}
			
			else{
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				drawerLayout.closeDrawers();
			}	
			
			break;
			
        case 4:
        	
        	intent = new Intent(Intent.ACTION_SEND);
        	intent.setType("message/rfc822");
        	intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"dalil@kaica.org.sa"});
        	intent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
        	Intent mailer = Intent.createChooser(intent, null);
        	startActivity(mailer);
        	drawerLayout.closeDrawers();
			break;
			
         case 5:
        	
        	intent = new Intent(Intent.ACTION_SEND);
        	intent.setType("message/rfc822");
        	intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"dalil@kaica.org.sa"});
        	intent.putExtra(Intent.EXTRA_SUBJECT, R.string.side_menu_azaf);
        	Intent mail = Intent.createChooser(intent, null);
        	startActivity(mail);
        	drawerLayout.closeDrawers();
			break;
						
			
		default:
			Toast.makeText(getApplicationContext(), "Sorry.... wrong choice!", Toast.LENGTH_LONG).show();
			break;
		}
		
	}

	
	protected void setActionBarStyling(String xyz) {
		ActionBar bar = getSupportActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(R.color.action_bar_title_color));
		
		bar.setIcon(R.drawable.logo);
		bar.setLogo(R.drawable.logo);
		bar.setDisplayHomeAsUpEnabled(false);
		
		
		
		View viewActionBar = getLayoutInflater().inflate(R.layout.action_bar, null);
		ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
		        ActionBar.LayoutParams.WRAP_CONTENT, 
		        ActionBar.LayoutParams.MATCH_PARENT, 
		        Gravity.CENTER);
		TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.title_text);
		textviewTitle.setText(xyz);
		bar.setCustomView(viewActionBar, params);
		bar.setDisplayShowCustomEnabled(true);
		bar.setDisplayShowTitleEnabled(false);
		
	}
	
	public void setCustomFont(TextView textView, String fontName){
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/"+fontName);
		if(textView!=null)
			textView.setTypeface(custom_font);
	}
}
