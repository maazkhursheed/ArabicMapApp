package com.example.arabicmapapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NavDrawerAdapter extends BaseAdapter{

	private Context context;
	private String[] drawerItemList;
	private int[] arabicImages;
	
	public NavDrawerAdapter(Context context, String[] navDrawerItemList, int[] mArabiImages){
		this.context = context;
		this.drawerItemList = navDrawerItemList;
		this.arabicImages = mArabiImages;
	}
	@Override
	public int getCount() {
		
		return drawerItemList.length;
	}

	@Override
	public Object getItem(int position) {
		
		return drawerItemList.length;
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		
		if(convertView == null){
			LayoutInflater mInflater = (LayoutInflater)
	                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	        convertView = mInflater.inflate(R.layout.drawer_list_item, null);
	        
			viewHolder = new ViewHolder();
//			viewHolder.relative = (RelativeLayout)convertView.findViewById(R.id.drawerItemParentView);
//			viewHolder.userImage = (CircularImageView) convertView.findViewById(R.id.drawerItem_image);
//			viewHolder.nextArrow = (ImageView) convertView.findViewById(R.id.drawerItem_arrow);
//			viewHolder.counter = (TextView) convertView.findViewById(R.id.drawerItem_counter);
//			viewHolder.drawerTitle = (TextView) convertView.findViewById(R.id.title);
		    viewHolder.iv = (ImageView)convertView.findViewById(R.id.ivIcon);
		    viewHolder.tv = (TextView)convertView.findViewById(R.id.tvTitle);
			
			convertView.setTag(viewHolder);
		}
		
		else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
    
//		viewHolder.userImage.setVisibility(View.GONE);
//		viewHolder.nextArrow.setVisibility(View.GONE);
//		viewHolder.counter.setVisibility(View.GONE);
//		viewHolder.drawerTitle.setTextColor(context.getResources().getColor(R.color.color_drawer_title));
//		viewHolder.drawerTitle.setText(drawerItemList.get(position).getTitle());
//		convertView.setBackgroundColor(context.getResources().getColor(R.color.color_drawer_rows));
         viewHolder.iv.setBackgroundResource(arabicImages[position]);
         viewHolder.tv.setText(drawerItemList[position]);

        return convertView;
		
	}
	
	public Typeface setCustomFont(String fontName) {
		Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/"+fontName);
		
		return custom_font;
	}
	
	private static class ViewHolder{
		
		
		TextView tv;
		ImageView iv;
		
	}

}
