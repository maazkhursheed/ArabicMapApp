package com.example.arabicmapapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class FragmentFavourite extends DrawerScreen{
	
	

	 ListView fav_list;
	private DataBaseHelper myDbHelper = null;
	Favourite_Adapter fav_adapter;
    public  PageCategoryActivity CustomListView = null;
    public List<FavouriteModel> CustomListViewValuesArr = new ArrayList<FavouriteModel>();
    public ImageView image;
    Context context;
    public String categoryItems;
    public Resources res;
    public boolean mUserLearnedDrawer;
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
	
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
    	
    	super.onCreate(savedInstanceState);
        
        ViewGroup content=(ViewGroup) findViewById( R.id.frame_container);
        getLayoutInflater().inflate(R.layout.fragment_favourite,content,true);
        
        res =getResources();
        fav_list = (ListView)findViewById( R.id.fav_lV);
        
        initializeDrawer();
        
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        
        Intent intent = getIntent();
        categoryItems = intent.getStringExtra("CategoryName");
        
        android.support.v7.app.ActionBar ab =  this.getSupportActionBar() ;
        setActionBarStyling("المفضلة");
        ab.setTitle("المفضلة");
        
       
        
        myDbHelper = new DataBaseHelper(this);
        CustomListViewValuesArr = myDbHelper.getAllFavourites();
        fav_adapter = new Favourite_Adapter(this, CustomListViewValuesArr,res,categoryItems);
        fav_list.setAdapter(fav_adapter);

        fav_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView,
					View view, int position, long offset) {
				
				Intent i = new Intent(getApplicationContext(),WebsiteView.class);
				FavouriteModel listM = CustomListViewValuesArr.get(position);
				
				i.putExtra("SitesLinks", listM.getLinksFavourite() );
				startActivity(i);
				
				
				
				Log.d("message", "Achaaaaa hmko sikharya he ....... :P");
				
				
			}
		});
        
        
        
       
    }


      @Override
     public void onBackPressed() {

// 	   Intent intent = new Intent(this,MainActivity.class);
// 	   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
// 	   startActivity(intent);
       super.onBackPressed();
 	   finish();
 	   
 	   overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	//super.onBackPressed();
   }

}
