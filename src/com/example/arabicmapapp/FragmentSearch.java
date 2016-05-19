package com.example.arabicmapapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.SQLException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class FragmentSearch extends DrawerScreen {
	
	
	ListView list;
	private DataBaseHelper myDbHelper = null;
    SearchSiteAdapter adapter;
    FragmentSearch CustomListView = null;
    public List<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
    public ImageView image;
    public Resources res;
    public String categoryItems;
    Context context;
    private int imageId = R.drawable.back;
    EditText editsearch;

    public boolean mUserLearnedDrawer;
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState)  {
//		 getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
//	    	getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
	    	super.onCreate(savedInstanceState);
	    	
	    	  ViewGroup content=(ViewGroup) findViewById( R.id.frame_container);
	         
	          getLayoutInflater().inflate(R.layout.fragment_search,content,true);
	          
	          initializeDrawer();
	          
	          overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
	          
	          android.support.v7.app.ActionBar ab =  this.getSupportActionBar() ;
	          setActionBarStyling("البحث");
	          ab.setTitle( "البحث");
	          
	         
	          
	          myDbHelper = new DataBaseHelper(this);
	 		 
	 		 try {

	 	    		myDbHelper.openDataBase();

	 	    	}catch(SQLException sqle){

	 	    		throw sqle;

	 	    	}
	 		
	 		 CustomListView = this;
	 		 
	 	    res =getResources();
	        list= ( ListView )findViewById( R.id.site_listView ); 
	        
	        Intent intent = getIntent();
//	        categoryItems = intent.getStringExtra("CategoryName");
//	        String categoryDescription = intent.getStringExtra("CategoryDescription");
	        
	        CustomListViewValuesArr  =  myDbHelper.getAllSiteItems();
	        adapter=new SearchSiteAdapter( CustomListView, CustomListViewValuesArr,res,"");
	        
	        list.setAdapter( adapter );
	        list.setTextFilterEnabled(true);
	        
	        // Yahan se search ka kaam he
	        
	     // Locate the EditText in listview_main.xml
			editsearch = (EditText) findViewById(R.id.edit_search);
			editsearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
					//adapter.filter(text);
					
				}
			});
	        
	        list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> adapterView,
						View view, int position, long offset) {
					
					Intent i = new Intent(FragmentSearch.this,WebsiteView.class);
					ListModel listM = CustomListViewValuesArr.get(position);
					
					i.putExtra("SitesLinks", listM.getLinks());
					startActivity(i);
					
					
					
					Log.d("message", "Achaaaaa hmko sikharya he ....... :P");
					
				}
			});
	        
	        
	        editsearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
				
				@Override
				  public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
					
					if (event != null && event.getAction() != KeyEvent.ACTION_DOWN) {
				        return false;
				    } 
					
					else if (actionId == EditorInfo.IME_ACTION_SEARCH
					        || event == null
					        || event.getKeyCode() == KeyEvent.KEYCODE_ENTER ) {
						searchArabia(v);
			            return true;
			        }
					
					return false;
				}
			});
	        
	        
	        
	 }
	 
	 public void searchArabia(View view){
		 
		 String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
		 adapter.filter(text);
		 
	 }


	@Override
	public void onBackPressed() {
		
		super.onBackPressed();
		
		overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	}


	
	 
	 
//	 @Override
//		protected void onResume() {
//			getWindow().getDecorView().findViewById(android.R.id.content).invalidate();
//			super.onResume();
//		}


}
