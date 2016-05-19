package com.example.arabicmapapp;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PageCategoryActivity extends DrawerScreen {
	
	ListView list;
	private DataBaseHelper myDbHelper = null;
    SiteAdapter adapter;
    public  PageCategoryActivity CustomListView = null;
    public List<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
    public ImageView image;
    Context context;
    private int imageId = R.drawable.back;
    public ArrayList<String> Categories = new ArrayList<String>();
	public String categoryItems;
	public int categoryItemsPosition;
    public Resources res;
    public int CatImage;
    public int[] imageItems = {R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4};
    private ImageView nextButton ;
    private ImageView disable_nextButton ;
    private ImageView backButton;
    private ImageView disable_backButton ;
    final String Hisabaat = "حسابات";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		super.onCreate(savedInstanceState);
		
		 ViewGroup content=(ViewGroup) findViewById( R.id.frame_container);       
	        getLayoutInflater().inflate(R.layout.activity_page_category,content,true);
	        initializeDrawer();
	        
	        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
//		setContentView(R.layout.activity_page_category);
		
		 myDbHelper = new DataBaseHelper(this);
		 
		 try {

	    		myDbHelper.openDataBase();

	    	}catch(SQLException sqle){

	    		throw sqle;

	    	}
		
		 CustomListView = this;
	        

	        
	        res =getResources();
	        list= ( ListView )findViewById( R.id.site_listView );  // List defined in XML ( See Below )
	       
	        Intent intent = getIntent();
	        categoryItems = intent.getStringExtra("CategoryName");
	        String categoryDescription = intent.getStringExtra("CategoryDescription");
	          
	        Categories = intent.getStringArrayListExtra("Categories");
	        
	        categoryItemsPosition = intent.getIntExtra("CategoryPosition", 0);
	        
	         CatImage = intent.getIntExtra("CategoryImage", 0);
	         image = (ImageView) findViewById(R.id.category_image);
	         image.setBackgroundResource(CatImage);
	         setActionBarStyling(categoryItems);
	        
	     // Actionbar color and title dynamicaly 
	         android.support.v7.app.ActionBar ab =  this.getSupportActionBar() ;
		     ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#219D95"));  
		     ab.setBackgroundDrawable(colorDrawable);
		     ab.setTitle(categoryItems);
		     
		    
	       
	        /**************** Create Custom Adapter *********/
	        CustomListViewValuesArr  =  myDbHelper.getAllSites(categoryItems);
	        
	        adapter=new SiteAdapter( CustomListView, CustomListViewValuesArr,res,categoryItems );
	        
	        list.setAdapter( adapter );
	        
	        list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> adapterView,
						View view, int position, long offset) {
					
					Intent i = new Intent(getApplicationContext(),WebsiteView.class);
					ListModel listM = CustomListViewValuesArr.get(position);
					
					i.putExtra("SitesLinks", listM.getLinks());
					startActivity(i);
					
					Log.d("message", "Achaaaaa hmko sikharya he ....... :P");
					
				}
			});
	        
	       

	        
	        // 3. show message on textView 
	        ((TextView)findViewById(R.id.category_title)).setText(categoryItems);
	        ((TextView)findViewById(R.id.category_sub_title)).setText(categoryDescription);
	        

	       nextButton = (ImageView) findViewById(R.id.next_category_arrow_image); 
	       disable_nextButton = (ImageView) findViewById(R.id.disable_next_category_arrow_image);
	       
	       backButton = (ImageView) findViewById(R.id.back_category_arrow_image);
	       disable_backButton = (ImageView) findViewById(R.id.disable_back_category_arrow_image);
	        
	       
	       if( categoryItemsPosition  == 0){
	    	   
	    	    backButton.setVisibility(View.INVISIBLE);
				disable_backButton.setVisibility(View.VISIBLE);
	       }
	       
	       
	       if( categoryItemsPosition  == 3 ){
				
				nextButton.setVisibility(View.INVISIBLE);
				disable_nextButton.setVisibility(View.VISIBLE);
			}
	       
	       
	}
	
	
	
