package com.example.arabicmapapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class SearchSiteAdapter  extends BaseAdapter  implements OnClickListener{
	
	
	private DataBaseHelper myDbHelper = null;
	private Activity activity;
    private List<ListModel> data;
    private ArrayList<ListModel> arraylist;
    private ArrayList<ListModel> searchList;
    private static LayoutInflater inflater=null;
    public Resources res;
    public String CategoryName;
    Context context;
    public boolean checkFav ; 
    
    
    int i=0;
 
    public SearchSiteAdapter(Activity a,List<ListModel> d,Resources resLocal, String categoryName) {
        
       
         activity = a;
         data=d;
         res = resLocal;
         CategoryName = categoryName;
         
         inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         
         this.arraylist = new ArrayList<ListModel>();
 		 this.arraylist.addAll(d);
 		 
 		 this.searchList = new ArrayList<ListModel>();
      
 }
	

	@Override
	public int getCount() {
		
		//return data.size();
		return searchList.size();
		
	}

	@Override
	public Object getItem(int position) {
		
		return position;
	}

	@Override
	public long getItemId(int position) {

		return position;
	}
	
	 public static class ViewHolder{
	        
	        public TextView siteHeading;
	        public TextView siteSub_heading;
            public Button shareButton;
            public Button copyButton;
            public Button favouriteButton;
	 
	    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		myDbHelper = new DataBaseHelper(activity);
		context=parent.getContext();
//		View vi = convertView;
        ViewHolder holder;
        
    if(convertView==null){
        	
    	convertView = inflater.inflate(R.layout.site_page, null);
        
        holder = new ViewHolder();
        
        holder.siteHeading = (TextView)convertView.findViewById(R.id.siteHeading); 
   //     holder.siteSub_heading = (TextView)vi.findViewById(R.id.siteSub_heading);
        holder.shareButton = (Button)convertView.findViewById(R.id.buttonShare);
        holder.copyButton = (Button)convertView.findViewById(R.id.buttonCopy);
        holder.favouriteButton = (Button)convertView.findViewById(R.id.buttonFavourite);
 
        convertView.setTag(holder);     
        
    }
    
    else{ 
        holder=(ViewHolder)convertView.getTag();
    }
    
    
    if(searchList.size()>=0){
    	holder.siteHeading.setText(searchList.get(position).getSites());
    	}
    
    else{       
        convertView.setOnClickListener(new OnItemClickListener( position ));
        }
    
    
    holder.shareButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		    sharingIntent.setType("text/plain");
		    ListModel model =searchList.get((Integer) v.getTag());
		    String shareBody = model.getLinks();
		    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
		    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		   
		    context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
		
		}
	});
    
    holder.favouriteButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			ListModel model = searchList.get((Integer) v.getTag());
			int idFav = model.getID(); 
			String catFav  = CategoryName;
			String siteFav = model.getSites();
			String linkFav = model.getLinks();  		
			
			FavouriteModel favMod = new FavouriteModel();
			favMod.setID(idFav);
			favMod.setCategoryFavourite(catFav);
			favMod.setSitesFavourite(siteFav);
			favMod.setLinksFavourite(linkFav);
			
  			checkFav = myDbHelper.checkFavourite(catFav,siteFav);
			
			if(checkFav == true){
				
				myDbHelper.deleteFavourite(catFav,siteFav);
				Toast.makeText(context, "تم إلغاء التفضيل", Toast.LENGTH_LONG).show();
			}
			else if(checkFav == false){
				myDbHelper.addFavourite(favMod);
				Toast.makeText(context, "تم تفضيل", Toast.LENGTH_LONG).show();
			}
			
		}
	});
    
    holder.copyButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			ListModel  str = searchList.get((Integer) v.getTag());
			String str1 = str._links;
			
			String stringYouExtracted =str1;
			ClipboardManager clipboard = (ClipboardManager)activity.getSystemService(activity.CLIPBOARD_SERVICE);
			clipboard.setText(stringYouExtracted);
			Toast.makeText(activity,"نسخ إلى الحافظة",Toast.LENGTH_SHORT).show();
			
		}
	});
    
    
    holder.favouriteButton.setTag(position);
    holder.copyButton.setTag(position);
    holder.shareButton.setTag(position);
        
    return convertView;
	}
	
	
	// Filter Class
		public void filter(String charText) {
			charText = charText.toLowerCase(Locale.getDefault());

			searchList.clear();

			if(charText.length() >= 3) 
			{

				for (ListModel lm : data)
				{
					if (lm.getSites().toLowerCase(Locale.getDefault()).contains(charText)) {
						searchList.add(lm);
						
					}

				}
				if(searchList.size()==0){

					Toast.makeText(activity,"لا توجد نتائج للبحث",Toast.LENGTH_SHORT).show();

				}
			}


			else if(charText.length() == 0){

				searchList.clear();
				Toast.makeText(activity,"لا توجد نتائج للبحث",Toast.LENGTH_SHORT).show();
			}
			
			
			notifyDataSetChanged();
		}

	@Override
	public void onClick(View arg0) {
		 Log.v("CustomAdapter", "=====Row button clicked=====");
		
	}
	
	private class OnItemClickListener  implements OnClickListener{           
        private int mPosition;
         
        OnItemClickListener(int position){
             mPosition = position;
        }
         
        @Override
        public void onClick(View view) {

   
//          MainActivity ma = (MainActivity)activity;
        	PageCategoryActivity pa = (PageCategoryActivity) activity;

         /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

            pa.onItemClick(mPosition);
           
        } 
        
        
    }  
	


}
