package com.example.arabicmapapp;

//import android.support.v7.app.ActionBarActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.*;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;	
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast; 
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;



public class MainActivity extends DrawerScreen {
	
	
	int mPosition = -1;
	String mTitle = "";
	 
	 // Array of strings storing country names
	 String[] mArabicContents ;
	
	// Array of integers points to images stored in /res/drawable-ldpi/
	 int[] mArabiImages = new int[]{
			 R.drawable.adab_arabi,			 
			 R.drawable.fav_icon,
			 R.drawable.search_icon,
			 R.drawable.about_icon,
			 R.drawable.contact_us,
			 R.drawable.site_store
			 };
	
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout mDrawer ;
    private List<HashMap<String,String>> mList ;
    private SimpleAdapter mAdapter;
    final private String ArabicContent = "arabicContent";
    final private String ArabicImage = "arabicImage";
 

	ListView list;
    private DataBaseHelper myDbHelper = null;
    CustomAdapter adapter;
    public  MainActivity CustomListView = null;
    public   List<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
    public int[] image = {R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4};
    public ArrayList<String> Categories = new ArrayList<String>();
    public boolean mUserLearnedDrawer;
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     	
    	super.onCreate(savedInstanceState);
    //	requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
    //	supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
      
    	getSupportActionBar().hide();
        
        ViewGroup content=(ViewGroup) findViewById( R.id.frame_container);       
        getLayoutInflater().inflate(R.layout.activity_main,content,true);
        initializeDrawer();
       
        
        mArabicContents = getResources().getStringArray(R.array.arabic_utilities);
        ArrayAdapter adapterArray = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mArabicContents);
        android.support.v7.app.ActionBar ab =  this.getSupportActionBar() ;
        ab.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayShowTitleEnabled(false);
       // setActionBarStyling("دليل اللغة العربية");

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).commit();
       
        
       
        
        
        myDbHelper = new DataBaseHelper(this);

        try {

        	myDbHelper.createDataBase();

    	} catch (IOException ioe) {

    		throw new Error("Unable to create database");

    	}

    	try {

    		myDbHelper.openDataBase();

    	}catch(SQLException sqle){

    		throw sqle;

    	}
    	
  ///// From here the work of Navigation Drawer /////

    	 
    	// Title of the activity
    	 mTitle = (String)getTitle();
    	 
    	// Getting a reference to the drawer listview
    	 mDrawerList = (ListView) findViewById(R.id.right_drawer);
    	 
    	// Each row in the list stores country name, count and flag
    	 mList = new ArrayList<HashMap<String,String>>();
    	 for(int i=0;i<5;i++){
    		 
    	 HashMap<String, String> hm = new HashMap<String,String>();
    	 hm.put(ArabicContent, mArabicContents[i]);
    	 hm.put(ArabicImage, Integer.toString(mArabiImages[i]) );
    	 
    	 mList.add(hm);
    	
    	 }
    	 
    	// Keys used in Hashmap
    	 String[] from = {ArabicContent,ArabicImage};
    	 
    	// Ids of views in listview_layout
    	 int[] to = {  R.id.tvTitle,R.id.ivIcon };
    	 
    	// Instantiating an adapter to store each items
    	 // R.layout.drawer_layout defines the layout of each item
    	 mAdapter = new SimpleAdapter(this, mList, R.layout.drawer_list_item, from, to);
    	 
    	 // Getting reference to DrawerLayout
    	 mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
    	 
    //	 Creating a ToggleButton for NavigationDrawer with drawer event listener
//    	 mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open,R.string.drawer_close){
//    	 
//    	 /** Called when drawer is closed */
//    	 public void onDrawerClosed(View view) {
//    	 highlightSelectedCountry();
// 
//    	 supportInvalidateOptionsMenu();
//    	 }
//    	 
//    	/** Called when a drawer is opened */
//    	 public void onDrawerOpened(View drawerView) {
//
//    	 supportInvalidateOptionsMenu();
//    	 }
//    	 };
    	 
    	// Setting event listener for the drawer
    	 mDrawerLayout.setDrawerListener(mDrawerToggle);
    	
    	 mDrawerList.setAdapter(adapterArray);
    	 
    	 
    	 
         CustomListView = this;
         

         
         Resources res =getResources();
         list= ( ListView )findViewById( R.id.listView1 );  // List defined in XML ( See Below )
          
         /**************** Create Custom Adapter *********/
        CustomListViewValuesArr  =  myDbHelper.getAllCategories();
        
        for(int i = 0; i< CustomListViewValuesArr.size();i++){
        	Categories.add( CustomListViewValuesArr.get(i).getCategory());
        }
        
         adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res ,image );
         list.setAdapter( adapter );
         list.setOnItemClickListener(new OnItemClickListener() {

 			@Override
 			public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
 				
// 				ListModel lm = (ListModel) adapter.getItem(position);
 				
 				Intent i = new Intent(MainActivity.this,PageCategoryActivity.class);
 				ListModel listM = CustomListViewValuesArr.get(position);
 				
 				
 				
 				
 				i.putExtra("CategoryName", listM.getCategory());
 				i.putExtra("CategoryDescription", listM.getSites());
 				i.putExtra("SitesLinks", listM.getLinks());
 				i.putExtra("CategoryImage", image[position]);
 				i.putExtra("CategoryPosition", position);
 				i.putStringArrayListExtra("Categories",Categories );
 				startActivity(i);

 				SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
 		            sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, false).commit();
 		       
 				
 				Log.d("message", "Achaaaaa hmko sikharya he ....... :P");
 				
 			}
 		});

        
        
   
    }

    
   @Override
	public void onBackPressed() {
	   new AlertDialog.Builder(this)
       .setMessage("هل تريد خروج من التطبيق")
       .setCancelable(false)
       .setIcon(R.drawable.logo)
       .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
    	   
           public void onClick(DialogInterface dialog, int id) {
                
        	   MainActivity.this.finish();
           }
       })
       .setNegativeButton("لا", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int id) {
			
			dialog.cancel();
			
		}
	})
       .show();
	
	}


public void openDrawer(View v){
	   
	   mDrawerLayout.openDrawer(Gravity.RIGHT);
	      
   }
   
   public void searchPanel(View v){
	   
	  Intent intent = new Intent(this, FragmentSearch.class);
	  startActivity(intent);
	  finish();
   }

	
    
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//    	// Inflate the menu; this adds items to the action bar if it is present.
//    	 getMenuInflater().inflate(R.menu.main, menu);
//    	 return true;
//    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
       // mDrawerToggle.syncState();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       
    }
 

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//    	if (mDrawerToggle.onOptionsItemSelected(item)) {
//    		 return true;
//    		 }
//    		 return super.onOptionsItemSelected(item);
//    }
    

    

    
 // Highlight the selected country : 0 to 4
    public void highlightSelectedCountry(){
    int selectedItem = mDrawerList.getCheckedItemPosition();
    
    if(selectedItem > 4)
    mDrawerList.setItemChecked(mPosition, true);
    else
    mPosition = selectedItem;
    

    }

    
   
    
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content
        
        
        return super.onPrepareOptionsMenu(menu);
    }

   
    
   


	public void onItemClick(int mPosition) {
		ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);
		   
		 
      
		
		Intent i = new Intent(MainActivity.this,PageCategoryActivity.class);
		startActivity(i);
		
	}


	
}
