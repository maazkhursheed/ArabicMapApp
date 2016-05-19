package com.example.arabicmapapp;

import java.util.ArrayList;
import java.util.HashMap;
 



import java.util.List;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class CustomAdapter extends BaseAdapter  implements OnClickListener {
 
	private DataBaseHelper myDbHelper = null;
	private Activity activity;
    private List<ListModel> data;
    private static LayoutInflater inflater=null;
    public Resources res;
    public int[] Images;
   
//    ListModel tempValues=null;
    int i=0;
 
    public CustomAdapter(Activity a, List<ListModel> d,Resources resLocal , int[] images ) {
        
       
         activity = a;
         data=d;
         res = resLocal;
         Images = images;
        
         inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      
 }
 
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
    
    public static class ViewHolder{
        
        public TextView textTitle;
        public TextView textSubTitle;
        public ImageView image;
        public ImageView favImage;
        public ImageView viewImage;
//        public TextView favText;
//        public TextView viewText;
 
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	myDbHelper = new DataBaseHelper(activity);
//    	myDbHelper.getCategoryDescription(data.get(position)._category);
    	

    	View vi = convertView;
         ViewHolder holder;
         
         
         
        if(convertView==null){
        	
            vi = inflater.inflate(R.layout.list_row, null);
            
            TextView favText = (TextView)vi.findViewById(R.id.textView_like);
            int countFav = myDbHelper.getFavouritesCount(data.get(position).getCategory());
            favText.setText(String.valueOf(countFav));
            
            TextView viewText = (TextView)vi.findViewById(R.id.textViews);
            int countSite = myDbHelper.getCategories_SitesCount(data.get(position).getCategory());
            viewText.setText(String.valueOf(countSite));
 
        holder = new ViewHolder();
        holder.textTitle = (TextView)vi.findViewById(R.id.title); 
        holder.textSubTitle = (TextView)vi.findViewById(R.id.sub_title); 
        holder.image=(ImageView)vi.findViewById(R.id.list_image); 
        holder.favImage=(ImageView)vi.findViewById(R.id.imageView1);
        holder.viewImage=(ImageView)vi.findViewById(R.id.imageView2);
//        holder.favText=(TextView)vi.findViewById(R.id.textView_like);
//        holder.viewText=(TextView)vi.findViewById(R.id.textView1);
        
        vi.setTag(holder);
        
        
        
        
        }
        else 
            holder=(ViewHolder)vi.getTag();
        
        if(data.size()>=0){
        	holder.textTitle.setText(data.get(position).getCategory());
            holder.textSubTitle.setText(data.get(position).getSites());
            holder.image.setBackgroundResource(Images[position]);
            
//            int countFav = myDbHelper.getFavouritesCount(data.get(position).getCategory());
//            holder.favText.setText(String.valueOf(countFav));
            
//            int countSite = myDbHelper.getCategories_SitesCount(data.get(position).getCategory());
//            holder.viewText.setText(String.valueOf(countSite));
        }
        else
        {           

             vi.setOnClickListener(new OnItemClickListener( position ));
        }
        
        return vi;       
       
        
    }

	@Override
	public void onClick(View v) {
		 Log.v("CustomAdapter", "=====Row button clicked=====");
	}
	
	 private class OnItemClickListener  implements OnClickListener{           
         private int mPosition;
          
         OnItemClickListener(int position){
              mPosition = position;
         }
          
         @Override
         public void onClick(View view) {

    
           MainActivity ma = (MainActivity)activity;

          /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

             ma.onItemClick(mPosition);
             
             Intent i = new Intent(ma,PageCategoryActivity.class);
             ma.startActivity(i);
             
         }               
     }   
}