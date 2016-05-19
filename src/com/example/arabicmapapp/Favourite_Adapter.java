package com.example.arabicmapapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Favourite_Adapter  extends BaseAdapter  implements OnClickListener{
	
	
	private DataBaseHelper myDbHelper = null;
	private Activity activity;
    private List<FavouriteModel> data;
    private List<FavouriteModel> arraylist;
    private static LayoutInflater inflater=null;
    public Resources res;
    public String CategoryName;
    
    Context context;
    
    public boolean checkFav ; 

    int i=0;
    
    public Favourite_Adapter(Activity a,List<FavouriteModel> d,Resources resLocal, String categoryName) {
        
        
        activity = a;
        data=d;
        res = resLocal;
        CategoryName = categoryName;
        
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        this.arraylist = new ArrayList<FavouriteModel>();
		this.arraylist.addAll(d);
}

	@Override
	public int getCount() {
		return data.size();
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
	        public Button unfavouriteButton;
	    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		myDbHelper = new DataBaseHelper(activity);
		context=parent.getContext();
//		View vi = convertView;
        ViewHolder holder;
        
    if(convertView==null){
        	
    	convertView = inflater.inflate(R.layout.unfav_site, null);
        
       
        holder = new ViewHolder();
        
        holder.siteHeading = (TextView)convertView.findViewById(R.id.fav_siteHeading); 
 //       holder.siteSub_heading = (TextView)convertView.findViewById(R.id.fav_siteSub_heading);
        holder.shareButton = (Button)convertView.findViewById(R.id.buttonShare);
        holder.copyButton = (Button)convertView.findViewById(R.id.buttonCopy);
        holder.unfavouriteButton = (Button)convertView.findViewById(R.id.buttonUnFavourite);
       
        convertView.setTag(holder);
                      
        }
    
    else {
        holder=(ViewHolder)convertView.getTag();
    }
    
    
    
    if(data.size()>=0){
    	data.get(position).getSitesFavourite();
    	data.get(position).getLinksFavourite();
    	holder.siteHeading.setText(data.get(position).getSitesFavourite());
      }
    
    else{       
        convertView.setOnClickListener(new OnItemClickListener( position ));
       }
    
    
   holder.shareButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		    sharingIntent.setType("text/plain");
		    FavouriteModel model = data.get((Integer) v.getTag());
		    String shareBody = model.getLinksFavourite();
		    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "دليل موقع اللغة العربية");
		    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		   
		    context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
		
		}
	});
   
   
   holder.unfavouriteButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
		    FavouriteModel model = data.get((Integer) v.getTag());
			int idFav = model.getID(); 
			String catFav  = model.getCategoryFavourite();
			String siteFav = model.getSitesFavourite();
			String linkFav = model.getLinksFavourite(); 
		
			FavouriteModel favMod = new FavouriteModel();
			favMod.setID(idFav);
			favMod.setCategoryFavourite(catFav);
			favMod.setSitesFavourite(siteFav);
			favMod.setLinksFavourite(linkFav);
			
 			checkFav = myDbHelper.checkFavourite(catFav,siteFav);
			
			if(checkFav == true){
				
				myDbHelper.deleteFavourite(catFav,siteFav);
				arraylist.clear();
				arraylist = myDbHelper.getAllFavourites();
				notifyDataSetChanged();				
				Toast.makeText(context, "ازالة التفضيل", Toast.LENGTH_LONG).show();
				Intent i = new Intent(context,FragmentFavourite.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(i);
				
			}
//			else if(checkFav == false){
//				myDbHelper.addFavourite(favMod);
//				Toast.makeText(context, "تم تفضيل", Toast.LENGTH_LONG).show();
//			}
			
		}
	});
   
   holder.copyButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			FavouriteModel  str = data.get((Integer) v.getTag());
			String str1 = str._linksFavourite;
			

			String stringYouExtracted =str1;
			ClipboardManager clipboard = (ClipboardManager)activity.getSystemService(activity.CLIPBOARD_SERVICE);
			clipboard.setText(stringYouExtracted);
			Toast.makeText(activity,"نسخ إلى الحافظة",Toast.LENGTH_SHORT).show();
			
		}
	});
    
          holder.unfavouriteButton.setTag(position);
          holder.copyButton.setTag(position);
          holder.shareButton.setTag(position);
          
    return convertView;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private class OnItemClickListener  implements OnClickListener{           
        private int mPosition;
         
        OnItemClickListener(int position){
             mPosition = position;
        }
         
        @Override
        public void onClick(View view) {

        	PageCategoryActivity pa = (PageCategoryActivity) activity;
            pa.onItemClick(mPosition);
           
        } 
        
        
    }  

}