//	@Override
//	protected void onResume() {
//		getWindow().getDecorView().findViewById(android.R.id.content).invalidate();
//		super.onResume();
//	}

	
           @Override
	public void onBackPressed() {
	
        	   Intent intent = new Intent(this,MainActivity.class);
        	   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	   startActivity(intent);
        	   finish();
        	   
        	   overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
		//super.onBackPressed();
	}



		public void goBack(View v){
			
			boolean matched = false;
        	   

           for(int i=0 ; Categories.size()>i; i++){
				
				if(Categories.get(i).equals(categoryItems)){
					matched = true;
					int j=i;
					j--;
					if(j == 0){
//				    Intent intent = new Intent(this,MainActivity.class);
//				    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
//					finish();
						backButton.setVisibility(View.INVISIBLE);
                    	disable_backButton.setVisibility(View.VISIBLE);
                    	nextButton.setVisibility(View.VISIBLE);
						
						
					}
					if(Categories.size()>j){
						image.setBackgroundResource(imageItems[j]);
				        image.invalidate();
						setNewAdapter(Categories.get(j));
						nextButton.setVisibility(View.VISIBLE);
						disable_nextButton.setVisibility(View.INVISIBLE);
						
                        if(j == 3){
							
                        	backButton.setVisibility(View.VISIBLE);
                        	
                        	disable_nextButton.setVisibility(View.INVISIBLE);
						}
                        
					
					}

				}
				
			}
           
           if(!matched){
				
				 image.setBackgroundResource(imageItems[0]);
				 image.invalidate();
				setNewAdapter(Categories.get(0));
				
			}
        	   //finish();
	
           }
		
		public void goNext(View v){
			boolean matched = false;
			
			for(int i =0 ; i< Categories.size(); i++){
				
				if(Categories.get(i).equals(categoryItems)){
					
					matched = true;
					
				int j=	i;
					j++;
					if(j< Categories.size()){
						image.setBackgroundResource(imageItems[j]);
				        image.invalidate();
						setNewAdapter(Categories.get(j));
						backButton.setVisibility(View.VISIBLE);
						disable_backButton.setVisibility(View.INVISIBLE);
						
						if(j == 3){
							
							nextButton.setVisibility(View.INVISIBLE);
							disable_nextButton.setVisibility(View.VISIBLE);
						}
						
						break;
						
					}
//					else if(i == Categories.size()){
//						 image.setBackgroundResource(imageItems[j]);
//						 image.invalidate();
//						setNewAdapter(Categories.get(0));
//					}
					
				}
				
			}
						 
			if(!matched){
				
				 image.setBackgroundResource(imageItems[0]);
				 image.invalidate();
				 setNewAdapter(Categories.get(0));
				 
			}			
			
		}
	
		
		public void setNewAdapter(String categoryName){
			 
			    categoryItems = categoryName;
			
			    CustomListViewValuesArr  =  myDbHelper.getAllSites(categoryName);
			 
			    ListModel object = CustomListViewValuesArr.get(0);
			
			    ((TextView)findViewById(R.id.category_sub_title)).setText( object.getSites());
		        adapter=new SiteAdapter( CustomListView, CustomListViewValuesArr,res,categoryName );
		        
		        list.setAdapter( adapter );
		        ((TextView)findViewById(R.id.category_title)).setText(categoryItems);
		        list.invalidate();
		        
		        android.support.v7.app.ActionBar ab =  this.getSupportActionBar() ;
			    ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#219D95"));  
			    ab.setBackgroundDrawable(colorDrawable);
			    ab.setTitle(categoryItems);
			    setActionBarStyling(categoryItems);
		        getWindow().getDecorView().invalidate();
			
		}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.page_category, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

	public void onItemClick(int mPosition) {
		ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);
		   
		 
       
	}
}
